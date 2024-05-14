package lk.ijse.helloshoeshop.repository;

import lk.ijse.helloshoeshop.entity.OccasionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccasionServiceDAO extends JpaRepository<OccasionEntity, String> {
}
