package com.sedef.blogWeb.Repository;

import com.sedef.blogWeb.Model.Category;
import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.enums.StatusConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment,Integer> {
    List<Comment> findByStatusConfirmation(StatusConfirmation statusConfirmation);
    List<Comment> findByStatusConfirmationAndPostId(StatusConfirmation statusConfirmation, int postId);

}
