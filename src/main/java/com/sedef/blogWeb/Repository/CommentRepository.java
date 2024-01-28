package com.sedef.blogWeb.Repository;

import com.sedef.blogWeb.Model.Category;
import com.sedef.blogWeb.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment,Integer> {
}
