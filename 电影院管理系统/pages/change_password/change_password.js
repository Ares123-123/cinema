let user_id = ''
let new_password = ''
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user:{}
  },


  onShow: function (e) {
    var that = this
    wx.getStorage({
      key: 'userInfo',
      success: function(res) {
        console.log('res.data',res.data)
          user_id = res.data
        wx.request({
          url: 'http://localhost:9000/selectOne_User', //本地服务器地址
          data: { //data中的参数值就是传递给后台的数据
            user_id:user_id
          },
          method: 'get',
          header: {
            'content-type': 'application/json' //默认值
          },
          success: function(res) { //res就是接收后台返回的数据
            that.setData({
              user: res.data[0],
            })
            console.log('user',that.data.user);
          },
          fail: function(res) {
            console.log("失败",res);
          }
        })
      },
      fail: function(res) {
      }
    })
  },
 //获取修改密码
 get_new_password(e){
   new_password = e.detail.value
   console.log(new_password)
 },
 //确定修改
 change_password(){
  console.log('新密码',new_password)
  var that = this;
  wx.getStorage({
    key: 'userInfo',
    success: function(res) {
      console.log('res.data',res.data)
        user_id = res.data
        wx.request({
          url: 'http://localhost:9000/wx_change_password', //本地服务器地址
          data: { //data中的参数值就是传递给后台的数据
            user_id:user_id,
            user_password:new_password
          },
          method: 'get',
          header: {
            'content-type': 'application/json' //默认值
          },
          success: function(res) { //res就是接收后台返回的数据
            console.log(res.data,'修改成功');
            wx.request({
              url: 'http://localhost:9000/selectOne_User', //本地服务器地址
              data: { //data中的参数值就是传递给后台的数据
              },
              method: 'get',
              header: {
                'content-type': 'application/json' //默认值
              },
              success: function(res) { //res就是接收后台返回的数据
               wx.navigateBack({
                 delta: 0,
               })
              },
              fail: function(res) {
                console.log("失败",res);
              }
            })
          },
          fail: function(res) {
            console.log("失败",res);
          }
        })
    },
    fail: function(res) {
    }
  })
 }
})