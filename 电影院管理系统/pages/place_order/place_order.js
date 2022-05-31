let session = {}
let currentSeat = ''
let user_money = ''
let cinema_income = ''
let id = ''
Page({

  /**
   * 页面的初始数据
   */
  data: {
    session:{},
    newOrder:{},
    user:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
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
     console.log('场次id',e.session_id,'选择座位号',e.currentSeat)
     currentSeat = e.currentSeat
     this.setData({
      newOrder:{
        currentSeat:e.currentSeat
      }
    })
    //根据场次id，即session_id查找场次信息保存在session里
    var that = this;
    wx.request({
      url: 'http://localhost:9000/wx_selectOneSession', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
        session_id:e.session_id
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        session = res.data[0]
        that.setData({
          session: session,
        })
        console.log('res.data',res.data);
        console.log('session',that.data.session);
      },
      fail: function(res) {
        console.log("失败",res);
      }
    })
  },
//付款
pay(){
 wx.showModal({
  title: '提示',
  content: '付款'+this.data.session.session_price+'元',
  success (res) {
  if (res.confirm) {
     //随机生成一个订单id
     let order_id=Math.floor(Math.random()*10000 + 99999);
     order_id=order_id.toString()
     var that = this;
     //查询余额
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
        console.log(res.data[0].user_money,session.session_price)
        if(res.data[0].user_money<session.session_price){
          wx.showToast({
            title: '余额不足',
            duration: 1000
          })
        }
        else{
          console.log(parseInt(res.data[0].user_money,10),parseInt(session.session_price,10))
          user_money = parseInt(res.data[0].user_money,10)-parseInt(session.session_price,10) 
          console.log(typeof(user_money))
          user_money = user_money.toString()
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
              user_id:id,
              user_money:user_money
            },
            method: 'get',
            header: {
              'content-type': 'application/json' //默认值
            },
            success: function(res) { //res就是接收后台返回的数据
              console.log('res.data',res.data);
     //生成订单，向数据库添加
     wx.request({
      url: 'http://localhost:9000/addNewOrder', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
        user_id:id,
        order_id:order_id,
        order_date:session.session_date,
        order_time:session.session_time,
        food_order_id:'',
        film_id:session.film_id,
        session_id:session.session_id,
        seat_number:currentSeat,
        total_price:session.session_price
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        setTimeout(function () {
          wx.showToast({
            title: '下单成功！',
            duration:800
          })
        }, 1000);
        setTimeout(function () {
          wx.reLaunch({
            url: '/pages/order/order?user_id='+id,
          })
        }, 2000);

        //查找电影院信息
        wx.request({
          url: 'http://localhost:9000/selectOneCinema', //本地服务器地址
          data: { //data中的参数值就是传递给后台的数据
            cinema_id:session.cinema_id
          },
          method: 'get',
          header: {
            'content-type': 'application/json' //默认值
          },
          success: function(res) { //res就是接收后台返回的数据
          cinema_income = parseInt(res.data[0].cinema_income,10)
          cinema_income = cinema_income + parseInt(session.session_price,10) 
          //电影院收益
          wx.request({
            url: 'http://localhost:9000/wx_cinemaIncome', //本地服务器地址
            data: { //data中的参数值就是传递给后台的数据
              cinema_id:session.cinema_id,
              cinema_income:cinema_income
            },
            method: 'get',
            header: {
              'content-type': 'application/json' //默认值
            },
            success: function(res) { //res就是接收后台返回的数据
            
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
      fail: function(res) {
        wx.showToast({
          title: '下单失败',
          duration:2000
        })
        console.log("下单失败",res);
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