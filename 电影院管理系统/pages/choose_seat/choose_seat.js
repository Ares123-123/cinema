//获取应用实例
var app = getApp();
let session_id = ''
let session_price = ''
let session = {}
let seat = []
Page({

  data: {
   seat:[],
   currentSeat:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    seat = []
    session_id = e.session_id
    session_price = e.session_price
    this.setData({
      session_price:session_price
    })
    console.log(session_id,session_price)
    console.log(seat)

    //展示座位
    this.showSeat(session_id)
  },

  //展示座位情况
  showSeat:function(session_id){
    var that = this;
    wx.request({
      url: 'http://localhost:9000/wx_selectOneSession_Hall', //本地服务器地址
      data: { //data中的参数值就是传递给后台的数据
        session_id:session_id
      },
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        session = res.data[0]
        that.setData({
          session: session,
          film_name:res.data[0].film_name
        })
        console.log('session',session);
        for(let index=1;index<=res.data[0].hall_seat_count;index++){
          wx.request({
            url: 'http://localhost:9000/wx_determine_seat_isSell', //本地服务器地址
            data: { //data中的参数值就是传递给后台的数据
              session_id:session_id,
              seat_number:index
            },
            method: 'get',
            header: {
              'content-type': 'application/json' //默认值
            },
            success: function(res) { //res就是接收后台返回的数据
                if(res.data!=''){
                  console.log('座位',res.data[0])
                  if(res.data[0].state=='待取票'){
                    console.log(index+'已售待取票'+' ')
                    seat.push({number:index,state:1}) //座位已售出
                  }
              }else{
                seat.push({number:index,state:0}) //座位可选
              }
              that.setData({
                seat:seat
              })
            },
            fail: function(res) {
              console.log("失败",res);
            }
          })
        } 
      },
      fail: function(res) {
        console.log("失败",res);
      }
    })
  },
  //选中座位
  chooseSeat(e) {
    let number = parseInt(e.currentTarget.dataset.number,10);
    console.log(number);
    console.log('seat',seat);  
    //设置当前样式
    if(seat[number-1].state==0){
      this.setData({
        currentSeat: number,
        myseat:number
      })
      console.log('currentSeat',this.data.currentSeat)
    } 
  },

  //跳转到下单界面
  place_order(){
    let currentSeat=this.data.currentSeat
    wx.redirectTo({
      url: '/pages/place_order/place_order?session_id='+session.session_id+'&currentSeat='+currentSeat,
    })
  }

})