package lk.ijse.helloshoeshop.controller;

import lk.ijse.helloshoeshop.dto.GenderDTO;
import lk.ijse.helloshoeshop.dto.ItemDTO;
import lk.ijse.helloshoeshop.dto.OccasionDTO;
import lk.ijse.helloshoeshop.dto.VarietyDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.service.GenderService;
import lk.ijse.helloshoeshop.service.InventoryService;
import lk.ijse.helloshoeshop.service.OccasionService;
import lk.ijse.helloshoeshop.service.VarietyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryController {
    @Autowired
    private GenderService genderService;

    @Autowired
    private OccasionService occasionService;

    @Autowired
    private VarietyService varietyService;

    @Autowired
    private InventoryService inventoryDao;

    @GetMapping("/health")
    private String healthCheck(){
        return "Inventory Health Check";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/genderSave")
    public ResponseEntity<?> saveGender(@Validated @RequestBody GenderDTO genderDTO,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            genderService.saveGender(genderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Gender Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/genderGetAll")
    public ResponseEntity<?> getAllGenders(){
        try {
            return ResponseEntity.ok(genderService.getAllGenders());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/genderDelete")
    public ResponseEntity<String> deleteGender(@RequestParam String id){
        try {
            genderService.deleteGender(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gender Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gender not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/genderUpdate")
    public ResponseEntity<String> updateGender(@Validated @RequestBody GenderDTO genderDTO,
                                               BindingResult bindingResult,
                                               @RequestParam ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            genderService.updateGender(id,genderDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gender Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gender not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender Details Gender Unsuccessfully.\nMore Reason\n"+exception);
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/occationSave")
    public ResponseEntity<?> saveOccation(@Validated @RequestBody OccasionDTO occasionDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            occasionService.saveOccasion(occasionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Occasion Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/occationGetAll")
    public ResponseEntity<?> getAllOccasion(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(occasionService.getAllOccasion());
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion Details fetched Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @PutMapping("/occationUpdate")
    public ResponseEntity<?> updateOccation(@Validated @PathVariable ("id") String id, @RequestBody OccasionDTO occasionDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            occasionService.updateOccasion(id, occasionDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Occasion Details updated Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion Details fetched Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @DeleteMapping("/occasionDelete")
    public ResponseEntity<?> deleteGender(@Validated @PathVariable ("id") String id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            occasionService.deleteOccasion(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Occasion Details deleted Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion Details deleted Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saveVariety")
    public ResponseEntity<?> saveVariety(@Validated @RequestBody VarietyDTO varietyDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            varietyService.saveVariety(varietyDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Variety Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/getAllVariety")
    public ResponseEntity<?> getAllVariety(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(varietyService.getAllVariety());
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety Details fetched Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @PutMapping("/updateVariety")
    public ResponseEntity<?> updateVariety(@Validated @PathVariable ("id") String id, @RequestBody VarietyDTO varietyDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            varietyService.updateVariety(id, varietyDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Variety Details updated Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety Details fetched Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @DeleteMapping("/deleteVariety")
    public ResponseEntity<?> deleteVariety(@Validated @PathVariable ("id") String id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            varietyService.deleteVariety(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Variety Details deleted Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety Details deleted Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saveInventory")
    public ResponseEntity<?> saveInventory(@Validated @RequestBody ItemDTO inventoryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            inventoryDao.saveInventory(inventoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inventory Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Inventory saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }
    @GetMapping("/getAllInventory")
    public ResponseEntity<?> getAllInventory(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryDao.getAllInventory());
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Inventory Details fetched Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/getItem")
    public ResponseEntity<?> getInventory(@Validated @PathVariable ("id") String id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryDao.getInventory(id));
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Inventory Details fetched Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @PutMapping("/updateInventory")
    public ResponseEntity<?> updateInventory(@Validated @PathVariable ("id") String id, @RequestBody ItemDTO inventoryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            inventoryDao.updateInventory(id, inventoryDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Inventory Details updated Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Inventory Details fetched Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @DeleteMapping("/deleteInventory")
    public ResponseEntity<?> deleteInventory(@Validated @PathVariable ("id") String id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            inventoryDao.deleteInventory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Inventory Details deleted Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Inventory Details deleted Unsuccessfully.\nMore Details\n"+exception);
        }
    }

}
