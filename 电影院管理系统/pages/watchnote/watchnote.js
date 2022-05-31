let Charts = require('../../utils/wxcharts');
var piechart = null
var linechart = null
let user_id = ''
Page({
  data: {
    order:[],
  },
  onLoad:function(e){
    this.getlinechart()
    this.getpiechart()
  },

  getlinechart:function(){
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
              var timelist=[0,0,0,0,0,0,0,0,0,0,0,0]
              timelist.length=12
              for(var i=0;i<that.data.order.length;i++){
                if(that.data.order[i].order_date.substr(6,1)==1) timelist[0]++
                else if(that.data.order[i].order_date.substr(6,1)==2) timelist[1]++
                else if(that.data.order[i].order_date.substr(6,1)==3) timelist[2]++
                else if(that.data.order[i].order_date.substr(6,1)==4) timelist[3]++
                else if(that.data.order[i].order_date.substr(6,1)==5) timelist[4]++
                else if(that.data.order[i].order_date.substr(6,1)==6) timelist[5]++
                else if(that.data.order[i].order_date.substr(6,1)==7) timelist[6]++
                else if(that.data.order[i].order_date.substr(6,1)==8) timelist[7]++
                else if(that.data.order[i].order_date.substr(6,1)==9) timelist[8]++
                else if(that.data.order[i].order_date.substr(6,1)==10) timelist[9]++
                else if(that.data.order[i].order_date.substr(6,1)==11) timelist[10]++
                else if(that.data.order[i].order_date.substr(6,1)==12) timelist[11]++
                console.log('that.data.order['+i+']',that.data.order[i])
              }
              let max=0 //求表中最多时间段的人数，即数轴的最大值
              for(let i=0;i<12;i++){
                if(max<timelist[i]){
                  max=timelist[i]
                  let k=i
                }  
              }
              linechart = new Charts({
                canvasId: 'canvas1',
                dataPointShape: "circle",
                type: 'column',
                extra: {
                lineStyle:'curve' //线条的形状（弧形）
                },
                categories: ['1', '2', '3', '4', '5', '6','7','9','10','11','12'],
                series: [{
                  name: ' 观影记录',
                  data:[timelist[0],timelist[1],timelist[2],timelist[3],timelist[4],timelist[5],timelist[6],timelist[7],timelist[8],timelist[9],timelist[10],timelist[11]],//[5,1,2,1,0,2],//设置某一个值为null会出现断层
                  format: function (val) {
                  return val + '次';
                }
                }],
                yAxis: {
                title: '观影次数',
                format: function (val) {
                return val;
                },
                fontColor: "blue",
                titleFontColor: "red",
                min: 0,
                max:max,
                gridColor:"red"
              },
              width: 320,
              height: 300,
              dataLabel: true
              })
            },
            fail: function(res) {
              console.log("查找失败",res);
            }
          })
        }
      },
      fail: function(res) {
      }
    })
 },
 getpiechart:function(){
   piechart = new Charts({
     canvasId: 'canvas2',
     type: 'pie',
     series: [{ name: '一班', data: 50 }, { name: '二班', data: 30 }, { name: '三班', data: 20 }, { name: '四班', data: 18 }, { name: '五班', data: 8 }],
     width: 300,
     height: 200,
     dataLabel: true,
   })
 },
})
