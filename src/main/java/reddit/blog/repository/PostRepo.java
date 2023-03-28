package reddit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reddit.blog.entity.Category;
import reddit.blog.entity.Post;
import reddit.blog.entity.User;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
