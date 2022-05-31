<template>
    <el-container>
        <el-main>
            <el-table :data="tableData"
                      border
                      style="width:100%;height:100%">
                <el-table-column fixed prop="user_name" label="用户" width="80">
                </el-table-column>
                <el-table-column fixed prop="comment_date" label="日期" width="100">
                </el-table-column>
                <el-table-column fixed prop="comment_time" label="时间" width="100">
                </el-table-column>
                <el-table-column fixed prop="film_name" label="电影" width="100">
                </el-table-column>
                <el-table-column fixed prop="comment_content" label="评论" width="150">
                </el-table-column>
                <el-table-column fixed prop="approval_state" label="审核状态" width="100">

                </el-table-column>
                <el-table-column fixed label="审核" width="100">
                    <template scope="scope">
                        <span v-if="scope.row.approval_state=='已通过'">
                            <img src="../assets/font/comment_success.png" class="state"/>
                        </span>
                        <span v-else-if="scope.row.approval_state=='未通过'">
                            <img src="../assets/font/comment_fail.png" class="state"/>
                        </span>
                        <span v-else-if="scope.row.approval_state=='未审核'">
                            <img src="../assets/font/comment_right.png" class="change_state" @click="approval_success(scope.$index, scope.row)"/>
                            <img src="../assets/font/comment_wrong.png" class="change_state" @click="approval_fail(scope.$index, scope.row)"/>
                        </span>
                    </template>
                </el-table-column>
                <el-table-column
                        fixed
                        label="操作"
                        width="250">
                    <template slot-scope="scope">
                        <el-button style="background: lightblue;width:50px;" type="text" size="small">编辑</el-button>
                        <el-button style="background: lightsalmon;width:50px;" @click="delete_comment(scope.row,scope.$index)" type="text" size="small">删除</el-button>
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
        name: "comment_management",
        data() {
            return{
                tableData: [],
            }
        },
        //Vue生命周期
        mounted:function(){
            this.getComment();//页面加载需要触发表格显示
        },
        methods:{
            getComment(){
                axios.post('http://localhost:9000/selectAllComment').then(res=>{
                    this.tableData = res.data;
                    console.log(this.tableData)
                }).catch((err)=>{
                    console.log(err);
                });
            },
            handleClick(row) {
                console.log(row);
            },
            //删除一行评论
            delete_comment(row,index){
                console.log(row,index)
                //this.tableData.splice(index,1) //删除前端
                this.$http.post("http://localhost:9000/deleteOneComment",index).then(res=>{
                    console.log("更新评论信息",res);
                    this.getComment();
                    alert("删除成功")
                }).catch(err=>{
                    alert('评论无法删除!')
                })
            },
           //审核成功
            approval_success(row,index){
                console.log(row,index.comment_id)
                let comment_id = index.comment_id
                axios({
                    method:'POST',
                    url:'http://localhost:9000/updateOneComment_stateSuccess',
                    data:{
                        comment_id:comment_id
                    },
                    headers:{'Content-Type':"application/json; charset=UTF-8"}
                }).then(res=>{
                    /*请求成功*/
                    console.log("更新审核状态已通过",res);
                    this.getComment();
                    alert("确认通过")
                }).catch(err=>{
                    alert('审核失败!')
                })
            },
          //审核不通过
            approval_fail(row,index){
                console.log(row,index)
                let comment_id = index.comment_id
                axios({
                    method:'POST',
                    url:'http://localhost:9000/updateOneComment_stateFail',
                    data:{
                        comment_id:comment_id
                    },
                    headers:{'Content-Type':"application/json; charset=UTF-8"}
                }).then(res=>{
                    /*请求成功*/
                    console.log("更新审核状态未通过",res);
                    this.getComment();
                    alert("确认不通过")
                }).catch(err=>{
                    alert('审核失败!')
                })
            },
        }
    }
</script>

<style scoped>
    .state {
        width: 25px;
        height: 25px;
        display: flex;
        padding-left:25px;
    }
    .change_state{
        width: 20px;
        height: 20px;
        display: inline;
        margin:8px
    }
</style>