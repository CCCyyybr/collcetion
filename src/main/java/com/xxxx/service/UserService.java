package com.xxxx.service;


import com.xxxx.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

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


    /**
     * 更新
     * @param user
     */
    public void updateUser(User user){
        if(null == user){
            throw  new RuntimeException("用户记录为空!");
        }
        if(StringUtils.isBlank(user.getUserName())){
            throw  new RuntimeException("用户名称不能为空!");
        }
        if(StringUtils.isBlank(user.getUserPwd())){
            throw  new RuntimeException("用户密码不能为空!");
        }
        if(StringUtils.isBlank(user.getNick())){
            throw  new RuntimeException("用户昵称不能为空!");
        }

        if(user.getId()==null|| null == findUserByUserId(user.getId())){
            throw  new RuntimeException("待更新的记录不存在!");
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(user.getUserName()) && !(users.get(i).getId().equals(user.getId()))){
                throw new RuntimeException("用户名已存在");
            }
        }

        users.set(users.indexOf(findUserByUserId(user.getId())),user);

    }

    private User findUserByUserId(Integer userId) {
        Optional<User> optionalUser = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }


    public void delUser(Integer userId){
        User result = this.findUserByUserId(userId);
        if (result == null){
            throw new RuntimeException("待删除记录不存在");
        }
        users.remove(result);

    }

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     */
    public void login(String userName,String userPwd){
        /**
         *  1.判空
         *     用户名 密码非空校验
         *  2.根据用户名查找用户记录
         *  3.记录存在判断
         *     不存在-->抛异常
         *  4.用户存在
         *      用户密码比较
         *         不正确-->抛异常
         *  5.密码正确
         *      登录成功
         */
        if(StringUtils.isBlank(userName)){
            throw  new RuntimeException("用户名称不能为空!");
        }
        if(StringUtils.isBlank(userPwd)){
            throw  new RuntimeException("用户密码不能为空!");
        }

        Integer index=null;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserName().equals(userName)){
                index=i;
                break;
            }
        }
        if(index==null){
            throw  new RuntimeException("用户记录不存在!");
        }
        User temp = users.get(index);
        /*Optional<User> optionalUser = users.stream()
                                        .filter(u -> u.getUserName().equals(userName))
                                        .findFirst();
        if (!(optionalUser.isPresent())) {
            throw  new RuntimeException("用户记录不存在!");
        }*/
        //User temp = optionalUser.get();
        if(!temp.getUserPwd().equals(userPwd)){
            throw new RuntimeException("用户密码错误!");
        }
        System.out.println("用户登录成功!");
    }

}
