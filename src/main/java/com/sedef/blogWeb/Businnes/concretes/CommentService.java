package com.sedef.blogWeb.Businnes.concretes;
import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.abstracts.IComment;
import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.Model.Post;
import com.sedef.blogWeb.Model.User;
import com.sedef.blogWeb.Repository.CommentRepository;
import com.sedef.blogWeb.Request.CommentRequest;
import com.sedef.blogWeb.enums.StatusConfirmation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements IComment {

    CommentRepository commentRepository;
    PostService postService;
    UserService userService;

    public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }


    @Override
    public Comment addComment(CommentRequest commentRequest) throws NotFoundException {
        if (commentRequest == null) {
            throw new NotFoundException("CommentRequest is null");
        } else if (commentRequest.getText() == null) {
            throw new NotFoundException("Text is null");
        }
       else  {
            Optional<Post> post = Optional.ofNullable(postService.getOnePostById(commentRequest.getPostId()));
            Optional<User> user = Optional.ofNullable(userService.getOneUserById(commentRequest.getUserId()));

            if (post.isPresent() && user.isPresent()) {
                User user1 = user.get();
                Post post1 = post.get();

                Comment comment = new Comment();
                comment.setText(commentRequest.getText());
                comment.setStatusConfirmation(commentRequest.getStatusConfirmation());
                comment.setUser(user1);
                comment.setPost(post1);
                return commentRepository.save(comment);
            } else {
                throw new NotFoundException("Post or User not found");
            }
        }

    }

    @Override
    public Comment updateComment(CommentRequest commentRequest, int id) throws NotFoundException {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (commentRequest.getText() == null) {
                throw new NotFoundException("Text is null");
            }

            if (commentRequest.getPostId() != 0) {
                Optional<Post> post = Optional.ofNullable(postService.getOnePostById(commentRequest.getPostId()));
                if (post.isPresent()) {
                    comment.setPost(post.get());
                } else {
                    throw new NotFoundException("Post not found with the given ID");
                }
            }

            if (commentRequest.getUserId() != 0) {
                Optional<User> user = Optional.ofNullable(userService.getOneUserById(commentRequest.getUserId()));
                if (user.isPresent()) {
                    comment.setUser(user.get());
                } else {
                    throw new NotFoundException("User not found with the given ID");
                }
            }
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        } else {
            throw new NotFoundException("Comment not found with the given ID");
        }
    }

    @Override
    public Comment getOneCommentById(int id) throws NotFoundException {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()){
           return optionalComment.get();
        }
        else {
            throw new NotFoundException("ID'si " + id + " olan Post bulunamadı");
        }
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteCommentById(int id) throws NotFoundException {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else throw new NotFoundException("Post with ID not found: " + id);
    }

    //adminin onay yapabilmesi için oluşturdum.
    public List<Comment> getAllNotConfirmedComments() {
        return commentRepository.findByStatusConfirmation(StatusConfirmation.NOT_CONFIRMATION);
    }

   //admin onaylanan tüm commentsleri görsün.
    public List<Comment> getConfirmedComments() {
        return commentRepository.findByStatusConfirmation(StatusConfirmation.CONFIRMATION);
    }

    public List<Comment> getConfirmedCommentsByPostId(int postId) {
        return commentRepository.findByStatusConfirmationAndPostId(StatusConfirmation.CONFIRMATION, postId);
    }

    public List<Comment> getNotConfirmedCommentsByPostId(int postId) {
        return commentRepository.findByStatusConfirmationAndPostId(StatusConfirmation.NOT_CONFIRMATION, postId);
    }


}
