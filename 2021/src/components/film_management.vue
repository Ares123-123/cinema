<template>
    <div>
        <el-container>
            <el-main>
                <el-popover
                        placement="right"
                        width="800"
                        trigger="click">
                    <el-form :model="AddOneFilm" ref="AddOneFilm" label-width="100px" class="demo-ruleForm">
                        <el-form-item
                                prop="film_name"
                                label="电影名">
                            <el-input type="text" v-model="AddOneFilm.film_name"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="film_rank"
                                label="电影排名">
                            <el-input type="text" v-model="AddOneFilm.film_rank"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="film_score"
                                label="电影评分">
                            <el-input type="text" v-model="AddOneFilm.film_score"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="film_style"
                                label="电影类型">
                            <el-input type="text" v-model="AddOneFilm.film_style"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="director"
                                label="导演">
                            <el-input type="text" v-model="AddOneFilm.director"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="actor"
                                label="主演">
                            <el-input type="text" v-model="AddOneFilm.actor"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="film_introduction"
                                label="简介">
                            <el-input type="textarea" v-model="AddOneFilm.film_introduction"></el-input>
                        </el-form-item>
                        <el-form-item
                                prop="film_picture_url"
                                label="上传图片">
                                <el-upload class="avatar-uploader"
                                           ref="upload"
                                           :auto-upload="false"
                                           :multiple="false"
                                           :show-file-list="false"
                                           :on-change="changeImage"
                                           :on-success="afterUpload"
                                           :before-upload="beforeAvatarUpload"
                                           action="'http://localhost:9000/upload'"
                                           name="file"
                                           >
                                    <img v-if="model.icon" :src="model.icon" class="avatar">
                                    <i v-else class="el-icon-plus avatar-uploader-icon" style="line-height: 178px;"></i>
                                </el-upload>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addFilm()">提交</el-button>
                            <el-button @click="resetForm()">重置</el-button>
                        </el-form-item>
                    </el-form>
                    <el-button slot="reference">上架电影</el-button>
                </el-popover>
                <el-table :data="tableData"
                          class="tb-edit"
                          highlight-current-row
                          @row-click="film_Change"
                          border
                          style="width:100%;height:100%">
                    <el-table-column fixed label="电影图片" width="100" height="200">
                        <template slot-scope="scope">
                            <img :src="'data:image/png;base64,'+scope.row.film_picture_url" class="film_picture">
                        </template>
                    </el-table-column>
                    <el-table-column fixed prop="film_name" label="电影名" width="150">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.film_name" placeholder="请输入内容" @change="film_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.film_name}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column fixed prop="film_rank" label="电影排名" width="80">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.film_rank" placeholder="请输入内容" @change="film_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.film_rank}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column fixed prop="film_score" label="电影评分" width="80">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.film_score" placeholder="请输入内容" @change="film_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.film_score}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column fixed prop="film_style" label="电影类型" width="100">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.film_style" placeholder="请输入内容" @change="film_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.film_style}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column fixed prop="director" label="导演" width="80">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.director" placeholder="请输入内容" @change="film_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.director}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column fixed prop="actor" label="主演" width="100">
                        <template scope="scope">
                            <el-input size="small" v-model="scope.row.actor" placeholder="请输入内容" @change="film_Edit(scope.$index, scope.row)">
                            </el-input>
                            <span>{{scope.row.actor}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            fixed
                            label="操作"
                            width="250">
                        <template slot-scope="scope">
                            <el-button style="background: moccasin;width:50px;" @click="filmDetail(scope.row)" type="text" size="small">查看</el-button>
                            <el-button style="background: lightblue;width:50px;" @click="update_film()" type="text" size="small">编辑</el-button>
                            <el-button style="background: lightsalmon;width:50px;" @click="delete_film(scope.row,scope.$index)" type="text" size="small">下架电影</el-button>
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
        name: "film_management",
        data() {
            return{
                tableData: [],
                AddOneFilm: {
                    film_id: '',  //电影id
                    film_name:'', //电影名
                    film_rank:'', //电影排名
                    film_score:'', //电影评分
                    film_style:'', //电影类型
                    director:'', //导演
                    actor:'',  //主演
                    film_introduction:"" , //简介
                    film_picture_url:'',  //图片Url
                      },
                model:{},
            }
        },
        //Vue生命周期
        mounted:function(){
            this.getFilm();//页面加载需要触发表格显示
        },
        methods:{
            //查看所有电影信息
            getFilm(){
                console.log('你点击了查看');
                axios.post('http://localhost:9000/selectAllFilm').then(res=>{
                    this.tableData = res.data;
                    console.log(this.tableData)
                }).catch((err)=>{
                    console.log(err);
                });
            },
            //添加电影表单
            addFilm(){
                //设置一个film_id随机数
                let film_id = this.getRandom(10000,99999)
                film_id = film_id.toString()
                this.AddOneFilm.film_id = film_id
                console.log('AddOneFilm',this.AddOneFilm);
                this.$http.post("http://localhost:9000/addOneFilm",this.AddOneFilm).then(res=>{
                    console.log("更新电影信息",res);
                    this.getFilm();
                    alert("添加成功");
                    //清空
                            this.AddOneFilm.film_id='',
                            this.AddOneFilm .film_name='',
                            this.AddOneFilm .film_rank='',
                            this.AddOneFilm.film_score='',
                            this.AddOneFilm.film_style='',
                            this.AddOneFilm.director='',
                            this.AddOneFilm.actor='',
                            this.AddOneFilm.film_introduction="" ,
                            this.AddOneFilm.film_picture_url='',
                            this.model={}
                })
            },
            resetForm() {
                console.log('你按了重置');
                //重置表单内容
                this.$refs.AddOneFilm.resetFields();
            },
            //删除一行数据
            delete_film(row,index){
                console.log(row.film_id,index)
                //this.tableData.splice(index,1) //删除前端
                this.$http.post("http://localhost:9000/deleteOneFilm",row).then(res=>{
                    console.log("更新电影信息",res);
                    this.getFilm();
                    alert("删除成功")
                }).catch(err=>{
                    alert('该电影已被排片,无法下架!')
                })
            },
            //查看
            filmDetail(row){
                console.log(row,row.film_id)
                // 跳转至订单列表页面传参
                this.$router.push({
                    path: '/film_detail',
                    query: {
                     film_id:row.film_id
                    }
                }) // 带参跳转
            },
             /*行内编辑*/
            film_Change(row, event, column) {
                console.log('handleCurrentChange:','row',row, 'event',event, 'column',column)
            },
            film_Edit(index, row) {
                console.log('handleEdit:', 'index',index, 'row',row);
                this.$http.post("http://localhost:9000/updateOneFilm",row).then(res=>{
                    console.log("更新电影信息",res);
                    console.log('编辑成功!')
                    this.getFilm()
                }).catch(err=>{
                    alert('编辑失败!')
                })
            },
            update_film(){
                this.getFilm()
            },
            /*行内编辑*/

            beforeAvatarUpload(file){

            },
            //添加图片
            changeImage(file){
                // 转换操作可以不放到这个函数里面，
                // 因为这个函数会被多次触发，上传时触发，上传成功也触发
                let reader = new FileReader()
                reader.readAsDataURL(file.raw);// 这里也可以直接写参数event.raw

                // 转换成功后的操作，reader.result即为转换后的DataURL ，
                // 它不需要自己定义，你可以console.log(reader.result)看一下
                reader.onload=()=>{
                    console.log("reader.result",reader.result)
                    let comma = reader.result.indexOf(",");
                    let base64 = reader.result.substring(comma + 1);
                    this.AddOneFilm.film_picture_url = base64
                }
                /* 另外一种本地预览方法 */
                let URL = window.URL || window.webkitURL;
                this.model.icon = URL.createObjectURL(file.raw);
                console.log(URL.createObjectURL(file.raw))
                console.log("this.model.icon",this.model.icon);
                // 转换后的地址为 blob:http://xxx/7bf54338-74bb-47b9-9a7f-7a7093c716b5
                this.getFilm();
            },

            //添加成功
            afterUpload(fileList,file) {
                console.log("this.model.icon",this.model.icon)
            }
        }
    }
</script>

<style scoped>
    .button-container{

    }
    .tb-edit .el-input {
        display: none
    }
    .tb-edit .current-row .el-input {
        display: block
    }
     .tb-edit .current-row .el-input+span {
         display: none
     }

    .avatar-uploader {
        margin-top: 20px;
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        width: 178px;
        height: 178px;
    }

    .avatar-uploader:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 250px;
        display: flex;
    }
    .film_picture{
        width: 80px;
        height: 100px;
    }
</style>