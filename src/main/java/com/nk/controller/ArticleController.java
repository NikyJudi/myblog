package com.nk.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    public String writer(HttpSession session, Model model, Long activeCid) {
        User user = (User) session.getAttribute("user");
        List<Article> articleList = articleSercice.queryArticlesByUserId(user.getId());
        model.addAttribute("articleList", articleList);

        List<Category> categoryList = categoryService.queryCategoriesByUserId(user.getId());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("activeCid", activeCid == null ? categoryList.get(0).getId() : activeCid);
        return "writer";
    }

    /**
     *
     * @param type 新增为1，修改为2
     * @param id 新增是activeCid 修改是articleId
     *      二者都需要category对象
     *      修改需要article对象
     * @param model
     * @return
     */
    @RequestMapping("/writer/forward/{type}/{id}/editor")
    public String editor(@PathVariable("type") Integer type, @PathVariable("id") Long id, Model model) {
        Category category = null;
        if (type == 1) {
            //新增
            category = categoryService.queryCategoriesById(id);
            model.addAttribute("activeCid", id);
        } else {
            //修改
            //待优化：queryArticle，queryCategoriesById 两次优化成一次 sql 查询 ：
            //          Article中再定义一个category属性，xml加入association
            Article article = articleSercice.queryArticle(id);
            category = categoryService.queryCategoriesById(Long.valueOf(article.getCategoryId()));
            model.addAttribute("article", article);
        }
        model.addAttribute("type", type);
        model.addAttribute("category", category);
        return "editor";
    }

    @RequestMapping(value = "/writer/article/{type}/{id}", method = RequestMethod.POST)
    public String submit(@PathVariable("type") Integer type, @PathVariable("id") Integer id,
                         HttpSession session, Article article) {
        article.setUpdatedAt(new Date());
        if (type == 1) {
            //新增插入
            User user = (User) session.getAttribute("user");
            article.setUserId(user.getId());
            article.setCategoryId(id);
            article.setCoverImage("https://picsum.photos/id/1/400/300");
            article.setCreatedAt(new Date());
            article.setStatus((byte) 0);
            article.setViewCount(0L);
            article.setCommentCount(0);
            int num = articleSercice.insert(article);
            id = article.getId().intValue();
        } else {
            //修改
            article.setId(Long.valueOf(id));
            int num = articleSercice.update2(article);
        }
        return String.format("redirect:/writer/forward/2/%s/editor", id);
    }

    @RequestMapping("/writer/forward/{articleId}/delete")
    public String delete (@PathVariable("articleId") Long articleId) {

        articleSercice.delete(articleId);
        return "redirect:/writer";
    }
}
