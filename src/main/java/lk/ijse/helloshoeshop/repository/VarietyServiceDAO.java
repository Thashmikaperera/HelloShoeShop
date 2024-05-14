package lk.ijse.helloshoeshop.repository;

import lk.ijse.helloshoeshop.entity.VarietyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarietyServiceDAO extends JpaRepository<VarietyEntity, String> {
}
