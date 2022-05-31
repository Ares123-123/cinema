import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import SignUp from '../components/SignUp'
import Home from '../components/Home.vue'
import user_management from '../components/user_management'
import film_management from '../components/film_management'
import cinema_management from '../components/cinema_management'
import session_management from '../components/session_management'
import hall_management from '../components/hall_management'
import ticket_management from '../components/ticket_management'
import order_management from '../components/order_management'
import comment_management from '../components/comment_management'
import manager_management from '../components/manager_management'
import film_detail from '../components/film_detail'
import hall_detail from '../components/hall_detail'
import session_seat from '../components/session_seat'
import data_management from '../components/data_management'
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect:'/Login',
        name: 'Login',
        component: Login,
        hidden:true
    },
    {
        path: '/Login',
        name: 'Login',
        component: Login,
        hidden:true
     },
    {
        path: '/SignUp',
        name: 'SignUp',
        component: SignUp,
        hidden:true
    },
    {
        path: '/Home',
        name: '导航',
        component: Home,
        children:[
            {
                path:'/user_management',
                name:'用户管理',
                component: user_management
            },
            {
                path:'/film_management',
                name:'电影管理',
                component: film_management
            },
            {
                path:'/cinema_management',
                name:'影院管理',
                component: cinema_management
            },
            {
                path:'/hall_management',
                name:'影厅管理',
                component: hall_management
            },
            {
                path:'/session_management',
                name:'排片管理',
                component: session_management
            },
            {
                path:'/ticket_management',
                name:'售票管理',
                component: ticket_management,
                hidden:true
            },
            {
                path:'/order_management',
                name:'订单管理',
                component: order_management
            },
            {
                path:'/comment_management',
                name:'评论管理',
                component: comment_management,
            },
            {
                path:'/film_detail',
                name:'影片详情',
                component: film_detail,
                hidden:true
            },
            {
                path:'/hall_detail',
                name:'排名详情',
                component: hall_detail,
                hidden:true
            },
            {
                path:'/session_seat',
                name:'场次售票情况',
                component: session_seat,
                hidden:true
            },
            {
                path:'/manager_management',
                name:'管理员管理',
                component: manager_management,
            },
            {
                path:'/data_management',
                name:'数据统计',
                component: data_management,
            }
        ]
    },
]

const router = new VueRouter({
  routes
})

export default router
