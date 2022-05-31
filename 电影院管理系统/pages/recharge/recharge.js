let auto_recharge = ''
let user_id = ''
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    user_id = e.user_id
     //查找用户信息
     var that = this
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
        console.log('res',res)   
        //设置信息
        that.setData({
          user:res.data[0]
        })
      },
      fail: function(err) {
        }
        })
  },
  //查找用户信息
  getUser(){
     //查找用户信息
     var that = this
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
        console.log('res',res)   
        //设置信息
        that.setData({
          user:res.data[0]
        })
      },
      fail: function(err) {
        }
        })
  },
//充值
recharge(e){
 let recharge = e.currentTarget.dataset.value
 let current_money = parseInt(this.data.user.user_money,10) + parseInt(recharge,10) 
 var that = this;
 wx.showModal({
  title: '',
  content: '确定充值'+recharge+'元',
  success (res) {
  if (res.confirm) {
  console.log('用户点击确定')
  wx.request({
   url: 'http://localhost:9000/payUser_money', //本地服务器地址
   data: { //data中的参数值就是传递给后台的数据
    user_id:user_id,
    user_money:current_money
   },
   method: 'get',
   header: {
     'content-type': 'application/json' //默认值
   },
   success: function(res) { //res就是接收后台返回的数据
    that.getUser()
     console.log(res.data);
   },
   fail: function(res) {
     console.log("失败",res);
   }
 })
  } else if (res.cancel) {
  console.log('用户点击取消')
  }
  }
  })
},

//获取自定义充值
getAuto_recharge(e){
  auto_recharge = e.detail.value
},
//自定义充值
auto_recharge(){
  let current_money = parseInt(this.data.user.user_money,10) + parseInt(auto_recharge,10) 
  var that = this;
  wx.request({
    url: 'http://localhost:9000/payUser_money', //本地服务器地址
    data: { //data中的参数值就是传递给后台的数据
     user_id:user_id,
     user_money:current_money
    },
    method: 'get',
    header: {
      'content-type': 'application/json' //默认值
    },
    success: function(res) { //res就是接收后台返回的数据
     that.getUser()
      console.log(res.data);
      that.setData({
        auto_recharge:''
      })
    },
    fail: function(res) {
      console.log("失败",res);
    }
  })
}
})