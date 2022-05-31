<template>
    <div>
        <el-container class="page">
            <el-header class="header">
                <span @click="quitLogin()" class="quitLogin">退出登录</span>
                <span class="header-title">电影院后台管理系统</span>
                </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu router>
                        <el-submenu index="1" v-for="(item,index) in this.$router.options.routes"
                                    :key="index" v-if="!item.hidden" >  <!--启用了路由模式-->
                            <template slot="title"><i class="el-icon-location">导航</i></template>
                            <el-menu-item :index="children.path" v-for="(children,index) in item.children"
                                          v-if="!children.hidden">{{children.name}}</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        methods:{
            quitLogin(){
                this.$router.push({path:"/Login"});
            }
        }
    }
</script>

<style>
.page{
    margin:0
}
.header{
    background: skyblue;
    padding:20px
}
.header-title{
    line-height:50px;
    font-size: 25px;
    font-weight: bold;
}
.quitLogin{
    position: absolute;left:20px;margin:10px;background: antiquewhite;border-radius:10px;padding:8px;
}

.quitLogin:hover{
    position: absolute;left:20px;margin:10px;background: seashell;border-radius:10px;padding:8px;
    border:2px solid saddlebrown;
}
</style>