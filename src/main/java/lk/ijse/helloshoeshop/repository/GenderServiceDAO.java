package lk.ijse.helloshoeshop.repository;

import lk.ijse.helloshoeshop.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderServiceDAO extends JpaRepository<GenderEntity,String> {
}