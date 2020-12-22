package curso.springframework.mvcrest.repositories;

import curso.springframework.mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
