package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.CardTags;

@Repository
public interface CardTagsRepository extends JpaRepository<CardTags, Long>{

}
