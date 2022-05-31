<template>
        <el-container>
            <el-main>
                <el-table :data="tableData"
                          border
                          style="width:850px;height:100%">
                    <el-table-column fixed prop="user_id" label="用户id" width="150">
                    </el-table-column>
                    <el-table-column fixed prop="user_name" label="用户名" width="150">
                    </el-table-column>
                    <el-table-column fixed prop="user_password" label="用户密码" width="150">
                    </el-table-column>
                    <el-table-column fixed prop="user_money" label="用户余额" width="100">
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
        name: "user_management",
        data() {
            return{
                tableData: []
            }
        },
        //Vue生命周期
        mounted:function(){
            this.getUser();//页面加载需要触发表格显示
        },
        methods:{
            getUser(){
                axios.post('http://localhost:9000/QueryUser').then(res=>{
                    this.tableData = res.data;
                    console.log(this.tableData)
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
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

</style>