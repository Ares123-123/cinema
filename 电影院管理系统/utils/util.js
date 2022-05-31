/**获取当前系统时间*/
const formatTime = date => {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('-')+' '+[hour, minute, second].map(formatNumber).join(':')}`
}

/**获取当前系统日期 */
const formatDate = date => {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()
 
  return [year, month, day].map(formatNumber).join('-')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}

module.exports = {
  formatDate,
  formatTime,
}
