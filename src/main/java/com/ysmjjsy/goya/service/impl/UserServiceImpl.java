package com.ysmjjsy.goya.service.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysmjjsy.goya.entity.User;
import com.ysmjjsy.goya.mapper.UserMapper;
import com.ysmjjsy.goya.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static sun.misc.Version.println;

/**
 * @author goya
 * @create 2022-03-18 22:06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User getUserView(int age) {

        String sql = "select * from user_view where age = ?";
        try {
            List<Entity> query = Db.use().query(sql, age);
            query.forEach(q->{
                System.out.println(q.toString());
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        int age = 25;
        String sql = "select * from user_view where age = ?";
        try {
            List<Entity> query = Db.use().query(sql, age);
            query.forEach(q->{
                System.out.println("--------"+q.getStr("user_name"));
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            List<Entity> entities = Db.use().find(Entity.create("user_view").set("age", 25));
            entities.forEach(e->{
                System.out.println(e.getStr("GENDER"));
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
