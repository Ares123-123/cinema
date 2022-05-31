//获取应用实例
var app = getApp();
let session_id = ''
let session_price = ''
let seat = []
Page({

  data: {
   seat:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    seat = []
    session_id = e.session_id
    this.setData({
      session_price:session_price,
      seat:[]
    })
    console.log(session_id)
    console.log(seat)
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
        console.log(res)
        for(let index=1;index<=res.data[0].hall_seat_count;index++){
          console.log(index+'号座');
            //判断是否是你的座位
            console.log('判断是否是你的座位');
            wx.request({
              url: 'http://localhost:9000/wx_determine_isYourSeat', //本地服务器地址
              data: { //data中的参数值就是传递给后台的数据
                user_id:'1710704113',
                session_id:session_id,
                seat_number:index
              },
              method: 'get',
              header: {
                'content-type': 'application/json' //默认值
              },
              success: function(res) { //res就是接收后台返回的数据
                if(res.data!=''){
                  console.log(index,'是你的');
                  seat.push({number:index,state:2}) //座位是你的
                }else{
                  seat.push({number:index,state:0}) //座位不是 你的
                }
                that.setData({
                  seat:seat
                })
              },
              fail: function(res) {
                console.log("失败",res);
              }
            })

          //判断是否售出
          console.log('判断是否售出');
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
                if(seat[index-1].state!=2){
                  seat[index-1].state=1  //座位已售出
                  console.log(index,'售出');
                }
              }else{
                seat[index-1].state=0
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
})