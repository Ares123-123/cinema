package com.example.demo.wx_api;

import com.example.demo.bean.Order;
import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class wx_user {
    @Autowired
    UserDao userDao;

    //用户注册
    @RequestMapping("/userSignUp")
    String userSignUp(HttpServletRequest request) {
        //实例化
        User signUp_user = new User();
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        int user_money = Integer.valueOf(request.getParameter("user_money"));

        //set方法
        signUp_user.setUser_id(user_id);
        signUp_user.setUser_name(user_name);
        signUp_user.setUser_password(user_password);
        signUp_user.setUser_money(user_money);

        List<User> addUser = userDao.userSignUp(signUp_user);

        return "OK";
    }

    //用户登录
    @RequestMapping("/userLogin")
    List<User> userLogin(HttpServletRequest request) {
        //实例化
        User login_user = new User();
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");

        //set方法
        login_user.setUser_name(user_name);
        login_user.setUser_password(user_password);

        List<User> loginUser = userDao.userLogin(login_user);

        System.out.println(loginUser);

        return loginUser;
    }

    //修改密码
    @RequestMapping("/wx_change_password")
    List<User> wx_change_password(HttpServletRequest request) {
        //实例化
        User change_user = new User();
        String user_id = request.getParameter("user_id");
        String user_password = request.getParameter("user_password");

        //set方法
        change_user.setUser_name(user_id);
        change_user.setUser_password(user_password);

        List<User> changeUser = userDao.wx_change_password(change_user);

        System.out.println(user_id);
        System.out.println(user_password);

        return changeUser;
    }

    //查询指定id用户信息
    @RequestMapping("/selectOne_User")
    public List<User> SelectOne_User(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        List<User> oneUser = userDao.selectOne_User(user_id);
        return oneUser;
    }
    //用户支付电影票/充值
    @RequestMapping("/payUser_money")
    public String PayUser_money(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        int user_money = Integer.valueOf(request.getParameter("user_money"));

        //实例化
        User payorder = new User();

            payorder.setUser_id(user_id);
            payorder.setUser_money(user_money);

            List<User> payUser_money = userDao.payUser_money(payorder);

            return  "OK";
    }

    //用户退票退款
    @RequestMapping("/wx_refundUser")
    public List<User> wx_refundUser(HttpServletRequest request) {
        //实例化
        User rUser = new User();

        String user_id = request.getParameter("user_id");
        int refund = Integer.valueOf(request.getParameter("refund"));

        rUser.setUser_id(user_id);
        rUser.setUser_money(refund);
        List<User> refundUser = userDao.wx_refundUser(rUser);
        return refundUser;
    }
}
