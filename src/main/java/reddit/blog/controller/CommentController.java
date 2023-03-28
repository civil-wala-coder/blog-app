package reddit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reddit.blog.payloads.ApiResponse;
import reddit.blog.payloads.CommentDto;
import reddit.blog.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{PostId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable Integer userId,
                                                    @PathVariable Integer PostId){
        System.out.println("commentDto =>"+commentDto.getContent());
        System.out.println("userId =>"+userId);
        System.out.println("PostId =>"+PostId);
        CommentDto addedComment = this.commentService.createComment(commentDto, userId, PostId);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment delete seuccesfully !!",true), HttpStatus.OK);
    }
}
