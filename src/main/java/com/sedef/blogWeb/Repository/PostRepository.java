package com.sedef.blogWeb.Repository;
import com.sedef.blogWeb.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {


}
