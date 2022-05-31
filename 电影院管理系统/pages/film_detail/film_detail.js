const { Time } = require('../../utils/util');
var util = require('../../utils/util');
let film_id = ''
let film_name = ''
let comment_content = ''
let id = ''
Page({
  data:{
   film_detail:{},
   showComment:[], //展示影评
   comment:[],   //写影评
   user:{},
   date:'',
   time:''
  },
  onLoad:function(e){
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

    film_id = e.film_id
    console.log("查询电影表单",film_id)
    var that = this;
    wx.request({
      url: 'http://localhost:9000/wx_selectOneFilm', //本地服务器地址
      data: {
        film_id:film_id
      },//data中的参数值就是传递给后台的数据
      method: 'get',
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function(res) { //res就是接收后台返回的数据
        that.setData({
          film_detail: res.data[0],
        })
        console.log('res',res.data);
      },
      fail: function(res) {
        console.log("失败",res);
      }
    })

     //展示影评
     this.showComment()

    //设置当前时间
    setInterval(function () {
      that.setData({
        date: util.formatDate(new Date()),
        time: util.formatTime(new Date())
      });
    }, 1000);
  },
    buy_ticket(){
      film_id=this.data.film_detail.film_id,
      film_name=this.data.film_detail.film_name
      wx.navigateTo({
        url: '/pages/film_cinema/film_cinema?film_id='+film_id+'&film_name='+film_name,
      })
    },
    //展示影评
    showComment(){
      var that = this;
      wx.request({
        url: 'http://localhost:9000/wx_showOneFilm_Comment', //本地服务器地址
        data: {
          //data中的参数值就是传递给后台的数据
          film_id:film_id
        },
        method: 'get',
        header: {
          'content-type': 'application/json' //默认值
        },
        success: function(res) { //res就是接收后台返回的数据
          that.setData({
            showComment:res.data
          })
          console.log("影评",res.data);
        },
        fail: function(res) {
          console.log("失败",res);
        }
      })
    },

    //写影评
    get_comment(e){
      comment_content = e.detail.value
      console.log("影评",comment_content)
    },
    save(){
        if(comment_content!=''){
          let password=Math.floor(Math.random()*10000 + 99999);
          password=password.toString()
          console.log(this.data.comment)
         //this.data.comment.push({comment_content:comment_content,film_id:film_id})
         var that = this;
         wx.request({
           url: 'http://localhost:9000/addOneComment', //本地服务器地址
           data: {
             //data中的参数值就是传递给后台的数据
             user_id:id,
             comment_id:password,
             comment_content:comment_content,
             comment_date:this.data.date,
             comment_time:this.data.time,
             film_id:film_id,
             approval_state:'未审核'
           },
           method: 'get',
           header: {
             'content-type': 'application/json' //默认值
           },
           success: function(res) { //res就是接收后台返回的数据
             console.log('提交影评成功',res.data);
             wx.showToast({
               title: '待审核',
             })
             comment_content=''
             that.setData({
              comment_content:'',
              comment:[]
             })
             that.showComment();
           },
           fail: function(res) {
             console.log("失败",res);
           }
         })
      }
    },
    
 /*滑动组件*/
 swichTab: function (e) {
  var that = this;
  if (this.data.currentTab === e.target.dataset.current) {
    return false;
  } else {
    var id = e.target.dataset.current;
    that.setData({
      currentTab: id
    })
  }
},
bindTouch: function (e) {
  var that = this;
  var id = e.detail.current;
  that.setData({
    currentTab: id
  });
},
})