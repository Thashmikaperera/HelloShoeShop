package lk.ijse.helloshoeshop.conversion;

import lk.ijse.helloshoeshop.dto.*;
import lk.ijse.helloshoeshop.entity.*;
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

    public SupplierDTO convertToSupplierDTO(Optional<SupplierEntity> supplierEntity){
        return modelMapper.map(supplierEntity, SupplierDTO.class);
    }

    public SupplierEntity convertToSupplierEntity(Optional<SupplierDTO> supplierDTO){
        return modelMapper.map(supplierDTO, SupplierEntity.class);
    }

    public List<SupplierDTO> getSupplierDTOList(List<SupplierEntity> supplierEntities){
        return modelMapper.map(supplierEntities,List.class);
    }

    public List<SupplierEntity> getSupplierEntityList(List<SupplierDTO> supplierDTOS){
        return modelMapper.map(supplierDTOS,List.class);
    }

    public UserEntity toUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,UserEntity.class);
    }

    public UserDTO toUserDTO(UserEntity userEntity){
        return modelMapper.map(userEntity,UserDTO.class);
    }

    public GenderEntity toGenderEntity(GenderDTO genderDTO) {
        return modelMapper.map(genderDTO, GenderEntity.class);
    }

    public GenderDTO toGenderDTO(GenderEntity genderEntity){
        return modelMapper.map(genderEntity, GenderDTO.class);
    }

    public List<GenderDTO> convertToGenderDTO(List<GenderEntity> genderEntities) {
        return modelMapper.map(genderEntities,List.class);
    }

    public OccasionDTO convertToOccasionDTO(OccasionEntity occasion) {
        return modelMapper.map(occasion, OccasionDTO.class);
    }
    public OccasionEntity convertToOccasionEntity(OccasionDTO occasionDTO) {
        return modelMapper.map(occasionDTO, OccasionEntity.class);
    }

    public List<OccasionDTO> convertToOccasionDTOList(List<OccasionEntity> occasion){
        return modelMapper.map(occasion, List.class);
    }

    public VarietyDTO convertToVarietyDTO(VarietyEntity variety) {
        return modelMapper.map(variety, VarietyDTO.class);
    }
    public VarietyEntity convertToVarietyEntity(VarietyDTO varietyDTO) {
        return modelMapper.map(varietyDTO, VarietyEntity.class);
    }

    public List<VarietyDTO> convertToVarietyDTOList(List<VarietyEntity> varieties){
        return modelMapper.map(varieties, List.class);
    }

    public ItemDTO convertToItemDTO(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDTO.class);
    }
    public ItemEntity convertToItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }

    public List<ItemDTO> convertToItemDTOList(List<ItemEntity> items){
        return modelMapper.map(items, List.class);
    }
}
