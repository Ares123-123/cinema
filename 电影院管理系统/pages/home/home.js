const app = getApp()
const db = wx.cloud.database()
var search = ''
var search_key = ''
Page({
  data: {
    clientHeight: 0,
    currentTab: 0,
    cinema:[],
    film:[],
  },
  onLoad: function (options) {
    var that = this;
    that.getfilm()
    this.getcinema()
    // 动态获取设备屏幕高度
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          clientHeight: res.windowHeight-30
        });
        console.log(res.windowHeight)
      }
    });
  },
  radioChange: function(e) {
    search =  e.detail.value
    console.log('radio发生change事件，携带value值为：', search)
  },
  
  /*获取影院*/
 getcinema(){
  console.log("查询电影院表单")
  var that = this;
  wx.request({
    url: 'http://localhost:9000/selectAllCinema', //本地服务器地址
    data: { //data中的参数值就是传递给后台的数据
    },
    method: 'get',
    header: {
      'content-type': 'application/json' //默认值
    },
    success: function(res) { //res就是接收后台返回的数据
      that.setData({
        cinema: res.data
      })
      console.log(res.data);
    },
    fail: function(res) {
      console.log("失败",res);
    }
  })
  },
  cinema_detail:function(e){
    let cinema_id = e.currentTarget.dataset.cinema_id
    wx.navigateTo({
      url: '/pages/cinema_detail/cinema_detail?cinema_id='+cinema_id,
    })
  },
  /*获取影院*/

  /*获取电影*/
  getfilm(){
    console.log("查看电影院表单")
    var that = this;
    wx.request({
      url: 'http://localhost:9000/selectAllFilm', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
        
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        that.setData({
          film: res.data
        })
        console.log(res.data);
      },
      fail: function(res) {
        console.log("失败",res);
      }
    })
  },
  film_detail:function(e){
    console.log(e.currentTarget.dataset.film_id)
    let film_id = e.currentTarget.dataset.film_id
    wx.navigateTo({
      url: '/pages/film_detail/film_detail?film_id='+film_id,
    })
   //wx.redirectTo 方法跳转会关闭当前页面跳转到某个页面  
   //wx.reLaunch 方法跳转会关闭所有打开的页面，跳转到某个页面
  },
  /*获取电影*/

  /*滑动组件*/
  swichTab: function (e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      var id = e.target.dataset.current;
      that.setData({
        currentTab: id
      })
    }
  },
  bindTouch: function (e) {
    var that = this;
    var id = e.detail.current;
    that.setData({
      currentTab: id
    });
  },
  switchTab_map:function(e){
   wx.navigateTo({
     url: '/pages/map/map',
   })
  },
  switchTab_rank:function(e){
    wx.navigateTo({
      url: '/pages/cinema_rank/cinema_rank',
    })
   },
  bindTouch_cinema: function (e) {
    var that = this;
    var id = e.detail.current;
    that.setData({
      currentTab: id
    });
  },
})

