package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.Enum.Level;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.SupplierDTO;
import lk.ijse.helloshoeshop.entity.CustomerEntity;
import lk.ijse.helloshoeshop.entity.EmployeeEntity;
import lk.ijse.helloshoeshop.entity.SupplierEntity;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.SupplierServiceDAO;
import lk.ijse.helloshoeshop.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SupplierServiceIMPL implements SupplierService {
   final private SupplierServiceDAO supplierServiceDAO;
   final private ConversionData conversionData;
    @Override
    public void saveSupplier(SupplierDTO supplierDTO) {
        supplierDTO.setSupplierCode(getNextSupplierCode());
        SupplierEntity supplierEntity = conversionData.convertToSupplierEntity(Optional.of(supplierDTO));
        supplierServiceDAO.save(supplierEntity);
    }

    @Override
    public SupplierDTO getSupplier(String id) {
        if (!supplierServiceDAO.existsById(id))throw new NotFoundException("Supplier Not Found");
        return conversionData.convertToSupplierDTO(supplierServiceDAO.findById(id));
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return conversionData.getSupplierDTOList(supplierServiceDAO.findAll());
    }

    @Override
    public void deleteSupplier(String id) {
        if (!supplierServiceDAO.existsById(id)) throw new NotFoundException("Supplier Not Found");
        supplierServiceDAO.deleteById(id);
    }

    @Override
    public void UpdateSullier(String id, SupplierDTO supplierDTO) {
        Optional<SupplierEntity> supplierEntity =supplierServiceDAO.findById(id);
        if (supplierEntity.isEmpty()) throw new NotFoundException("Supplier Not Found");
        supplierEntity.get().setSupplierName(supplierDTO.getSupplierName());
        supplierEntity.get().setCategory(supplierDTO.getCategory());
        supplierEntity.get().setAddress1(supplierDTO.getAddress1());
        supplierEntity.get().setAddress2(supplierDTO.getAddress2());
        supplierEntity.get().setAddress3(supplierDTO.getAddress3());
        supplierEntity.get().setAddress4(supplierDTO.getAddress4());
        supplierEntity.get().setPostalCode(supplierDTO.getPostalCode());
        supplierEntity.get().setCountry(supplierDTO.getCountry());
        supplierEntity.get().setContactNo1(supplierDTO.getContactNo1());
        supplierEntity.get().setContactNo2(supplierDTO.getContactNo2());
        supplierEntity.get().setEmail(supplierDTO.getEmail());
    }

    private String getNextSupplierCode() {
        SupplierEntity firstByOrderBySupplierCodeDesc = supplierServiceDAO.findFirstByOrderBySupplierCodeDesc();
        return (firstByOrderBySupplierCodeDesc != null) ?
                String.format("Sup-%03d",Integer.parseInt(firstByOrderBySupplierCodeDesc.getSupplierCode()
                        .replace("Sup-",""))+1) : "Sup-001";
    }

}
