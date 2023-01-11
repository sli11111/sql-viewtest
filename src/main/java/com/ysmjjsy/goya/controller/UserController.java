//package com.ysmjjsy.goya.controller;
//
//import com.baomidou.mybatisplus.extension.service.IService;
//import com.ysmjjsy.goya.base.BaseController;
//import com.ysmjjsy.goya.entity.User;
//import com.ysmjjsy.goya.service.UserService;
//import io.swagger.annotations.Api;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author goya
// * @create 2022-03-18 21:58
// */
//@RestController
//@RequestMapping("/api/user")
//@Api(tags = "用户信息")
//public class UserController extends BaseController<User> {
//
//    @Resource
//    private  UserService userService;
//
//    @Override
//    public IService<User> getService() {
//        return this.userService;
//    }
//
//    @GetMapping("/view")
//    public String getUserView(){
//        int age = 25;
//        User user = this.userService.getUserView(age);
//
//        return user.getUserName();
//    }
//
//
//
//
//
//
//}
