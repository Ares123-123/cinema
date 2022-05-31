let film_id=''
let film_name=''
Page({
  data: {
    cinema:[]
  },

  onLoad: function (e) {
    console.log(e)
     film_id=e.film_id
     film_name=e.film_name
     this.setData({
       film_name:film_name
     })
     //查找上映该电影的电影院
     var that = this;
     wx.request({
       url: 'http://localhost:9000/wx_selectOneFilm_Cinema', //本地服务器地址
       data: {
         //data中的参数值就是传递给后台的数据
         film_id:film_id
       },
       method: 'get',
       header: {
         'content-type': 'application/json' //默认值
       },
       success: function(res) { //res就是接收后台返回的数据
         that.setData({
           cinema:res.data
         })
         console.log("上映该电影的电影院",res.data);
       },
       fail: function(res) {
         console.log("失败",res);
       }
     })
  },

  //点击电影院
  click_cinema(e){
    let cinema_id = e.currentTarget.dataset.cinema_id
    console.log('cinema_id',cinema_id)
    wx.redirectTo({
      url: '/pages/cinema_detail/cinema_detail?cinema_id='+cinema_id,
    })
  }
})