package com.lhy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.mapper.BlogMapper;
import com.lhy.mapper.TypeMapper;
import com.lhy.pojo.Blog;
import com.lhy.pojo.Type;
import com.lhy.pojo.TypeQuery;
import com.lhy.service.BlogService;
import com.lhy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;
    //    分页查询分类
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {

        List<TypeQuery> types = typeService.getNumType1();
        System.out.println("types:"+types);
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = types.get(0).getId();
        }

        Blog blog = new Blog();
        blog.setTypeId(id);
        model.addAttribute("types", types);
        List<Blog> blogs = blogService.getAllBlog(blog);

        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}