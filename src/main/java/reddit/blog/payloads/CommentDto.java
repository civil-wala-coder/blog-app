package reddit.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reddit.blog.entity.Post;
import reddit.blog.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private int id;
    private String content;
 //   private User user;
   // private Post post;
}
