package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lk.ijse.helloshoeshop.entity.enumeratedData.Gender;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Entity
@Table(name = "employee")
@Data
public class EmployeeEntity {
    @Id
    private String employeeCode;
    private String enployeeName;
    private String profilePic;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String status;
    private String designation;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    private Date dateOfJoin;

    private String attachedBranch;
    private String address1;
    private String address2;
    private String address3;
    private String address4;

    private String postalCode;
    private String contactNo;
    private String email;
    private String emergencyContactName;
    private String emergencyContact;
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;
}
