package lk.ijse.helloshoeshop.conversion;

import lk.ijse.helloshoeshop.dto.CustomerDTO;
import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.entity.CustomerEntity;
import lk.ijse.helloshoeshop.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ConversionData {
    final private ModelMapper modelMapper;
    public CustomerDTO convertToCustomerDTO(Optional<CustomerEntity> customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity convertToCustomerEntity(Optional<CustomerDTO> customerDTO){
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public List<CustomerDTO> getCustomerDTOList(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities,List.class);
    }

    public List<CustomerEntity> getCustomerEntityList(List<CustomerEntity> customerDtos){
        return modelMapper.map(customerDtos,List.class);
    }

    public EmployeeDTO convertToEmployeeDTO(Optional<EmployeeEntity> employeeEntity){
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public  EmployeeEntity convertToEmployeeEntity(Optional<EmployeeDTO> employeeDTO){
        return modelMapper.map(employeeDTO,EmployeeEntity.class);
    }

    public List<EmployeeDTO> getEmployeeDTOList(List<EmployeeEntity> employeeEntities){
        return modelMapper.map(employeeEntities,List.class);
    }

    public List<EmployeeEntity> getEmployeeEntityList(List<EmployeeEntity> employeeDtos){
        return modelMapper.map(employeeDtos,List.class);
    }
}
