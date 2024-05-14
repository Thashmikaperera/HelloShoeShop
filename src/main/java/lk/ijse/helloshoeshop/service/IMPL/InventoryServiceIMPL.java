package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.ItemDTO;
import lk.ijse.helloshoeshop.entity.ItemEntity;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.InventoryServiceDAO;
import lk.ijse.helloshoeshop.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryServiceIMPL implements InventoryService {
    @Autowired
    private InventoryServiceDAO inventoryServiceDAO;

    @Autowired
    private ConversionData conversionData;

    @Override
    public void saveInventory(ItemDTO inventoryDTO) {
        inventoryDTO.setItemCode(generateItemCode((inventoryDTO)));
        inventoryServiceDAO.save(conversionData.convertToItemEntity(inventoryDTO));
    }

    @Override
    public String generateItemCode(ItemDTO itemDTO) {
        StringBuilder prefixBuiler = new StringBuilder();
        if (itemDTO.getGenderCode() != null) {
            prefixBuiler.append(itemDTO.getGenderCode());
        }
        if (itemDTO.getOccasionCode() != null) {
            prefixBuiler.append(itemDTO.getOccasionCode());
        }
        if (itemDTO.getVarietyCode() != null) {
            prefixBuiler.append(itemDTO.getVarietyCode());
        }

        String prefix = prefixBuiler.toString();

        String lastItemCodeStartingWithPrefix = inventoryServiceDAO.findLastItemCodeStartingWithPrefix(prefix);

        return (lastItemCodeStartingWithPrefix != null)
                ? String.format("%s%05d", prefix, Integer.parseInt(lastItemCodeStartingWithPrefix.replace(prefix, "")) + 1)
                : prefix + "00001";
    }

    @Override
    public List<ItemDTO> getAllInventory() {
        return conversionData.convertToItemDTOList(inventoryServiceDAO.findAll());
    }

    @Override
    public ItemDTO getInventory(String id) {
        Optional<ItemEntity> item = inventoryServiceDAO.findById(id);
        if(item.isEmpty()) throw new NotFoundException("Item not found");
        return conversionData.convertToItemDTO(item.get());
    }

    @Override
    public void updateInventory(String id,ItemDTO inventoryDTO) {
        if (inventoryServiceDAO.existsById(id)) throw new NotFoundException("Item not Found");
        inventoryServiceDAO.save(conversionData.convertToItemEntity(inventoryDTO));
    }

    @Override
    public void deleteInventory(String id) {
        inventoryServiceDAO.deleteById(id);
    }
}
