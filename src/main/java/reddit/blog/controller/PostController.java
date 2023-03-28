package reddit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reddit.blog.payloads.ApiResponse;
import reddit.blog.payloads.PostDto;
import reddit.blog.service.PostService;
import reddit.blog.serviceImpl.PostServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    // Create Post
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        return new ResponseEntity<PostDto>(this.postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
    }


    // Get According to User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    // Get According to Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    // Get All Posts
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(this.postService.getAllPosts(), HttpStatus.OK);
    }


    // Get Post By id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        return new ResponseEntity<>(this.postService.getSinglePost(postId), HttpStatus.OK);
    }


    // Delete Post
    @DeleteMapping("/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("post is successfully deleted", true);
    }


    // Update Post
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, postId), HttpStatus.OK);
    }
}
