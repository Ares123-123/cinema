let id = ''
Page({
  data: {
    user:{},
    showModalStatus: false,
  },
  onShow:function(e){
    var that = this
    wx.getStorage({
      key: 'userInfo',
      success: function(res) {
        console.log('res.data',res.data)
        if(res.data){
          id = res.data
          //查找用户信息
          wx.request({
            url: 'http://localhost:9000/selectOne_User', //本地服务器地址
            data: { //data中的参数值就是传递给后台的数据
              user_id:id
            },
            method: 'get',
            header: {
              'content-type': 'application/json' //默认值
            },
            success: function(res) { //res就是接收后台返回的数据
              console.log('id',id)
              console.log('res',res)   
              //设置信息
              that.setData({
                user:res.data[0]
              })
            },
            fail: function(err) {
              }
              })
        }
        else{
          wx.navigateTo({
            url: '/pages/login/login',
          })
        }
      },
      fail: function(res) {
      }
    })
  },
  //充值
  recharge(){
    wx.navigateTo({
      url: '/pages/recharge/recharge?user_id='+id,
    })
  },
  WatchBoard:function(){
    wx.navigateTo({
      url: '/pages/watchnote/watchnote',
    })
  },

  //查看订单页
  click_order:function(e){
    let user_id = e.currentTarget.dataset.user_id;
    wx.navigateTo({
      url: '/pages/order/order?user_id='+user_id,
    })
  },

  //查看评论
  comment_detail(e){
    let user_id = e.currentTarget.dataset.user_id;
    wx.navigateTo({
      url: '/pages/comment_detail/comment_detail?user_id='+user_id,
    })
  },
  //退出登录
  quit_login(){
    wx.setStorage({
      key: 'userInfo',
      data: ''
  })

  wx.getStorage({
    key: 'userInfo',
    success: function(res) {
      if(res.data==''){
        console.log(res)
        wx.navigateTo({
        url: '/pages/login/login',
      })
      }
    },
    fail: function(res) {
    }
  })
  },
  //修改密码
  change_password(){
    wx.navigateTo({
      url: '/pages/change_password/change_password',
    })
  }
})