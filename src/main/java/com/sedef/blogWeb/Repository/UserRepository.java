package com.sedef.blogWeb.Repository;
import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String userName);
}
