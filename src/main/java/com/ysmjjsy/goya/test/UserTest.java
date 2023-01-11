//package com.ysmjjsy.goya.test;
//
//import cn.hutool.cache.CacheUtil;
//import cn.hutool.cache.impl.FIFOCache;
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.lang.Console;
//import cn.hutool.core.thread.ThreadUtil;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.core.util.RandomUtil;
//import com.ysmjjsy.goya.entity.User;
//import net.bytebuddy.implementation.bytecode.Throw;
//import org.apache.commons.compress.utils.Lists;
//
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//public class UserTest {
//    public static void main(String[] args) {
//        FIFOCache<String, Object> objects = CacheUtil.newFIFOCache(10000);
//        ArrayList<User> users = new ArrayList<>();
//        User user = null;
//        for (int i = 0; i < 10000; i++) {
//            user = new User();
//            user.setUserName(Thread.currentThread().getName());
//            user.setAge(RandomUtil.randomInt(1, 100));
//            user.setEmail("21345@qq.com");
//            user.setGender(1);
//            user.setIdNumber(Convert.toInt(IdUtil.getSnowflakeNextId()));
//            users.add(user);
//        }
//        ThreadUtil.execAsync(() -> {
//            /**
//             查到一条就终止
//             */
//            User user1 = users.stream().filter(x -> x.getAge() == 50).findFirst().orElse(null);
//            Console.log("show info:{}", user1);
//            /**
//             * 查所有
//             */
//            Optional.ofNullable(users.stream()
//                    .filter(x -> x.getAge() == 50)
//                    .collect(Collectors.toList()))
//                    .ifPresent(x -> Console.log("show info:{}", x));
//            Console.log(user1);
//        });
//
//        for (int i = 0; i <users.size() ; i++) {
//            if (users.get(i).getAge()==50){
//                Console.log("show info:{}", users.get(i));
//            }
//        }
//
//
//    }
//}
