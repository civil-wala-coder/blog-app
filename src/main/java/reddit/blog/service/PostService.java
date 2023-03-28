package reddit.blog.service;

import reddit.blog.payloads.PostDto;
import reddit.blog.payloads.UserDto;

import java.util.List;

public interface PostService {

    // Create Post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // Update
    PostDto updatePost(PostDto postDto, Integer postId);

    // Delete
    void deletePost(Integer postId);

    // get Single Post
    PostDto getSinglePost(Integer postId);

    // get All Posts
    List<PostDto> getAllPosts();

    // get post by searching
    List<PostDto> getBySearch(String searchKey);


    // get posts by User
    List<PostDto> getPostsByUser(Integer userId);


    // get posts by Category
    List<PostDto> getPostsByCategory(Integer categoryId);
}
