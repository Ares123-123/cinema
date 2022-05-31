let user_id = ''
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    user_id = e.user_id
    console.log('用户id',user_id)
    var that = this
    //查找所有评论
    wx.request({
      url: 'http://localhost:9000/wx_showOneUser_Comment', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
        user_id:user_id
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        console.log(res)
        that.setData({
        comment:res.data
       })
      },
      fail: function(err) {
       
      }
    })
  },
  
})