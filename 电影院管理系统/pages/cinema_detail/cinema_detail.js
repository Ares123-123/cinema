 let cinema_id = ''
Page({
  data: {
    latitude: 33.38893,
    longitude: 120.1953,
    markers: [],
    customCalloutMarkerIds: [],
    num: 1,    //地图
    session:[],
    showFilm:[],
    id:''
  },

  onLoad: function (e) {
    //电影展示卡
    this.showAllFilm()
    cinema_id = e.cinema_id
    console.log("查询电影院表单",cinema_id)
 //展示该电影院的排片
 var that = this;
 wx.request({
   url: 'http://localhost:9000/wx_selectOneCinema_Session', //本地服务器地址
   data: { //data中的参数值就是传递给后台的数据
     cinema_id:cinema_id
   },
   method: 'get',
   header: {
     'content-type': 'application/json' //默认值
   },
   success: function(res) { //res就是接收后台返回的数据
     that.setData({
      session: res.data
     })
     console.log(res.data);
   },
   fail: function(res) {
     console.log("失败",res);
   }
 })

  //查询该电影院信息
  wx.request({
    url: 'http://localhost:9000/selectOneCinema', //本地服务器地址
    data: { //data中的参数值就是传递给后台的数据
      cinema_id:cinema_id
    },
    method: 'get',
    header: {
      'content-type': 'application/json' //默认值
    },
    success: function(res) { //res就是接收后台返回的数据
      console.log('电影院信息',res.data);
      //显示地图
  var normalCallout = {
    id: 1,
    latitude: res.data[0].latitude,
    longitude: res.data[0].longitude,
    iconPath: '/image/location.png',
    callout: {
      content: res.data[0].cinema_name,
      color: '#af1700',
      fontSize: 14,
      borderWidth: 2,
      borderRadius: 5,
      //borderColor: '#000000',
      //bgColor: '#fff',
      padding: 5,
      display: 'ALWAYS',
      textAlign: 'center',
      scale:20,
    },
    // label: {
    //   content: 'label 文本',
    //   fontSize: 24,
    //   textAlign: 'center',
    //   borderWidth: 1,
    //   borderRadius: 5,
    //   bgColor: '#fff',
    //   padding: 5
    // }
  }
  var allMarkers = [normalCallout]
      //显示地图
     that.mapCtx = wx.createMapContext('myMap')
     var markers = allMarkers
     that.setData({
       markers,
       customCalloutMarkerIds: [1],
       latitude: res.data[0].latitude,
       longitude: res.data[0].longitude,
     })
    },
    fail: function(res) {
      console.log("失败",res);
    }
  })
  },
  //地图绑定函数
  
  markertap(e) {
    console.log('@@@ markertap', e)
  },
  callouttap(e) {
    console.log('@@@ callouttap', e)
  },
  labeltap(e) {
    console.log('@@@ labeltap', e)
  },
  //展示所有电影
  showAllFilm(){
    var that = this;
 wx.request({
   url: 'http://localhost:9000/wx_selectAllFilm', //本地服务器地址
   data: { //data中的参数值就是传递给后台的数据
   },
   method: 'get',
   header: {
     'content-type': 'application/json' //默认值
   },
   success: function(res) { //res就是接收后台返回的数据
     that.setData({
      showFilm: res.data
     })
     console.log(res.data);
   },
   fail: function(res) {
     console.log("失败",res);
   }
 })
},

//点击卡片电影查看场次
click_film(e){
  console.log("id",e.currentTarget.dataset.film_id)
  this.setData({
    id:e.currentTarget.dataset.film_id
  })
},

//选座
choose_seat(e){
  let session_id = e.currentTarget.dataset.session_id
  let session_price = e.currentTarget.dataset.session_price
  console.log(session_id,session_price)
  wx.navigateTo({
    url: '/pages/choose_seat/choose_seat?session_id='+session_id+'&'+'session_price='+session_price,
  })
},
})