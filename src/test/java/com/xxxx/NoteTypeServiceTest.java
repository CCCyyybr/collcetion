package com.xxxx;

import com.xxxx.pojo.NoteType;
import com.xxxx.service.NoteTypeService;
import com.xxxx.service.UserService;
import org.junit.Before;
import org.junit.Test;

public class NoteTypeServiceTest {
    private NoteTypeService noteTypeService;


    @Before
    public void init(){
        System.out.println("测试方法执行前执行.......");
        noteTypeService=new NoteTypeService();
    }

    @Test
    public void addNoteType(){
        noteTypeService.addNoteType(new NoteType(4,"111",1));
    }

    @Test
    public void listNoteType(){
        noteTypeService.listNoteType(1);
    }
}
