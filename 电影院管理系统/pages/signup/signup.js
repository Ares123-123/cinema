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
  
  },

  
  onLoad: function (options) {

  },

  get_username(e){
    username = e.detail.value
  },

  get_password(e){
    password = e.detail.value
  },

  get_validatePassword(e){
    validatePassword = e.detail.value
  },

  //注册
  signUp(){
    if(password==validatePassword){
      //随机生成用户id
      user_id = Math.floor(Math.random()*10000 + 99999)
      user_id = user_id.toString()
      var that = this;
      wx.request({
        url: 'http://localhost:9000/userSignUp', //本地服务器地址
        data: { //data中的参数值就是传递给后台的数据
          user_id:user_id,
          user_name:username,
          user_password:password,
          user_money:'0'
        },
        method: 'get',
        header: {
          'content-type': 'application/json' //默认值
        },
        success: function(res) { //res就是接收后台返回的数据
          console.log(res.data);
          wx.showToast({
            title: '注册成功',
            icon: 'success',
            duration: 600
          })
   
       setTimeout(function () {
        wx.switchTab({
          url: '/pages/me/me',
        })
         }, 800)
        },
        fail: function(res) {
          console.log("失败",res);
          wx.showToast({
            title: '注册失败',
            icon: 'none',
            duration: 800
          })
        }
      })
    }
    else{
      wx.showModal({
        title: '提示',
        content: '两次输入密码不一致！',
        success (res) {
        if (res.confirm) {
        console.log('用户点击确定')
        } else if (res.cancel) {
        console.log('用户点击取消')
        }
        }
        })
    }
  }
})