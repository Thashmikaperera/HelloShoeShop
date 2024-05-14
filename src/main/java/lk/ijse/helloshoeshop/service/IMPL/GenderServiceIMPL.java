package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.GenderDTO;
import lk.ijse.helloshoeshop.dto.OccasionDTO;
import lk.ijse.helloshoeshop.entity.OccasionEntity;
import lk.ijse.helloshoeshop.exception.DuplicateException;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.GenderServiceDAO;
import lk.ijse.helloshoeshop.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GenderServiceIMPL implements GenderService {
    private final GenderServiceDAO genderServiceDAO;
    private final ConversionData conversionData;
    @Override
    public void saveGender(GenderDTO genderDTO) {
        if (genderServiceDAO.existsById(genderDTO.getGenderCode())) throw new DuplicateException("Gender Id Duplicate");
        genderServiceDAO.save(conversionData.toGenderEntity(genderDTO));
    }

    @Override
    public List<GenderDTO> getAllGenders() {
        return conversionData.convertToGenderDTO(genderServiceDAO.findAll());
    }

    @Override
    public void deleteGender(String id) {
        if (!genderServiceDAO.existsById(id)) throw new NotFoundException("Gender not Found");
        genderServiceDAO.deleteById(id);
    }

    @Override
    public void updateGender(String id, GenderDTO genderDTO) {
        if (!genderServiceDAO.existsById(id)) throw new NotFoundException("Gender not Found");
        genderServiceDAO.save(conversionData.toGenderEntity(genderDTO));
    }


}
