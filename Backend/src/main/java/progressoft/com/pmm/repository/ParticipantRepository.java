package progressoft.com.pmm.repository;

import com.example.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progressoft.com.pmm.entity.ParticipantEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    Optional<ParticipantEntity> findByCode(String code);
    Optional<ParticipantEntity> findByBic(String bic);
    List<ParticipantEntity> findByType(Type type);
}
