package com.xxxx.service;


import com.xxxx.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 云日记-用户管理模块
 *    用户登录
 *    用户注册
 *    用户列表展示
 *    用户信息更新
 *    用户信息删除
 */
public class UserService {
    /**
     * 用户记录如何存储???
     *    List-->ArrayList<User>
     *    Map-->HashMap<Integer,User>
     *    Set-->HashSet<User>
     *    List<Map<k,v>>
     */
    private List<User> users;

    public UserService() {
        users =new ArrayList<User>();
        users.add(new User(1,"admin","123456","admin","",""));
        users.add(new User(2,"test","123456","test","",""));
    }

    /**
     * 添加用户记录
     * @param user
     */
    public void addUser(User user){
        /**
         * 1.参数合法校验
         *     用户名 密码 昵称 非空
         * 2.用户名唯一  & 昵称唯一
         * 3.执行添加 返回结果
         */
        if(null == user){
            throw  new RuntimeException("用户记录为空!");
        }
        if(StringUtils.isBlank(user.getUserName())){
            throw  new RuntimeException("用户名称不能为空!");
        }
        if(StringUtils.isBlank(user.getUserPwd())){
            throw  new RuntimeException("用户密码不能为空!");
        }
        /**
         * 用户名唯一校验
         */
        /*for (User temp : users) {
            if(temp.getUserName().equals(user.getUserName())){
                throw  new RuntimeException("用户名已存在!");
            }
        }*/
        if(users.contains(user)){
            throw new RuntimeException("用户名已存在!");
        }
       /*long count = users.stream()
                            .filter(u->u.getUserName().equals(user.getUserName()))
                            .count();
       if(count==1){
           throw  new RuntimeException("用户名已存在!");
       }*/
        users.add(user);
    }


    public void listUser(){
       /* for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }*/
       /* for (User user : users) {
            System.out.println(user);
        }*/
        /*Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
        ListIterator<User> userListIterator = users.listIterator();
        while (userListIterator.hasNext()){
            System.out.println(userListIterator.next());
        }
        /*users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });*/
        /*users.forEach(u->{
            System.out.println(u);
        });*/
    }


    public void updateUser(User user){

    }


    public void delUser(Integer userId){

    }
}
