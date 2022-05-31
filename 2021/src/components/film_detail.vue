<template>
    <div style="display: inline-flex">
        <div style="width:50%;display: block">
            <img :src="'data:image/png;base64,'+this.listQuery[0].film_picture_url" class="film_picture">
        </div>
        <div style="width:50%;display: block">
            <span>电影名：{{this.listQuery[0].film_name}}</span>
            <span>排名：{{this.listQuery[0].film_rank}}</span>
            <span>评分：{{this.listQuery[0].film_score}}</span>
            <span>类型：{{this.listQuery[0].film_style}}</span>
            <span>
            <span class="introduction">
                <i class="el-icon-bell"></i>
                简介：{{this.listQuery[0].film_introduction}}</span>
            </span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "film_detail",
        data(){
            return{
                listQuery: {
                    id:'',
                    film_name: '',
                    film_rank:'',
                    film_score:'',
                    film_style:'',
                    film_introduction:''
                }
            }
        },
        //Vue生命周期
        mounted:function(){
            // 如果是跳转来的，则接受初始化参数
            if (this.$route.query) {
                console.log('传参',this.$route.query)
                this.listQuery.film_id=this.$route.query.film_id
                //去film表查询
                this.$http.post("http://localhost:9000/selectOneFilm",this.listQuery).then(res=>{
                    console.log("查找电影信息",res.data);
                    this.listQuery=res.data;
                    console.log(this.listQuery);
                }).catch(err=>{
                    alert('查找失败!')
                })
            }
        },
        methods:{

        }
    }
</script>

<style scoped>
span{
    display:block;
    left:0;
    height:40px;
    line-height:40px;
}
.introduction{
    display:block;
    left:0;
    height: auto;
    background:seashell;
    border-radius:20px;
    padding:10px;
}
.film_picture{
    width: 400px;
    height: 500px;
}
</style>