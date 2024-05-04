package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.Enum.Status;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.entity.EmployeeEntity;
import lk.ijse.helloshoeshop.entity.UserEntity;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.EmployeeServiceDAO;
import lk.ijse.helloshoeshop.repository.UserServiceDAO;
import lk.ijse.helloshoeshop.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EmployeeServiceIMPL implements EmployeeService {
    @Autowired
    final private EmployeeServiceDAO employeeServiceDAO;
    @Autowired
    final private UserServiceDAO userServiceDAO;
    @Autowired
    final private ConversionData conversionData;
    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(getNextEmployeeCode());
        log.info(employeeDTO.getEmail());
        Optional<UserEntity> userEntity = userServiceDAO.findByEmail(employeeDTO.getEmail());
        log.info("User Entity {]",userEntity);
        EmployeeEntity employee = conversionData.convertToEmployeeEntity(Optional.of(employeeDTO));
        if (userEntity.isEmpty()) {
            System.out.println("hi");
            throw new NotFoundException("User Not Found");
        }
        UserEntity newUser = new UserEntity();
        newUser.setId(userEntity.get().getId());
        newUser.setEmail(userEntity.get().getEmail());
        newUser.setPassword(userEntity.get().getPassword());
        newUser.setRole(userEntity.get().getRole());

        employee.setUserEntity(newUser);
        employeeServiceDAO.save(employee);
    }

    @Override
    public EmployeeDTO getEmployee(String id) {
        if(!employeeServiceDAO.existsById(id)){throw new NotFoundException("Employee Not Found");}
        return conversionData.convertToEmployeeDTO(Optional.ofNullable(employeeServiceDAO.findById(id).orElse(null)));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return conversionData.getEmployeeDTOList(employeeServiceDAO.findAll());
    }

    @Override
    public void deleteEmplyee(String id) {
        if (!employeeServiceDAO.existsById(id)) throw new NotFoundException("Employee Not Found");
        employeeServiceDAO.deleteById(id);
    }

    @Override
    public void updateEmployee(String id, EmployeeDTO employeeDTO) {
        if (!employeeServiceDAO.existsById(id)) {
            throw new NotFoundException("Employee Not Found");
        }
        Optional<EmployeeEntity> employeeEntity = employeeServiceDAO.findById(id);
        EmployeeEntity employee = employeeEntity.get();
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setGender(employeeDTO.getGender());
        employee.setStatus(employeeDTO.getStatus());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setDateOfJoin(employeeDTO.getDateOfJoin());
        employee.setAttachedBranch(employeeDTO.getAttachedBranch());
        employee.setAddress1(employeeDTO.getAddress1());
        employee.setAddress2(employeeDTO.getAddress2());
        employee.setAddress3(employeeDTO.getAddress3());
        employee.setAddress4(employeeDTO.getAddress4());
        employee.setPostalCode(employeeDTO.getPostalCode());
        employee.setContactNo(employeeDTO.getContactNo());
        employee.setEmail(employeeDTO.getEmail());
        employee.setEmergencyContactName(employeeDTO.getEmergencyContactName());
        employee.setEmergencyContact(employeeDTO.getEmergencyContact());

    }

    @Override
    public String getNextEmployeeCode() {
        EmployeeEntity employeeEntity = employeeServiceDAO.findFirstByOrderByEmployeeCodeDesc();
        return (employeeEntity != null)
                ? String.format("Emp-%03d",
                Integer.parseInt(employeeEntity.getEmployeeCode()
                        .replace("Emp-", "")) + 1)
                : "Emp-001";
    }


}
