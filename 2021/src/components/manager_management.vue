<template>
    <el-container>
        <el-main>
            <el-table :data="tableData"
                      border
                      style="width:100%;height:100%">
                <el-table-column fixed prop="manager_id" label="管理员工号" width="150">
                </el-table-column>
                <el-table-column fixed prop="manager_name" label="管理员名" width="150">
                </el-table-column>
                <el-table-column fixed prop="manager_password" label="管理员密码" width="150">
                </el-table-column>
                <el-table-column fixed prop="phone_number" label="管理员电话" width="150">
                </el-table-column>
                <el-table-column
                        fixed
                        label="操作"
                        width="250">
                    <template slot-scope="scope">
                        <el-button style="background: moccasin;width:50px;" type="text" size="small">编辑</el-button>
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
        name: "manager_management",
        data() {
            return {
                tableData: [],
            }
        },
        //Vue生命周期
        mounted:function(){
            this.getManager();//页面加载需要触发表格显示
        },
        methods: {
            getManager() {
                console.log('你点击了查看管理员');
                axios.post('http://localhost:9000/selectAllManager').then(res => {
                    this.tableData = res.data;
                    console.log(this.tableData)
                }).catch((err) => {
                    console.log(err);
                });
            },
        }
    }
</script>

<style scoped>

</style>