package com.sedef.blogWeb.Businnes.concretes;

import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.Repository.CommentRepository;
import com.sedef.blogWeb.enums.StatusConfirmation;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminconfirmationService {
    CommentRepository commentRepository;

    public AdminconfirmationService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void setCommentsAsConfirmed(int id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if(optionalComment.isPresent()){
          Comment comment = optionalComment.get();
            comment.setStatusConfirmation(StatusConfirmation.CONFIRMATION);
            commentRepository.save(comment);
            System.out.println("Comment status changed to CONFIRMATION");
        }
        else  System.out.println("Comment with id " + id + " not found.");
    }

}
