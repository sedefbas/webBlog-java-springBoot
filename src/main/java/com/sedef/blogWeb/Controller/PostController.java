package com.sedef.blogWeb.Controller;
import Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.concretes.PostService;
import com.sedef.blogWeb.Model.Post;
import com.sedef.blogWeb.Request.PostRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public Post addPost(@RequestBody PostRequest postRequest) throws NotFoundException {
        return postService.addPost(postRequest);
    }

    @PostMapping("/{id}")
    public Post update(@RequestBody PostRequest postRequest, @PathVariable int id) throws NotFoundException {
        return postService.updatePost(postRequest,id);
    }

    @GetMapping("/getOne/{id}")
    public Post getOnePostById(@PathVariable int id) throws NotFoundException {
        return postService.getOnePostById(id);
    }

    @GetMapping("/getAll")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @DeleteMapping("/delete/{id}")
    public void deletePostById(@PathVariable int id) throws NotFoundException {
        postService.deletePostById(id);
    }
}
