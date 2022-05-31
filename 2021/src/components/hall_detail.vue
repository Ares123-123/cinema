<template>
    <el-container>
        <el-main>
            <el-table :data="tableData"
                      border
                      style="width:850px;height:100%">
                <el-table-column fixed prop="hall_number" label="影厅号" width="80">
                </el-table-column>
                <el-table-column fixed prop="film_name" label="排片电影" width="150">
                </el-table-column>
                <el-table-column fixed prop="session_date" label="日期" width="100">
                </el-table-column>
                <el-table-column fixed prop="session_time" label="时间" width="100">
                </el-table-column>
                <el-table-column fixed prop="session_price" label="票价" width="100">
                </el-table-column>
                <el-table-column fixed prop="sold_number" label="售出票数" width="100">
                </el-table-column>
                <el-table-column fixed prop="hall_seat_count" label="发放票数" width="100">
                </el-table-column>
                <el-table-column
                        fixed
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
    </el-container>
</template>

<script>
    import axios from 'axios';
    export default {
        name: "hall_detail",
        data() {
            return {
                tableData: []
            }
        },
        //Vue生命周期
        mounted:function(){
            // 如果是跳转来的，则接受初始化参数
            if (this.$route.query) {
                console.log('传参',this.$route.query.hall_id)
                //去film表查询
                this.$http.post("http://localhost:9000/selectOneHall",this.$route.query).then(res => {
                    console.log("查找影厅信息", res.data);
                    this.tableData = res.data;
                    console.log(this.tableData);
                }).catch(err => {
                    alert('查找失败!')
                })
            }
        },
        methods:{
            handleClick(row) {
                console.log(row);
            }
        }
    }
</script>

<style scoped>

</style>