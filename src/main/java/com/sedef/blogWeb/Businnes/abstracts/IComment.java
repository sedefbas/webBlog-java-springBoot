package com.sedef.blogWeb.Businnes.abstracts;

import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.Request.CommentRequest;

import java.util.List;

public interface IComment {
    Comment addComment(CommentRequest commentRequest) throws NotFoundException;
    Comment updateComment(CommentRequest commentRequest, int id) throws NotFoundException;
    Comment getOneCommentById(int id) throws NotFoundException;
    List<Comment> getAll();
    void deleteCommentById(int id) throws NotFoundException;

}
