<template>
    <div>
        <el-form ref="LoginForm"
                 :rules="loginRules"
                 :model="LoginForm"
                 v-loading="loading"
                 element-loading-text="正在登录"
                 element-loading-spinner="el-icon-loading"
                 element-loading-background="rgba(0,0,0,0.8)"
                 class="LoginContainer">
         <h3>系统登录</h3>
            <el-form-item prop="user_name">
                <el-input type="text" auto-complete="false" v-model="LoginForm.manager_name" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="user_password">
                <el-input type="password" v-model="LoginForm.manager_password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-checkbox v-model="checked" class="loginRemeber">记住我</el-checkbox>
                </div>
                <el-button type="primary" style="width:180px" @click="submitLogin()">登录</el-button>
                <el-button type="info" @click="resetLogin()">重置</el-button>
                <el-button @click="signUp()">注册</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>

    export default {
        name: "Login",
        data(){
            return{
                captureUrl:'', //验证码图片
                //表单数据对象
                LoginForm:{
                    manager_name:'',
                    manager_password:'',
                },
                loading:false,//加载初值为无
                checked:'true',
                //验证对象
                loginRules:{
                    //校验用户名
                    manager_name:[
                        {require:true,message:'请输入用户名',trigger:'blur'},  //必填验证
                        {min:3,max:10,message:'长度在3到5个字符',trigger:'blur'} //长度验证
                    ],
                    //校验密码
                    manager_password:[
                        {required:true,message:'请输入密码',trigger:'blur'},//必填项验证
                        {min:3,max:10,message:'长度在5到10个字符',trigger:'blur'} //长度验证
                    ]
                }
            }
        },
        methods:{
            submitLogin(){
                console.log('你按了登录')
                //验证你按了登录
                this.$refs.LoginForm.validate(async valid=>{
                    if(valid){
                       this.loading=true;  //正在加载
                       const {data:res} = await this.$http.post('Login',this.LoginForm); //访问后台
                       if(res == "OK"){
                           this.$message.success("登录成功"); //登录成功
                           this.$router.push({path:"/Home"});
                       } else{
                           this.$message.error("登录失败");//登录失败
                       }
                    }
                    else{
                       return false; //验证失败
                     }
             })
            },
            resetLogin(){
                console.log('你按了重置');
                //重置表单内容
                this.$refs.LoginForm.resetFields();
            },
            signUp(){
                this.$router.replace({path:"/SignUp"});
            }
        }
    }
</script>

<style>
.LoginContainer{
    border-radius:15px;
    background-clip:padding-box;
    margin:50px auto;
    width:350px;
    padding:15px 35px 15px 35px;
    background: aliceblue;
    border:1px solid skyblue;
    /*box-shadow: 0 0 25px gray;*/  /*盒子外边框阴影*/
}
.loginRemeber{
    display:block;
    float:top;
    text-align: left;
    margin: 0 0 15px 0
}
</style>