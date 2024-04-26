package lk.ijse.helloshoeshop.service;

import lk.ijse.helloshoeshop.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployee(String id);
    List<EmployeeDTO> getAllEmplyees();
    void deleteEmplyee(String id);
    void updateEmployee(String id,EmployeeDTO employeeDTO);
}
