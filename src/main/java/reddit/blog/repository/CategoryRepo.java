package reddit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reddit.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
