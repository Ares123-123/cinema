let user_id = ''
Page({

  /**
   * 页面的初始数据
   */
  data: {
    transfer_order:[],
    order:[],
    sameSessionOrder:[], //与该用户相同场次的用户
    user_id:''
  },
  onLoad:function (options) {
    var that = this
    wx.getStorage({
      key: 'userInfo',
      success: function(res) {
        console.log('res.data',res.data)
        if(res.data){
          user_id = res.data
          that.setData({
            user_id:res.data
          })
          //查找用户信息
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
              //获取与该用户相同场次的用户
            that.getSameSession_order(user_id) 
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
  onShow: function (options) {
    this.get_transfer_order()
  },
 //获取订单
 get_allOrder(e){
  var that = this
  wx.getStorage({
    key: 'userInfo',
    success: function(res) {
      console.log('res.data',res.data)
      if(res.data){
        user_id = res.data
        wx.request({
          url: 'http://localhost:9000/selectOneUser_order', //本地服务器地址
          data: { //data中的参数值就是传递给后台的数据
            user_id:user_id
          },
          method: 'get',
          header: {
            'content-type': 'application/json' //默认值
          },
          success: function(res) { //res就是接收后台返回的数据
            that.setData({
              order: res.data,
            })
            console.log('res.data',res.data);
          },
          fail: function(res) {
            console.log("查找失败",res);
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

 //获取相同场次影友信息
 getSameSession_order(user_id){
  var that = this;
  wx.request({
    url: 'http://localhost:9000/wx_select_sameSession_order', //本地服务器地址
    data: { //data中的参数值就是传递给后台的数据
      user_id:user_id
    },
    method: 'get',
    header: {
      'content-type': 'application/json' //默认值
    },
    success: function(res) { //res就是接收后台返回的数据
      that.setData({
        sameSessionOrder: res.data
      })
      console.log('相同场次用户',res.data);
    },
    fail: function(res) {
      console.log("失败",res);
    }
  })
 },

  //获取转让电影票表单
  get_transfer_order(){
    var that = this;
    wx.request({
      url: 'http://localhost:9000/wx_selectAllTransfer_order', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        that.setData({
          transfer_order: res.data
        })
        console.log(res.data);
      },
      fail: function(res) {
        console.log("失败",res);
      }
    })
  },
  //购买转让电影票
  place_order(e){
    let ticket = e.currentTarget.dataset.ticket
    console.log('ticket',ticket)
    var that = this;
    wx.showModal({
      title: '提示',
      content: '付款'+ticket.current_price+'元',
      success (res) {
      if (res.confirm) {
         //随机生成一个订单id
         let order_id=Math.floor(Math.random()*10000 + 99999);
         order_id=order_id.toString()
         //查询余额
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
            if(res.data[0].user_money<ticket.current_price){
              wx.showToast({
                title: '余额不足',
                duration: 1500
              })
            }
            else{
              console.log(parseInt(res.data[0].user_money,10),parseInt(ticket.current_price,10))
              let user_money = parseInt(res.data[0].user_money,10)-parseInt(ticket.current_price,10) 
              console.log(typeof(user_money))
              setTimeout(function () {
                wx.showToast({
                  icon:'loading',
                  title: '支付中',
                  duration:1000
                })
              }, 800);
              wx.request({
                url: 'http://localhost:9000/payUser_money', //本地服务器地址
                data: { //data中的参数值就是传递给后台的数据
                  user_id:user_id,
                  user_money:user_money
                },
                method: 'get',
                header: {
                  'content-type': 'application/json' //默认值
                },
                success: function(res) { //res就是接收后台返回的数据
                  console.log('res.data',res.data);
         //生成订单，向数据库添加
    //随机生成订单id
    let order_id=Math.floor(Math.random()*10000 + 99999);
    //生成订单
  wx.request({
    url: 'http://localhost:9000/addNewOrder', //本地服务器地址
    data: { //data中的参数值就是传递给后台的数据
      user_id:user_id,
      order_id:order_id,
      order_date:ticket.order_date,
      order_time:ticket.order_time,
      food_order_id:'',
      film_id:ticket.film_id,
      session_id:ticket.session_id,
      seat_number:ticket.seat_number,
      total_price:ticket.current_price
    },
    method: 'get',
    header: {
      'content-type': 'application/json' //默认值
    },
    success: function(res) { //res就是接收后台返回的数据
      wx.showToast({
        title: '转让成功',
      })
      console.log(res.data);
      //删除该转让订单
      wx.request({
        url: 'http://localhost:9000/wx_deleteOneTransfer_order', //本地服务器地址
        data: { //data中的参数值就是传递给后台的数据
          transfer_order_id:ticket.transfer_order_id
        },
        method: 'get',
        header: {
          'content-type': 'application/json' //默认值
        },
        success: function(res) { //res就是接收后台返回的数据
          console.log('删除成功',res);
          //获取转让用户信息
          wx.request({
            url: 'http://localhost:9000/selectOne_User', //本地服务器地址
            data: { //data中的参数值就是传递给后台的数据
              user_id:ticket.user_id
            },
            method: 'get',
            header: {
              'content-type': 'application/json' //默认值
            },
            success: function(res) { //res就是接收后台返回的数据
              console.log(res.data);
              let transfer_user_id = res.data[0].user_id
              let user_money = parseInt(user_money,10) + parseInt(ticket.current_price,10)
              wx.request({
                url: 'http://localhost:9000/payUser_money', //本地服务器地址
                data: { //data中的参数值就是传递给后台的数据
                  user_id:transfer_user_id,
                  user_money:user_money
                },
                method: 'get',
                header: {
                  'content-type': 'application/json' //默认值
                },
                success: function(res) { //res就是接收后台返回的数据
                  console.log(res.data);
                    //转让的用户获取收益
                  that.get_transfer_order()
                  wx.navigateTo({
                    url: '/pages/order/order?user_id='+user_id,
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
            wx.showToast({
              title: '取消支付',
            })
            }
          })
        }
      }
      })
        }
        else if(res.cancel){
        }
      }
    })
  }
})