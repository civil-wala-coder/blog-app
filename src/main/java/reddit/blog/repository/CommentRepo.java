package reddit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reddit.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
