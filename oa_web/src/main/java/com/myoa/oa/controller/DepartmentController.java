package com.myoa.oa.controller;

import com.myoa.oa.biz.DepartmentBiz;
import com.myoa.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("departmentController")
@RequestMapping("/department") //根目录下/department
public class DepartmentController {
    @Autowired
    @Qualifier("departmentBiz")
    private DepartmentBiz departmentBiz;

    //参数是map这样写不依赖mvc
    // 路径相当于是 /department/list
    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("list",departmentBiz.getAll());
        //转发
        return "department_list";
    }

    @RequestMapping("/to_add")
    public String toadd(Map<String,Object> map) {
        //键值和页面中modelAttribute一致
        //也要和下面的add中的参数名一致
        // 相当于从页面获得数据，自动封装为dep对象
        map.put("department",new Department());
        //转发
        return "department_add";
    }

    @RequestMapping("/add")
    public String add(Department department) {
         departmentBiz.add(department);
         return "redirect:list";
    }


    @RequestMapping(value = "/to_update",params = "sn")
    public String toupdate(String sn,Map<String,Object> map) {
        //键值和页面中modelAttribute一致
        //也要和下面的add中的参数名一致
        // 相当于从页面获得数据，自动封装为dep对象
        map.put("department",departmentBiz.get(sn));
        //转发
        return "department_update";
    }

    @RequestMapping("/update")
    public String update(Department department) {
        departmentBiz.edit(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove",params = "sn")
    public String update(String sn) {
        departmentBiz.remove(sn);
        return "redirect:list";
    }
}
