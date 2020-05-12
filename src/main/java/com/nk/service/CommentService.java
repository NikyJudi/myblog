package com.nk.service;

import com.nk.mapper.CommentMapper;
import com.nk.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> queryComments(Long id) {
        return commentMapper.queryComments(id);
    }

    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    public int queryCommentsCount(Long id) {
        return commentMapper.queryCount(id.intValue());
    }
}
