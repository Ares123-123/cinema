<template>
    <el-container>
        <el-main>
            <el-button @click="getAllHall()">全部</el-button>
            <el-button>
                <el-dropdown  @command="clickCinema">
                    <span class="el-dropdown-link">
                        <span v-if="!show_cinema_id">选择电影院</span>
                        <span>{{show_cinema_name}}</span>
                        <i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item v-for='item in cinemaData':command="composeValue(item.cinema_name,item.cinema_id)">
                            {{item.cinema_name}}
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-button>
            <el-popover
                    placement="right"
                    width="400"
                    trigger="click">
                <span v-if="show_cinema_name">
                  <i class="el-icon-film"></i><span>{{show_cinema_name}}</span>
                </span>
                <span>
                    <el-alert
                            v-if="!show_cinema_id"
                            title="请选择电影院!"
                            :closable="false"
                            type="error">
                    </el-alert>
                </span>
                <el-form :model="NewOneHall" :rules="addHallRules" ref="NewOneHall" label-width="100px" class="demo-ruleForm"
                         v-if="show_cinema_id">
                    <el-form-item
                            prop="cinema_id"
                            label="影院id">
                        <el-text type="text"v-model="NewOneHall.cinema_id">{{show_cinema_id}}</el-text>
                    </el-form-item>
                    <el-form-item
                            prop="hall_number"
                            label="影厅号">
                        <el-input type="text" v-model="NewOneHall.hall_number"></el-input>
                    </el-form-item>
                    <el-form-item
                            prop="hall_seat_count"
                            label="座位数">
                        <el-input type="text" v-model="NewOneHall.hall_seat_count"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="addHall()">提交</el-button>
                        <el-button @click="resetForm()">重置</el-button>
                    </el-form-item>
                </el-form>
                <el-button slot="reference">添加影厅</el-button>
            </el-popover>
            <el-table :data="tableData"
                      class="tb-edit"
                      highlight-current-row
                      @row-click="hall_Change"
                      border
                      style="width:850px;height:100%">
                <el-table-column fixed prop="cinema_name" label="电影院名" width="150">
                </el-table-column>
                <el-table-column fixed prop="hall_id" label="影厅id" width="150">
                </el-table-column>
                <el-table-column fixed prop="hall_number" label="影厅号" width="150">
                    <template scope="scope">
                        <el-input size="small" v-model="scope.row.hall_number" placeholder="请输入内容" @change="hall_Edit(scope.$index, scope.row)">
                        </el-input>
                        <span>{{scope.row.hall_number}}</span>
                    </template>
                </el-table-column>
                <el-table-column fixed prop="hall_seat_count" label="影厅座位数" width="150">
                    <template scope="scope">
                        <el-input size="small" v-model="scope.row.hall_seat_count" placeholder="请输入内容" @change="hall_Edit(scope.$index, scope.row)">
                        </el-input>
                        <span>{{scope.row.hall_seat_count}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        fixed
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button style="background: moccasin;width:50px;" @click="hallDetail(scope.row)" type="text" size="small">排片详情</el-button>
                        <el-button style="background: lightblue;width:50px;" @click="update_hall()" type="text" size="small">编辑</el-button>
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
        name: "hall_management",
        data() {
            return {
                tableData: [],
                cinemaData: [],
                NewOneHall: {
                   cinema_id: '',
                   hall_id: '',
                   hall_number: '',
                   hall_seat_count: ''
                 },
                show_cinema_id:'',
                show_cinema_name:'',
                //验证对象
                addHallRules:{
                    //校验影院id
                    cinema_id:[
                        {required: true, message: '请输入影院id', trigger: 'blur'},//必填项验证
                    ],
                    //校验影厅id
                    hall_id:[
                            {required: true, message: '请输入影厅id', trigger: 'blur'},//必填项验证
                       ],
                    //校验影厅号
                    hall_number:[
                            {required: true, message: '请输入影厅号', trigger: 'blur'},//必填项验证
                    ],
                    //校验影厅座位数
                    hall_seat_count:[
                           {required: true, message: '请输入密码', trigger: 'blur'},//必填项验证
                    ]
                }
            }
        },
        //Vue生命周期
        mounted:function(){
            this.getAllHall();//页面加载需要触发表格显示
            this.getCinema();
        },
        methods:{
            //查看所有影厅
            getAllHall(){
                axios.post('http://localhost:9000/selectAllHall').then(res=>{
                    this.tableData = res.data;
                    this.show_cinema_id=''
                    this.show_cinema_name=''
                    console.log(this.tableData)
                }).catch((err)=>{
                    console.log(err);
                });
            },
            //查看所有下拉列表
            getCinema() {
                console.log('你点击了查看电影院');
                axios.post('http://localhost:9000/selectAllCinema').then(res => {
                    this.cinemaData = res.data;
                    console.log(this.cinemaData)
                }).catch((err) => {
                    console.log(err);
                });
            },
            //点击选择下拉列表
            clickCinema(command) {
                console.log('command',command);
                this.show_cinema_id=command.show_cinema_id //获取command对象里的属性值
                this.show_cinema_name=command.show_cinema_name //获取command对象里的属性值
                console.log(this.show_cinema_id,this.show_cinema_name);
                axios({
                    method:'POST',
                    url:'http://localhost:9000/selectOneCinema_Hall',
                    data:{
                        cinema_id:this.show_cinema_id,
                    },
                    headers:{'Content-Type':"application/json; charset=UTF-8"}
                }).then(res=>{
                    /*请求成功*/
                    console.log(res)
                    this.tableData = res.data;
                }).catch(err=>{
                    /*请求失败*/
                    console.log(err)
                })
            },
            composeValue(cinema_name,cinema_id) {
                return {
                    'cinema_name': cinema_name,
                    'cinema_id': cinema_id,
                    'show_cinema_id':cinema_id,
                    'show_cinema_name':cinema_name,
                }
            },
            //添加影厅表单
            addHall(){
             console.log('在 '+this.show_cinema_name+' 中添加影厅')
                this.NewOneHall.cinema_id=this.show_cinema_id
                //设置一个cinema_id随机数
                let hall_id = this.getRandom(10000,99999)
                hall_id = hall_id.toString()
                this.NewOneHall.hall_id = hall_id
                console.log(this.NewOneHall)
                this.$http.post("http://localhost:9000/addOneHall",this.NewOneHall).then(res=>{
                    console.log("更新影厅信息",res);
                    alert("添加成功");
                    axios({
                        method:'POST',
                        url:'http://localhost:9000/selectOneCinema_Hall',
                        data:{
                            cinema_id:this.show_cinema_id,
                        },
                        headers:{'Content-Type':"application/json; charset=UTF-8"}
                    }).then(res=>{
                        /*请求成功*/
                        console.log(res)
                        this.tableData = res.data;
                    }).catch(err=>{
                        /*请求失败*/
                        console.log(err)
                    })
                    //清空
                        this.NewOneHall.cinema_id='',
                        this.NewOneHall.hall_id= '',
                        this.NewOneHall.hall_seat_count= '',
                        this.NewOneHall.hall_number= ''
                })
            },
           //清空添加影厅表单
             resetForm() {
             console.log('你按了重置');
          //重置表单内容
             this.$refs.NewOneHall.resetFields();
           },
            //查看
            hallDetail(row){
                console.log(row,row.hall_id)
                // 跳转至订单列表页面传参
                this.$router.push({
                    path: '/hall_detail',
                    query: {
                        hall_id:row.hall_id
                    }
                }) // 带参跳转
            },
            /*行内编辑*/
            hall_Change(row, event, column) {
                console.log('handleCurrentChange:','row',row, 'event',event, 'column',column)
            },
            hall_Edit(index, row) {
                console.log('handleEdit:', 'index',index, 'row',row);
                this.$http.post("http://localhost:9000/updateOneHall",row).then(res=>{
                    console.log("更新影厅信息",res);
                    console.log('编辑成功!')
                    this.getAllHall()
                }).catch(err=>{
                    alert('编辑失败!')
                })
            },
            update_hall(){
                this.getAllHall()
            },
            /*行内编辑*/
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