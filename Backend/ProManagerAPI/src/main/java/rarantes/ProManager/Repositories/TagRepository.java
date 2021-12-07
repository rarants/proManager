package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

}
