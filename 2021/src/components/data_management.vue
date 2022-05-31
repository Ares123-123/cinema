<template>
    <div>
        <span>订单数据统计</span>
        <div id="myChart" style="width:1000px; height:500px;"></div>
    </div>
</template>

<script>
    import axios from 'axios';
    let filmName = []
    let filmOrder = []
    export default {
        name: "data_management",
        mounted(){
            // 在通过mounted调用即可
            this.getFilm()
        },
        data() {
            return {
                filmData: [],
                OrderData:[],
                filmName:[],
                filmOrder:[]
            }
        },
        methods: {
            drawLine() {
                console.log('filmName',filmName);
                console.log('filmOrder',filmOrder);
                let echarts = require('echarts');
                // 基于准备好的dom，初始化echarts实例
                let myChart = echarts.init(document.getElementById('myChart'))
                // 绘制图表
                myChart.setOption({
                    title: {text: ''},
                    tooltip: {},
                    xAxis: {
                        type: 'value',
                        // x轴的字体样式
                        axisLabel: {
                            show: true,    //这行代码控制着坐标轴x轴的文字是否显示
                            textStyle: {
                                color: 'black',   //x轴上的字体颜色
                                fontSize: '16'    // x轴字体大小
                            }
                        },
                        // 控制网格线是否显示
                        splitLine: {
                            show: true,   // 网格线是否显示
                            //  改变样式
                            lineStyle: {
                                color: '#ccc', // 修改网格线颜色
                                width: 1
                            }
                        },
                        // x轴的颜色和宽度
                        axisLine: {
                            lineStyle: {
                                color: '#ccc', // x坐标轴的轴线颜色
                                width: 10,      //这里是坐标轴的宽度,可以去掉
                            }
                        }
                    },
                    yAxis: {
                        type: 'category',
                        data: filmName,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: 'black',
                                fontSize: '10'
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                color: 'black', // x坐标轴的轴线颜色
                                width: 1,      //这里是坐标轴的宽度,可以去掉
                            }
                        }
                    },
                    series: [{
                        "name": "订单数",
                        "type": "bar",
                        data:filmOrder,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true, //开启显示
                                    position: 'right', //在右方显示
                                    textStyle: { //数值样式
                                        color: '#f5f',
                                        fontSize: 16
                                    }
                                }
                            }
                        },
                    }]
                });
            },

            //查看所有电影信息
            getFilm(){
                filmName = []
                filmOrder = []
                console.log('你点击了查看');
                axios.post('http://localhost:9000/selectAllFilm').then(res=>{
                    this.filmData = res.data;
                    console.log('电影',res.data)
                    this.filmName.length = res.data.length
                    for(let i =0;i<this.filmData.length;i++){
                        this.filmName[i]=this.filmData[i].film_name
                    }
                    this.filmName = this.filmName
                    this.get_filmOrder()
                }).catch((err)=>{
                    console.log(err);
                });
            },
            get_filmOrder(){
                axios.post('http://localhost:9000/selectAllFilm_Order').then(res=>{
                    this.OrderData = res.data;
                    console.log('订单',this.OrderData)
                        this.filmOrder=[]
                        this.filmOrder.length = this.filmData.length
                        for(let i = 0;i<this.filmOrder.length;i++){
                            this.filmOrder[i]= 0
                        }
                        console.log('filmOrder',this.filmOrder);
                        for(let i = 0;i<this.OrderData.length;i++){
                            for(let j = 0;j<this.filmName.length;j++){
                                if(this.OrderData[i].film_name == this.filmName[j]){
                                    this.filmOrder[j]++;
                                }
                            }
                        }
                        for(let i = 0;i<this.filmName.length;i++){
                            filmName.push(this.filmName[i])
                        }
                        for(let i = 0;i<this.filmOrder.length;i++){
                            filmOrder.push(this.filmOrder[i])
                        }
                        console.log('this.filmName',this.filmName);
                        console.log('this.filmOrder',this.filmOrder);
                        console.log('filmName',filmName);
                        console.log('filmOrder',filmOrder);
                        this.drawLine()
                }).catch((err)=>{
                    console.log(err);
                });
            },
        }
    }
</script>

<style scoped>

</style>