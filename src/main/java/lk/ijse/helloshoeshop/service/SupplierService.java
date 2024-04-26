package lk.ijse.helloshoeshop.service;

import lk.ijse.helloshoeshop.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    void saveSupplier(SupplierDTO supplierDTO);
    SupplierDTO getSupplier(String id);
    List<SupplierDTO> getAllSuppliers();
    void deleteSupplier(String id);
    void UpdateSullier(String id,SupplierDTO supplierDTO);
}
