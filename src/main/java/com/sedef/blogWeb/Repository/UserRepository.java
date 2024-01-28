package com.sedef.blogWeb.Repository;
import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
