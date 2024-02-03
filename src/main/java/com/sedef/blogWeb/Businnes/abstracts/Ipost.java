package com.sedef.blogWeb.Businnes.abstracts;

import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Model.Post;
import com.sedef.blogWeb.Request.PostRequest;

import java.util.List;

public interface Ipost {
    Post addPost(PostRequest postRequest) throws NotFoundException;
    Post updatePost(PostRequest postRequest,int id) throws NotFoundException;
    Post getOnePostById(int id) throws NotFoundException;
    List<Post> getAllPosts();
    void deletePostById(int id) throws NotFoundException;
}
