App({
  onLaunch:function(){
    wx.cloud.init({
      env:'demo2021-4g5fsqwj6c86d6a8',
      traceUser:true //用户访问记录到用户管理中
    })
  }
})