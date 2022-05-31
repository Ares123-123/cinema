<template>
    <el-container>
        <el-main>
            <el-table :data="tableData"
                      border
                      style="width:100%;height:100%">
                <el-table-column fixed prop="user_name" label="用户" width="80">
                </el-table-column>
                <el-table-column fixed prop="cinema_name" label="电影院" width="100">
                </el-table-column>
                <el-table-column fixed prop="hall_number" label="影厅号" width="70">
                </el-table-column>
                <el-table-column fixed prop="film_name" label="电影" width="100">
                </el-table-column>
                <el-table-column fixed prop="session_price" label="单价" width="50">
                </el-table-column>
                <el-table-column fixed prop="total_price" label="总花费" width="80">
                </el-table-column>
                <el-table-column fixed prop="order_date" label="场次日期" width="100">
                </el-table-column>
                <el-table-column fixed prop="order_time" label="场次时间" width="80">
                </el-table-column>
                <el-table-column fixed prop="state" label="订单状态" width="80">
                </el-table-column>
                <el-table-column
                        fixed
                        label="操作"
                        width="250">
                    <template slot-scope="scope">
                        <el-button style="background: lightblue;width:50px;" type="text" size="small">编辑</el-button>
                        <el-button style="background: lightsalmon;width:50px;" type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <span class="notice">共{{tableData.length}}条记录</span>
        </el-main>
    </el-container>
</template>

<script>
    import axios from 'axios';
    export default {
        name: "order_management",
        data() {
            return{
                tableData: []
            }
        },
        //Vue生命周期
        mounted:function(){
            this.get_filmOrder();//页面加载需要触发表格显示
        },
        methods:{
            get_filmOrder(){
                axios.post('http://localhost:9000/selectAllFilm_Order').then(res=>{
                    this.tableData = res.data;
                    console.log('订单',this.tableData)
                }).catch((err)=>{
                    console.log(err);
                });
            },
            handleClick(row) {
                console.log(row);
            }
        }
    }
</script>

<style scoped>
.notice{
    margin-left:90%;
}
</style>