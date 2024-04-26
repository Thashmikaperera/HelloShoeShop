package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.Enum.Status;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.entity.EmployeeEntity;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.EmployeeServiceDAO;
import lk.ijse.helloshoeshop.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {
    final private EmployeeServiceDAO employeeServiceDAO;
    final private ConversionData conversionData;
    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(getNextEployeeCode());
        EmployeeEntity employeeEntity=conversionData.convertToEmployeeEntity(Optional.of(employeeDTO));
        employeeServiceDAO.save(employeeEntity);
    }

    @Override
    public EmployeeDTO getEmployee(String id) {
        if (!employeeServiceDAO.existsById(id)) throw new NotFoundException("Employee Not Found");
        return conversionData.convertToEmployeeDTO(employeeServiceDAO.findById(id));
    }

    @Override
    public List<EmployeeDTO> getAllEmplyees() {
        return conversionData.getEmployeeDTOList(employeeServiceDAO.findAll());
    }

    @Override
    public void deleteEmplyee(String id) {
        if (!employeeServiceDAO.existsById(id)) throw new NotFoundException("Employee Not Found");
        employeeServiceDAO.deleteById(id);
    }

    @Override
    public void updateEmployee(String id, EmployeeDTO employeeDTO) {
        Optional<EmployeeEntity> employeeEntity =employeeServiceDAO.findById(id);
        if (employeeEntity.isEmpty()) throw new NotFoundException("Employee Not Found");
        employeeEntity.get().setEmployeeName(employeeDTO.getEmployeeName());
        employeeEntity.get().setProfilePic(employeeDTO.getProfilePic());
        employeeEntity.get().setGender(employeeDTO.getGender());
        employeeEntity.get().setStatus(employeeDTO.getStatus());
        employeeEntity.get().setDesignation(employeeDTO.getDesignation());
        employeeEntity.get().setDateOfBirth(employeeDTO.getDateOfBirth());
        employeeEntity.get().setDateOfJoin(employeeDTO.getDateOfJoin());
        employeeEntity.get().setAttachedBranch(employeeDTO.getAttachedBranch());
        employeeEntity.get().setAddress1(employeeDTO.getAddress1());
        employeeEntity.get().setAddress2(employeeDTO.getAddress2());
        employeeEntity.get().setAddress3(employeeDTO.getAddress3());
        employeeEntity.get().setAddress4(employeeDTO.getAddress4());
        employeeEntity.get().setPostalCode(employeeDTO.getPostalCode());
        employeeEntity.get().setContactNo(employeeDTO.getContactNo());
        employeeEntity.get().setEmail(employeeDTO.getEmail());
        employeeEntity.get().setEmergencyContactName(employeeDTO.getEmergencyContactName());
        employeeEntity.get().setEmergencyContact(employeeDTO.getEmergencyContact());
    }

    private String getNextEployeeCode() {
        EmployeeEntity firstByOrderByEmployeeCodeDesc = employeeServiceDAO.findFirstByOrderByEmployeeCodeDesc();
        return (firstByOrderByEmployeeCodeDesc != null)
                ? String.format("Emp-%03d",
                Integer.parseInt(firstByOrderByEmployeeCodeDesc.getEmployeeCode().
                        replace("Emp-", "")) + 1)
                : "Emp-001";
    }
}
