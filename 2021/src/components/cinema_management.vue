<template>
    <div>
        <el-container>
            <el-main>
                <el-popover
                        placement="right"
                        width="400"
                        trigger="click">
                    <el-form :model="AddOneCinema" ref="AddOneCinema" label-width="100px" class="demo-ruleForm">
                        <el-form-item
                                prop="cinema_name"
                                label="电影院名">
                            <el-input type="text"v-model="AddOneCinema.cinema_name"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="cinema_address"
                                label="电影院地址">
                            <el-input type="text"v-model="AddOneCinema.cinema_address"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addCinema()">提交</el-button>
                            <el-button @click="resetForm()">重置</el-button>
                        </el-form-item>
                    </el-form>
                    <el-button slot="reference">添加电影院</el-button>
                </el-popover>
                <el-table :data="tableData"
                          class="tb-edit"
                          highlight-current-row
                          @row-click="cinema_Change"
                          border
                          style="width:850px;height:100%">
                    <el-table-column fixed prop="cinema_id" label="电影院id" width="150">
                    </el-table-column>
                    <el-table-column fixed prop="cinema_name" label="电影院名" width="250">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.cinema_name" placeholder="请输入内容" @change="cinema_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.cinema_name}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column fixed prop="cinema_address" label="电影院地址" width="150">
                    <template scope="scope">
                        <el-input size="small" v-model="scope.row.cinema_address" placeholder="请输入内容" @change="cinema_Edit(scope.$index, scope.row)">
                        </el-input>
                        <span>{{scope.row.cinema_address}}</span>
                    </template>
                </el-table-column>
                    <el-table-column fixed prop="cinema_income" label="收入" width="150">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.cinema_income" placeholder="请输入内容" @change="cinema_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.cinema_income}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            fixed
                            label="操作"
                            width="200">
                        <template slot-scope="scope">
                            <el-button style="background: moccasin;width:50px;" @click="update_cinema()" type="text" size="small">编辑</el-button>
                            <el-button style="background: lightsalmon;width:50px;" @click="delete_cinema(scope.row,scope.$index)" type="text" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <span class="notice">共{{tableData.length}}条记录</span>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        name: "cinema_management",
        data() {
            return {
                tableData: [],
                AddOneCinema:{
                    cinema_id:'',
                    cinema_name:'',
                    cinema_address:'',
                    cinema_income:''
                }
            }
        },
        //Vue生命周期
        mounted: function () {
            this.getCinema();//页面加载需要触发表格显示
        },
        methods: {
            getCinema() {
                console.log('你点击了查看电影院');
                axios.post('http://localhost:9000/selectAllCinema').then(res => {
                    this.tableData = res.data;
                    console.log(this.tableData)
                }).catch((err) => {
                    console.log(err);
                });
            },
            //添加电影院表单
            addCinema(){
                //设置一个cinema_id随机数
                let cinema_id = this.getRandom(10000,99999)
                cinema_id = cinema_id.toString()
                this.AddOneSession.cinema_id = cinema_id
                this.AddOneCinema.cinema_income=0   //收入初始值为0
                console.log('AddOneCinema',this.AddOneCinema)
                this.$http.post("http://localhost:9000/addOneCinema",this.AddOneCinema).then(res=>{
                    this.tableData = res.data;
                    console.log("添加影厅信息",res);
                    alert("添加成功");
                    this.getCinema()
                        this.AddOneCinema.cinema_id='',
                        this.AddOneCinema.cinema_name='',
                        this.AddOneCinema.cinema_address='',
                        this.AddOneCinema.cinema_income=''
                })
            },
            //清空电影院表单
            resetForm() {
                console.log('你按了重置');
                //重置表单内容
                this.$refs.AddOneCinema.resetFields();
            },
            /*行内编辑*/
            cinema_Change(row, event, column) {
                console.log('handleCurrentChange:','row',row, 'event',event, 'column',column)
            },
            cinema_Edit(index, row) {
                console.log('handleEdit:', 'index',index, 'row',row);
                this.$http.post("http://localhost:9000/updateOneCinema",row).then(res=>{
                    console.log("更新影院信息",res);
                    console.log('编辑成功!')
                    this.getCinema()
                }).catch(err=>{
                    alert('编辑失败!')
                })
            },
            update_cinema(){
                this.getCinema()
            },
            /*行内编辑*/

            //删除电影院
            delete_cinema(row,index){
             console.log(row.cinema_id,index)
                //this.tableData.splice(index,1) //删除前端
                this.$http.post("http://localhost:9000/deleteOneCinema",row).then(res=>{
                    console.log("更新电影院信息",res);
                    this.getCinema();
                    alert("删除成功")
                }).catch(err=>{
                    alert('无法删除该电影院!')
                })
           }
        }
    }
</script>

<style scoped>
    .tb-edit .el-input {
        display: none
    }
    .tb-edit .current-row .el-input {
        display: block
    }
    .tb-edit .current-row .el-input+span {
        display: none
    }
</style>