package com.xxxx.service;


import com.xxxx.pojo.NoteType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 云记类别管理
 *    云记类别遍历
 *    云记类别添加
 *    云记类别更新
 *    云记类别删除
 */
public class NoteTypeService {

    private Map<Integer, NoteType> noteTypeMap;
    UserService userService = new UserService();


    public NoteTypeService() {
        noteTypeMap= new HashMap<Integer, NoteType>();
        noteTypeMap.put(1,new NoteType(1,"java",1));
        noteTypeMap.put(2,new NoteType(2,"php",1));
        noteTypeMap.put(3,new NoteType(3,"scala",2));
    }

    public void addNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空 用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         * 2.当前用户下类别名称不可重复
         * 3.执行添加
         */
        if (noteType.getTypeName() == null){
            throw new RuntimeException("类别名不能为空");
        }

        if (userService.findUserByUserId(noteType.getUserId()) == null){
            throw new RuntimeException("该用户不存在");
        }

        for (NoteType type : noteTypeMap.values()) {
            if (noteType.getUserId().equals(type.getUserId()) &&
                    noteType.getTypeName().equals(type.getTypeName())){
                throw new RuntimeException("类别名重复");
            }
            if (noteType.getId().equals(type.getId())){
                throw new RuntimeException("云记id重复");
            }
        }
        int key = 4;
        noteTypeMap.put(key++,noteType);

    }

    /**
     * 根据登录用户查询云记类别
     * @param userId
     */


    public void listNoteType(Integer userId){
        Iterator it = noteTypeMap.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            NoteType value = noteTypeMap.get(key);
            if (value.getUserId().equals(userId)){
                System.out.println("key=" + key + "and value=" + value);
            }
        }
    }


    public void updateNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空
         *    用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         *    云记类别id 必须存在
         * 2.当前用户下类别名称不可重复
         * 3.执行更新
         */
        if (noteType.getTypeName() == null){
            throw new RuntimeException("类别名不能为空");
        }
        if (userService.findUserByUserId(noteType.getUserId()) == null){
            throw new RuntimeException("该用户不存在");
        }

        for (NoteType type : noteTypeMap.values()) {
            if (!(noteType.getId().equals(type.getId()))){
                throw new RuntimeException("云记id不存在");
            }
        }


    }

    public void delNoteType(Integer noteTypeId){
        /**
         * 1.参数校验
         *    云记类别id 必须存在
         * 2.如果类别下存在云记记录 不允许删除
         * 3.执行删除
         */


    }


}
