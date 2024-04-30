package lk.ijse.helloshoeshop.controller;

import jakarta.validation.Valid;
import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {
    final private EmployeeService employeeService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Employee Health check";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveEmployee(@Validated @RequestBody EmployeeDTO employeeDTO,
                                               BindingResult bindingResult,@RequestPart("profilepic") String profilepic){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            String dp =convertBase64(profilepic);
            employeeDTO.setProfilePic(dp);
            employeeService.saveEmployee(employeeDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Employee Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Employee saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    private String convertBase64(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getEmployee(@PathVariable("id")String id){
        try {
            return ResponseEntity.ok(employeeService.getEmployee(id));
        }catch (NotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error | Employee Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllEmployees(){
        try {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Employee Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable ("id") String id){
        try {
            employeeService.deleteEmplyee(id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee Details Delete Successfully");
        }catch (NotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Employee Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployee(@Validated @RequestBody EmployeeDTO employeeDTO,
                                                 BindingResult bindingResult,
                                                 @PathVariable ("id") String id){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            employeeService.updateEmployee(id, employeeDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Employee Details Updated Successfully.");
        }catch (NotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Emloyee Not Found");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error | Employee Details Updated Successfully.\nMore Reason\n"+exception);
        }
    }
}
