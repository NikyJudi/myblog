package com.nk.service;

import com.nk.mapper.ArticleMapper;
import com.nk.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleSercice {

    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> queryArticles() {
        return articleMapper.selectAll();
    }

    public Article queryArticle(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public List<Article> queryArticlesByUserId(Long id) {
        return articleMapper.queryArticlesByUserId(id);
    }
}
