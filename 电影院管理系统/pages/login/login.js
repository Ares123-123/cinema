let user_id = ''
let username = ''
let password = ''
let validatePassword = ''
let user_money = ''
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{}
  },
  get_username(e){
    username = e.detail.value
  },

  get_password(e){
    password = e.detail.value
  },

  //登录
  login(e){
    var that = this;
    if(username&&password){
      wx.request({
        url: 'http://localhost:9000/userLogin', //本地服务器地址
        data: { //data中的参数值就是传递给后台的数据
          user_name:username,
          user_password:password,
        },
        method: 'get',
        header: {
          'content-type': 'application/json' //默认值
        },
        success: function(res) { //res就是接收后台返回的数据
          console.log('本地缓存',res.data);
          if(res.data.length!=0){
            wx.setStorage({
              key: 'userInfo',
              data: res.data[0].user_id
          })
          wx.showToast({
            title: '登录成功',
            icon: 'success',
            duration: 1000
            })
          setTimeout(function () {
            wx.switchTab({
              url: '/pages/me/me',
            })
             }, 1000)
          }
         else{
          wx.showModal({
            title: '提示',
            content: '用户名或密码不正确',
            success (res) {
            if (res.confirm) {
            console.log('用户点击确定')
            } else if (res.cancel) {
            console.log('用户点击取消')
            }
            }
            })
         }  
        },
        fail: function(res) {
          console.log("失败",res);
        }
      })
    }
   else{
     wx.showModal({
      title: '提示',
      content: '请输入用户名和密码',
      success (res) {
      if (res.confirm) {
      console.log('用户点击确定')
      } else if (res.cancel) {
      console.log('用户点击取消')
      }
      }
      })
   }
  },
  //注册
  signup(){
    wx.navigateTo({
      url: '/pages/signup/signup',
    })
  }
})