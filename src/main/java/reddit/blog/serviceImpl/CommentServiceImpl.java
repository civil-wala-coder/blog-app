package reddit.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.blog.entity.Comment;
import reddit.blog.entity.Post;
import reddit.blog.entity.User;
import reddit.blog.exceptions.ResourceNotFoundException;
import reddit.blog.payloads.CommentDto;
import reddit.blog.repository.CommentRepo;
import reddit.blog.repository.PostRepo;
import reddit.blog.repository.UserRepo;
import reddit.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", " id ",userId));
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post"," id ",postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment"," id ",commentId));
        this.commentRepo.delete(comment);

    }
}
