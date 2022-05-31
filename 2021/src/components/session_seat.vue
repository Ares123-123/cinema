<template>
    <div>
        <div style="margin-left:80px;margin-bottom:40px;">
            <div  style="display:inline-flex">
                <div style="display:block">
                    <div style="background: skyblue;width: 60px;height: 40px;border-radius:10px"></div>
                    <span>未售出</span>
                </div>
                <div style="display:block;margin-left:20px">
                    <div style="background: red;width: 60px;height: 40px;border-radius:10px"></div>
                    <span>已售出</span>
                </div>
            </div>
        </div>
        <div class="hall">
            <div v-for="item in hallSeat">
                <div v-if="item.state=='未售出'" class="no_order"></div>
            </div>
            <div v-for="item in hallSeat">
                <div v-if="item.state=='已售出'" class="no_order">}</div>
            </div>
        </div>
        <div>共{{hallSeat.length}}个座位</div>
    </div>
</template>

<script>
    export default {
        name: "session_seat",
        data(){
            return {
                hallSeat:[]
            }
        },
        //Vue生命周期
        mounted:function(){
            // 如果是跳转来的，则接受初始化参数
            if (this.$route.query) {
                console.log('传参',this.$route.query.session_id)
                //去film表查询
                this.$http.post("http://localhost:9000/selectOneSession_Hall",this.$route.query).then(res => {
                    console.log("查找影厅信息", res.data);

                    for(let index=1;index<=res.data[0].hall_seat_count;index++){
                        this.hallSeat.push({state:'未售出'})
                    }

                    console.log(this.hallSeat)
                }).catch(err => {
                    alert('查找失败!')
                })
            }
        },
        methods:{}
    }
</script>

<style>
.hall{
    display:inline-block;
    width:95%;
}
.no_order{
    float:left;
    width:60px;
    height:50px;
    background: skyblue;
    border-radius:10px;
    margin:5px;
}
.order{
    float:left;
    width:60px;
    height:50px;
    background: red;
    border-radius:10px;
    margin:5px;
}
</style>