package lk.ijse.helloshoeshop.service.IMPL;
import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.Enum.Level;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.CustomerDTO;
import lk.ijse.helloshoeshop.entity.CustomerEntity;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.CustomerServiceDAO;
import lk.ijse.helloshoeshop.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    final private CustomerServiceDAO customerServiceDAO;
    final private ConversionData conversionData;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(getNextCustomerId());
        customerDTO.setLevel(Level.NEW);
        customerDTO.setTotalPoint(0);
        CustomerEntity customerEntity = conversionData.convertToCustomerEntity(Optional.of(customerDTO));
        customerServiceDAO.save(customerEntity);
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        if (!customerServiceDAO.existsById(id)) throw new NotFoundException("Customer Not Found");
        return conversionData.convertToCustomerDTO(customerServiceDAO.findById(id));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return conversionData.getCustomerDTOList(customerServiceDAO.findAll());
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerServiceDAO.existsById(id)) throw new NotFoundException("Customer Not Found");
        customerServiceDAO.deleteById(id);
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerEntity = customerServiceDAO.findById(id);
        if (customerEntity.isEmpty()) throw new NotFoundException("Customer Not Found");
        customerEntity.get().setCustomerName(customerDTO.getCustomerName());
        customerEntity.get().setGender(customerDTO.getGender());
        customerEntity.get().setJoinDate(customerDTO.getJoinDate());
        customerEntity.get().setDob(customerDTO.getDob());
        customerEntity.get().setAddress1(customerDTO.getAddress1());
        customerEntity.get().setAddress2(customerDTO.getAddress2());
        customerEntity.get().setAddress3(customerDTO.getAddress3());
        customerEntity.get().setAddress4(customerDTO.getAddress4());
        customerEntity.get().setPostalCode(customerDTO.getPostalCode());
        customerEntity.get().setContactNo(customerDTO.getContactNo());
        customerEntity.get().setEmail(customerDTO.getEmail());
        customerEntity.get().setRecentPurchasedDate(customerDTO.getRecentPurchasedDate());
    }

    private String getNextCustomerId() {
        CustomerEntity firstByOrderByCustomerIdDesc = customerServiceDAO.findFirstByOrderByCustomerIdDesc();
        return (firstByOrderByCustomerIdDesc != null)
                ? String.format("Cust-%03d",
                Integer.parseInt(firstByOrderByCustomerIdDesc.getCustomerId().
                        replace("Cust-", "")) + 1)
                : "Cust-001";
    }
}