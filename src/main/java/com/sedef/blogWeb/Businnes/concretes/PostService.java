package com.sedef.blogWeb.Businnes.concretes;
import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.abstracts.Ipost;
import com.sedef.blogWeb.Model.Post;
import com.sedef.blogWeb.Model.SubCategory;
import com.sedef.blogWeb.Repository.PostRepository;
import com.sedef.blogWeb.Request.PostRequest;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements Ipost {
    PostRepository postRepository;
    SubCategoryService subCategoryService;

    public PostService(PostRepository postRepositoy, SubCategoryService subCategoryService) {
        this.postRepository = postRepositoy;
        this.subCategoryService = subCategoryService;
    }

    @Override
    public Post addPost(PostRequest postRequest) throws NotFoundException {

        if (postRequest == null) {
            throw new NotFoundException("PostRequest is null ");
        }
        else if (postRequest.getStatusActive() == null) {
            throw new NotFoundException("getStatusActive is null ");
        } else if (postRequest.getSubCategoryId() == 0){
            throw new NotFoundException("getSubCategoryId is 0 ");
        }
        else {
            SubCategory subCategory = subCategoryService.getOneSubCategoryById(postRequest.getSubCategoryId());
            Post post = new Post();
            post.setTitle(postRequest.getTitle());
            post.setText(postRequest.getText());
            post.setStatusActive(postRequest.getStatusActive());
            post.setSubCategory(subCategory);
            post.setDate(new Date());
            return postRepository.save(post);
        }
    }

    @Override
    public Post updatePost(PostRequest postRequest,int id) throws NotFoundException {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (postRequest.getTitle() != null) {
                post.setTitle(postRequest.getTitle());
            }

            if (postRequest.getText() != null) {
                post.setText(postRequest.getText());
            }

            if (postRequest.getStatusActive() != null) {
                post.setStatusActive(postRequest.getStatusActive());
            }

            post.setDate(new Date());


            return postRepository.save(post);
        } else {
            throw new NotFoundException("update save error");
        }
    }

    @Override
    public Post getOnePostById(int id) throws NotFoundException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
             return optionalPost.get();}
        else {
            throw new NotFoundException("ID'si " + id + " olan Post bulunamadı");
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePostById(int id) throws NotFoundException {
        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);}
        else
            throw new NotFoundException("Post with ID not found: " + id);
    }
}
