package com.nk.controller;

import com.nk.model.Comment;
import com.nk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/a/{id}/comments", method = RequestMethod.POST)
    //id自动注入
    //content自动注入,默认为get请求，添加post请求，Content-Type: application/x-www-form-urlencoded 默认表单
    public String addComment(@PathVariable("id") Long id, String content) {
        Comment comment = new Comment();
        comment.setArticleId(id);
        comment.setContent(content);
        comment.setCreatedAt(new Date());
        //感觉可以用session再添加信息
        if (content != null) {
            int num = commentService.insert(comment);
        }
        //springMvc重定向
        return "redirect:/a/" + id;
    }
}
