package lk.ijse.helloshoeshop.repository;

import lk.ijse.helloshoeshop.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeServiceDAO extends JpaRepository<EmployeeEntity,String> {
    EmployeeEntity findFirstByOrderByEmployeeCodeDesc();
}
