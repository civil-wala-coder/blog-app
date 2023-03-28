package reddit.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.blog.entity.Category;
import reddit.blog.entity.Post;
import reddit.blog.entity.User;
import reddit.blog.exceptions.ResourceNotFoundException;
import reddit.blog.payloads.PostDto;
import reddit.blog.repository.CategoryRepo;
import reddit.blog.repository.PostRepo;
import reddit.blog.repository.UserRepo;
import reddit.blog.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private PostRepo postRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

//        postDto.setImageName("pic.png");
//        postDto.setAddedDate(new Date());
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",categoryId));

        Post convertedRecord = this.modelMapper.map(postDto, Post.class);
        convertedRecord.setImageName("pic.png");
        convertedRecord.setAddedDate(new Date());
        convertedRecord.setUser(user);
        convertedRecord.setCategory(category);
        Post addedRecord = this.postRepo.save(convertedRecord);
        System.out.println("addedRecord =>"+addedRecord.getUser().getName());
        return this.modelMapper.map(addedRecord, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id ",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id ",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto getSinglePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id ",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPosts = this.postRepo.findAll();
        List<PostDto> postsDto = allPosts.stream().map(i-> this.modelMapper.map(i, PostDto.class)).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDto> getBySearch(String searchKey) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postsDto = posts.stream().map(i-> this.modelMapper.map(i, PostDto.class)).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category =  this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("User"," id ",categoryId));

        List<Post> wantedPosts = this.postRepo.findByCategory(category);
        List<PostDto> convtdList = wantedPosts.stream().map(i -> this.modelMapper.map(i, PostDto.class)).collect(Collectors.toList());
        return convtdList;
    }


}
