<template>
    <div>
        <el-form ref="SignUpForm"
                 :rules="SignUpRules"
                 :model="SignUpForm"
                 v-loading="loading"
                 element-loading-text="正在注册"
                 element-loading-spinner="el-icon-loading"
                 element-loading-background="rgba(0,0,0,0.8)"
                 class="SignUpContainer">
            <h3>系统注册</h3>
            <el-form-item prop="user_name">
                <el-input type="text" auto-complete="false" v-model="SignUpForm.manager_name" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="user_password">
                <el-input type="password" v-model="SignUpForm.manager_password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item prop="verify_user_password">
                <el-input type="password" v-model="SignUpForm.verify_user_password" placeholder="输入确认密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" style="width:150px" @click="signUp()">注册</el-button>
                <el-button style="width:150px" @click="goLogin()">返回登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "SignUp",
        data(){
            return{
                //表单数据对象
                SignUpForm:{
                    manager_name:'',
                    manager_password:'',
                    verify_user_password:'',
                    manager_id:''
                },
                loading:false,//加载初值为无
            //验证对象
                SignUpRules:{
                //校验用户名
                manager_name:[
                    {require:true,message:'请输入用户名',trigger:'blur'},  //必填验证
                    {min:3,max:10,message:'长度在3到5个字符',trigger:'blur'} //长度验证
                ],
                    //校验密码
                    manager_password:[
                    {required:true,message:'请输入密码',trigger:'blur'},//必填项验证
                    {min:3,max:10,message:'长度在5到10个字符',trigger:'blur'} //长度验证
                ],
                    verify_user_password:[
                        {required:true,message:'请输入验证密码',trigger:'blur'},
                    ]//必填项验证
            }
            }
        },
        methods:{
            signUp() {
                if (this.SignUpForm.manager_password != this.SignUpForm.verify_user_password && this.SignUpForm) {
                    alert("两次输入的密码不一致！")
                }
                else if(this.SignUpForm.manager_name&&this.SignUpForm.manager_password&&this.SignUpForm.verify_user_password){
                    let manager_id = this.getRandom(10000, 99999)
                    manager_id = manager_id.toString()
                    this.SignUpForm.manager_id = manager_id
                    this.$refs.SignUpForm.validate(async valid => {
                        if (valid) {
                            this.loading = true;  //正在加载
                            const {data: res} = await this.$http.post('signUpManager', this.SignUpForm); //访问后台
                            if (res == "OK") {
                                this.$message.success("注册成功"); //登录成功
                                this.$router.push({path: "/Login"});
                            } else {
                                this.$message.error("注册失败");//登录失败
                            }
                        }
                        else {
                            return false; //验证失败
                        }
                    })
                    alert("注册成功！")
                }
                else{
                    alert("请输入注册信息！")
                }
            },
            goLogin(){
                this.$router.replace({path:"/"});
            },
        }
    }
</script>

<style scoped>
    .SignUpContainer{
        background-clip:padding-box;
        margin:50px auto;
        width:350px;
        padding:15px 35px 15px 35px;
        background: aliceblue;
        border-left:1px solid skyblue;
        border-right:1px solid skyblue;
    }
</style>