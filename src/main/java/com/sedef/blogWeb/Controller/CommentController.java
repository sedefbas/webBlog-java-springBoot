package com.sedef.blogWeb.Controller;
import Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.concretes.CommentService;
import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.Request.CommentRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

   CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

  @PostMapping("/add")
  public Comment addComment(@RequestBody CommentRequest commentRequest) throws NotFoundException {
        return commentService.addComment(commentRequest);
  }
  @PostMapping("/{id}")
  public Comment updateComment(@RequestBody CommentRequest commentRequest,@PathVariable int id) throws NotFoundException {
        return commentService.updateComment(commentRequest,id);
  }

  @GetMapping("/getOne/{id}")
  public Comment getOneCommentById(@PathVariable int id) throws NotFoundException {
        return commentService.getOneCommentById(id);
  }

  @GetMapping("/getAll")
  public List<Comment> getAllComments(){
        return commentService.getAll();
  }


  @DeleteMapping("/{id}")
  public void deleteCommentbyId(@PathVariable int id) throws NotFoundException {
      commentService.getOneCommentById(id);
  }


}
