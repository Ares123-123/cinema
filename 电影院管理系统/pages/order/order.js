let order_id=''
let user_id = ''
let transfer_price = ''
Page({
  data: {
    order:[],
    showModalStatus: false,
  },
 onLoad:function(e){
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
 //获取订单
 get_allOrder(e){
  let user_id = e  
  console.log(user_id)
  var that = this;
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
 },

 //查看详情
 order_detail(e){
 let session_id = e.currentTarget.dataset.session_id
 console.log(session_id)
 wx.navigateTo({
   url: '/pages/order_detail/order_detail?session_id='+session_id,
 })
 },
//退票
quit_order(e){
  var that = this
  let current_order = e.currentTarget.dataset.order
  wx.showModal({
    title: '',
    content: '是否要退票',
    success (res) {
    if (res.confirm) {
    console.log('用户点击确定')
    wx.request({
      url: 'http://localhost:9000/wx_quitUserOrder', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
       order_id:current_order.order_id
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        //退款
        //需要40%的手续费
        let refund = parseInt(current_order.total_price,10)*0.6
        //用户退款
        wx.request({
          url: 'http://localhost:9000/wx_refundUser', //本地服务器地址
          data: { //data中的参数值就是传递给后台的数据
            user_id:current_order.user_id,
            refund:refund
          },
          method: 'get',
          header: {
            'content-type': 'application/json' //默认值
          },
          success: function(res) { //res就是接收后台返回的数据
            console.log(res.data);
            wx.showToast({
              title: '退票成功',
              icon: 'success',
              duration: 600
            })
          },
          fail: function(res) {
            console.log("失败",res);
          }
        })
        //展示
        that.get_allOrder(user_id)
        wx.showToast({
         title: '退票成功',
         duration:1000
       })
      },
      fail: function(res) {
        wx.showToast({
          title: '退票失败',
          duration:1000
        })}
        })
    } else if (res.cancel) {
    console.log('用户点击取消')
    }
    }})},

  //取票
  get_ticket(e){
   let ticket_code = Math.floor(Math.random()*100000 + 999999);
   wx.showModal({
    title: '取票码',
    content: '取票码为'+ticket_code,
    success (res) {
    if (res.confirm) {
    console.log('用户点击确定')
   
    } else if (res.cancel) {
    console.log('用户点击取消')
    }
    }
    })
  },

  //确认订单
  confirm_order(e){
    let current_order = e.currentTarget.dataset.order
    console.log(current_order)
    var that = this

    wx.showModal({
      title: '提示',
      content: '订单是否完成?',
      success (res) {
      if (res.confirm) {
      console.log('用户点击确定')
      //修改订单状态
 wx.request({
  url: 'http://localhost:9000/wx_Update_oneOrderState', //本地服务器地址
  data: { //data中的参数值就是传递给后台的数据
    order_id:current_order.order_id,
    state:'已完成'
  },
  method: 'get',
  header: {
    'content-type': 'application/json' //默认值
  },
  success: function(res) { //res就是接收后台返回的数据
    console.log(res.data);
    that.get_allOrder(user_id)
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
  //转让电影票弹窗
  powerDrawer: function (e) { 
    var currentStatu = e.currentTarget.dataset.statu; 
    this.util(currentStatu) 
   }, 
   util: function(currentStatu){ 
    /* 动画部分 */
    // 第1步：创建动画实例  
    var animation = wx.createAnimation({ 
     duration: 200, //动画时长 
     timingFunction: "linear", //线性 
     delay: 0 //0则不延迟 
    }); 
      
    // 第2步：这个动画实例赋给当前的动画实例 
    this.animation = animation; 
    
    // 第3步：执行第一组动画 
    animation.opacity(0).rotateX(-100).step(); 
    
    // 第4步：导出动画对象赋给数据对象储存 
    this.setData({ 
     animationData: animation.export() 
    }) 
      
    // 第5步：设置定时器到指定时候后，执行第二组动画 
    setTimeout(function () { 
     // 执行第二组动画 
     animation.opacity(1).rotateX(0).step(); 
     // 给数据对象储存的第一组动画，更替为执行完第二组动画的动画对象 
     this.setData({ 
      animationData: animation 
     }) 
       
     //关闭 
     if (currentStatu == "close") { 
      this.setData( 
       { 
        showModalStatus: false
       } 
      ); 
     } 
    }.bind(this), 200) 
     
    // 显示 
    if (currentStatu == "open") { 
     this.setData( 
      { 
       showModalStatus: true
      } 
     ); 
    } 
   },

   //获取自定义价格
   get_transfer_price(e){
    transfer_price = e.detail.value
   },
   //转让电影票
   transfer_order(e){
    let transfer_order_id = Math.floor(Math.random()*10000 + 99999);
    let current_order = e.currentTarget.dataset.order
    console.log('current_order',current_order)
    var that = this;
    if(transfer_price > current_order.total_price){
      wx.showModal({
        title: '提示',
        content: '转让定价不能高于原价！',
        success (res) {
        if (res.confirm) {
        console.log('用户点击确定')
        } else if (res.cancel) {
        console.log('用户点击取消')
        }
        }
        })
    }else{
      wx.request({
        url: 'http://localhost:9000/wx_addTransfer_order', //本地服务器地址
        data: { //data中的参数值就是传递给后台的数据
          transfer_order_id:transfer_order_id,
          order_date:current_order.order_date,
          order_time:current_order.order_time,
          film_id:current_order.film_id,
          user_id:current_order.user_id,
          session_id:current_order.session_id,
          seat_number:current_order.seat_number,
          original_price:current_order.total_price,
          current_price:transfer_price
        },
        method: 'get',
        header: {
          'content-type': 'application/json' //默认值
        },
        success: function(res) { //res就是接收后台返回的数据
          console.log('添加到转让',res.data);
          //删除原有订单
    wx.request({
      url: 'http://localhost:9000/wx_quitUserOrder', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
        order_id:current_order.order_id
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        console.log(res.data);
        that.get_allOrder(user_id)
        wx.switchTab({
          url: '/pages/inform/inform',
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
    }
   }
})