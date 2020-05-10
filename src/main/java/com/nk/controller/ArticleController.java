package com.nk.controller;

import com.nk.mapper.ArticleMapper;
import com.nk.model.Article;
import com.nk.model.Category;
import com.nk.model.Comment;
import com.nk.model.User;
import com.nk.service.ArticleSercice;
import com.nk.service.CategoryService;
import com.nk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleSercice articleSercice;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Article> articles = articleSercice.queryArticles();
        model.addAttribute("articleList", articles);
        return "index";
    }

    @RequestMapping("/a/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Article article = articleSercice.queryArticle(id);
        List<Comment> commentList = commentService.queryComments(id);
        article.setCommentList(commentList);
        model.addAttribute("article", article);
        return "info";
    }

    @RequestMapping("/writer")
    public String writer(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Article> articleList = articleSercice.queryArticlesByUserId(user.getId());
        model.addAttribute("articleList", articleList);

        List<Category> categoryList = categoryService.queryCategoriesByUserId(user.getId());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("activeCid",categoryList.get(0).getId());
        return "writer";
    }

    @RequestMapping("/writer/forward/{type}/{id}/editor")
    public String editor(@PathVariable("type") Integer type, @PathVariable("id") Long id, Model model) {
        model.addAttribute("type", type);
        return "editor";
    }
}
