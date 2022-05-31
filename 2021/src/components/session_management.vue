<template>
    <div>
    <el-container>
        <el-main>
            <el-button @click="getSession()">全部</el-button>
            <el-button>
                <el-dropdown  @command="clickCinema">
                    <span class="el-dropdown-link">
                        <span v-if="!show_cinema_id">选择电影院</span>
                        <span v-if="show_cinema_id">{{show_cinema_name}}</span>
                        <i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item v-for='item in cinemaData':command="cinemaValue(item.cinema_name,item.cinema_id)">
                            {{item.cinema_name}}
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-button>
            <el-popover
                    placement="right"
                    width="400"
                    trigger="click">
                <span>
                    <el-alert
                            v-if="!show_cinema_id"
                            title="请选择电影院!"
                            :closable="true"
                            type="error">
                    </el-alert>
                </span>
                <el-form :model="AddOneSession" ref="AddOneSession" label-width="100px" class="demo-ruleForm"
                         v-if="show_cinema_id">
                    <el-form-item
                            label="电影院">
                        <span>{{show_cinema_name}}</span>
                    </el-form-item>
                    <el-form-item
                            label="场次日期">
                        <div class="block">
                            <el-date-picker
                                    v-model="AddOneSession.session_date"
                                    align="right"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="选择日期"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </div>
                    </el-form-item>
                    <el-form-item
                            label="场次时间">
                        <el-time-picker
                                v-model="AddOneSession.session_time"
                                value-format="hh:mm:ss"
                                :picker-options="{
                                   selectableRange: '8:00:00 - 22:00:00'
                                   }"
                                placeholder="选择时间">
                        </el-time-picker>
                    </el-form-item>
                    <el-form-item
                            prop="session_price"
                            label="场次票价">
                        <el-input type="text" v-model="AddOneSession.session_price"></el-input>
                    </el-form-item>
                    <el-form-item
                            label="选择电影">
                        <el-dropdown  @command="clickFilm">
                            <span class="el-dropdown-link"><i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item v-for='item in filmData':command="filmValue(item.film_name,item.film_id)">
                                    {{item.film_name}}
                                </el-dropdown-item>
                            </el-dropdown-menu>
                            <span style="margin-left:10px;font-size: large;padding:5px;border-radius:2px"
                                   v-if="show_film_name">
                                {{show_film_name}}
                            </span>
                        </el-dropdown>
                    </el-form-item>
                    <el-form-item
                            label="选择影厅">
                        <el-dropdown  @command="clickHall">
                            <span class="el-dropdown-link"><i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item v-for='item in hallData':command="hallValue(item.hall_number,item.hall_id)">
                                    {{item.hall_number}}号厅
                                </el-dropdown-item>
                            </el-dropdown-menu>
                            <span style="margin-left:10px;font-size: large;padding:5px;border-radius:2px"
                                  v-if="show_hall_id">
                                {{show_hall_number}}号厅
                            </span>
                        </el-dropdown>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="add_session()">提交</el-button>
                        <el-button @click="resetForm()">重置</el-button>
                    </el-form-item>
                </el-form>
                <el-button slot="reference">添加场次</el-button>
            </el-popover>
            <el-table :data="tableData"
                      class="tb-edit"
                      highlight-current-row
                      @row-click="session_Change"
                      border
                      style="width:100%;height:100%">
                <el-table-column fixed prop="session_date" label="场次日期" width="100">
                    <template scope="scope">
                        <el-input size="small" v-model="scope.row.session_date" placeholder="请输入内容" @change="session_Edit(scope.$index, scope.row)">
                        </el-input>
                        <span>{{scope.row.session_date}}</span>
                    </template>
                </el-table-column>
                <el-table-column fixed prop="session_time" label="场次时间" width="100">
                    <template scope="scope">
                        <el-input size="small" v-model="scope.row.session_time" placeholder="请输入内容" @change="session_Edit(scope.$index, scope.row)">
                        </el-input>
                        <span>{{scope.row.session_time}}</span>
                    </template>
                </el-table-column>
                <el-table-column fixed prop="session_price" label="场次票价" width="80">
                    <template scope="scope">
                        <el-input size="small" v-model="scope.row.session_price" placeholder="请输入内容" @change="session_Edit(scope.$index, scope.row)">
                        </el-input>
                        <span>{{scope.row.session_price}}</span>
                    </template>
                </el-table-column>
                <el-table-column fixed prop="hall_seat_count" label="发放票数" width="80">
                </el-table-column>
                <el-table-column fixed prop="cinema_name" label="影院名" width="150">
                </el-table-column>
                <el-table-column fixed prop="hall_number" label="影厅号" width="80">
                </el-table-column>
                <el-table-column fixed prop="film_name" label="电影名" width="100">
                </el-table-column>
                <el-table-column
                        fixed
                        label="操作"
                        width="250">
                    <template slot-scope="scope">
                        <!--
                        <el-button style="background: lightblue;width:50px;" @click="session_detail(scope.row)" type="text" size="small">售票情况</el-button>
                        -->
                        <el-button style="background: lightblue;width:50px;" @click="update_session()" type="text" size="small">编辑</el-button>
                        <el-button style="background: lightsalmon;width:50px;" @click="delete_session(scope.row,scope.$index)" type="text" size="small">删除</el-button>
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
        name: "session_management",
        data(){
            return{
                tableData: [],
                cinemaData: [],

                filmData: [],
                show_film_name:'',
                show_film_id:'',
                show_hall_number:'',
                show_hall_id:'',

                hallData:[],
                update:[],
                show_cinema_id:'',
                show_cinema_name:'',
                AddOneSession: {
                    session_id:'',
                    session_date: '',
                    session_time:new Date(18, 40),
                    session_price:'',
                    sold_number:'',
                    hall_id:'',
                    film_id:''
                },

                /*日期选择*/
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() <= Date.now();
                    },
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '明天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '后天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24 * 2);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                /*日期选择*/
            }
        },
        //Vue生命周期
        mounted:function(){
            this.getSession();//页面加载需要触发表格显示
            this.getCinema();
            this.getFilm();
        },
        methods:{
            getSession(){
                console.log('你点击了查看');
                axios.post('http://localhost:9000/selectAllSession').then(res=>{
                    this.tableData = res.data;
                    console.log(this.tableData);
                    this.show_cinema_id=''
                    this.show_cinema_name=''
                }).catch((err)=>{
                    console.log(err);
                });
            },
            //添加场次
            add_session(){
                console.log(this.AddOneSession.session_date,typeof(this.AddOneSession.session_date)+' '+this.AddOneSession.session_time,typeof(this.AddOneSession.session_time))
                this.AddOneSession.film_id = this.show_film_id;
                this.AddOneSession.hall_id = this.show_hall_id;
                this.AddOneSession.sold_number=0;
                //设置一个session_id随机数
                let session_id = this.getRandom(10000,99999)
                session_id = session_id.toString()
                this.AddOneSession.session_id = session_id
                console.log(this.AddOneSession)
                axios.post('http://localhost:9000/addOneSession',this.AddOneSession).then(res=>{
                    this.getSession();
                    alert("添加成功！")
                        this.AddOneSession.session_id='',
                        this.AddOneSession.session_date='',
                        this.AddOneSession.session_time='',
                        this.AddOneSession.session_price='',
                        this.AddOneSession.sold_number='',
                        this.AddOneSession.hall_id='',
                        this.AddOneSession.film_id='',
                        this.show_film_id='',
                        this.show_film_name='',
                        this.show_hall_id='',
                        this.show_hall_number=''
                }).catch(err=>{
                    alert("添加失败！")
                    console.log(err);
                });
            },
            resetForm() {
                console.log('你按了重置');
                //重置表单内容
                this.$refs.AddOneSession.resetFields();
                this.show_film_id='',
                this.show_film_name=''
            },

            //查看所有电影院下拉列表
            getCinema() {
                console.log('你点击了查看电影院');
                axios.post('http://localhost:9000/selectAllCinema').then(res => {
                    this.cinemaData = res.data;
                    console.log(this.cinemaData)
                }).catch((err) => {
                    console.log(err);
                });
            },
            //查看所有电影下拉列表
            getFilm(){
                console.log('你点击了查看电影');
                axios.post('http://localhost:9000/selectAllFilm').then(res => {
                    this.filmData = res.data;
                    console.log(this.filmData)
                }).catch((err) => {
                    console.log(err);
                });
            },
            //查看所有电影厅下拉列表
            getHall(){
                console.log('你点击了查看影厅');
                console.log( this.hallData);
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
                    this.hallData = res.data;
                }).catch(err=>{
                    /*请求失败*/
                    console.log(err)
                })
            },

            //点击选择电影院下拉列表
            clickCinema(command) {
                console.log('command',command);
                this.show_cinema_id=command.show_cinema_id //获取command对象里的属性值
                this.show_cinema_name=command.show_cinema_name //获取command对象里的属性值
                console.log(this.show_cinema_id,this.show_cinema_name);
                axios({
                    method:'POST',
                    url:'http://localhost:9000/selectOneCinema_session',
                    data:{
                        cinema_id:this.show_cinema_id,
                    },
                    headers:{'Content-Type':"application/json; charset=UTF-8"}
                }).then(res=>{
                    /*请求成功*/
                    console.log(res)
                    this.tableData = res.data;
                    this.getHall()
                }).catch(err=>{
                    /*请求失败*/
                    console.log(err)
                })
            },
            cinemaValue(cinema_name,cinema_id) {
                return {
                    'cinema_name': cinema_name,
                    'cinema_id': cinema_id,
                    'show_cinema_id':cinema_id,
                    'show_cinema_name':cinema_name,
                }
            },


            //点击选择电影下拉列表
            clickFilm(command) {
                console.log('command',command);
                this.show_film_id=command.show_film_id //获取command对象里的属性值
                this.show_film_name=command.show_film_name //获取command对象里的属性值
                console.log(this.show_film_id,this.show_film_name);
            },
            filmValue(film_name,film_id) {
                return {
                    'film_name': film_name,
                    'film_id': film_id,
                    'show_film_name':film_name,
                    'show_film_id':film_id
                }
            },
            //点击选择影厅下拉列表
            clickHall(command) {
                console.log('command',command);
                this.show_hall_id=command.show_hall_id //获取command对象里的属性值
                this.show_hall_number=command.show_hall_number //获取command对象里的属性值
                console.log(this.show_hall_id,this.show_hall_number);
            },
            hallValue(hall_number,hall_id) {
                return {
                    'hall_number': hall_number,
                    'hall_id': hall_id,
                    'show_hall_number':hall_number,
                    'show_hall_id':hall_id
                }
            },


            /*行内编辑*/
            session_Change(row, event, column) {
                console.log('handleCurrentChange:','row',row, 'event',event, 'column',column)
            },
            session_Edit(index, row) {
                console.log('handleEdit:', 'index',index, 'row',row);
                this.$http.post("http://localhost:9000/updateOneSession",row).then(res=>{
                    console.log("更新影厅信息",res);
                    console.log('编辑成功!')
                    this.getSession()
                }).catch(err=>{
                    alert('可能是时间格式不对!试试用日期yyyy-xx-zz，时间hh:mm:ss')
                    this.getSession()
                })
            },
            update_session(){
                this.getSession()
            },
            /*行内编辑*/

            //查看排片详情
            session_detail(row){
                console.log(row,row.session_id)
                // 跳转至订单列表页面传参
                this.$router.push({
                        path: '/session_seat',
                        query: {
                            session_id:row.session_id
                        }
            })
        },
            //删除行
            delete_session(row,index){

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
    .notice{
        margin-left:90%;
    }
</style>