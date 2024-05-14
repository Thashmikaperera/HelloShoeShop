package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.OccasionDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.OccasionServiceDAO;
import lk.ijse.helloshoeshop.service.OccasionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OccasionServiceIMPL implements OccasionService {
    @Autowired
    private OccasionServiceDAO occasionServiceDAO;

    @Autowired
    private ConversionData conversionData;
    @Override
    public void saveOccasion(OccasionDTO occasionDTO) {
        occasionServiceDAO.save(conversionData.convertToOccasionEntity(occasionDTO));
    }

    @Override
    public List<OccasionDTO> getAllOccasion() {
        return conversionData.convertToOccasionDTOList(occasionServiceDAO.findAll());
    }

    @Override
    public void updateOccasion(String id, OccasionDTO occasionDTO) {
        if(!occasionServiceDAO.existsById(id)){throw new NotFoundException("Occasion Not Found");}
        occasionServiceDAO.save(conversionData.convertToOccasionEntity(occasionDTO));
    }

    @Override
    public void deleteOccasion(String id) {
        occasionServiceDAO.deleteById(id);
    }
}
