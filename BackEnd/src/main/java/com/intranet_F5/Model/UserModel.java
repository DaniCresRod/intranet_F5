package com.intranet_F5.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@CrossOrigin(origins = "*")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long id;

    @Column(name = "Name")
    private String userName;

    @Column(name = "Surname")
    private String userSurName;

    @Column(name = "Nif")
    private String userNif;

    @Column(name = "Email")
    private String userEmail;

    @Column(name = "Phone")
    private String userPhone;

    @Column(name = "BirthDate")
    private LocalDate userBirthDate;

    @Column(name = "StartDate")
    private LocalDate userStartDate;

    @Column(name = "EndDate")
    private LocalDate userEndDate;

    @Column(name = "Spent_Days")
    private Integer userDays;

    @Column(name = "Password")
    private String userPass;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "userPhoto")
    private String userImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_Id")
    @JsonIgnoreProperties("schoolUserList")
    private SchoolModel SchoolID;

    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("userId")
    private List<UserRequestModel> userRequests;

    public enum UserType
    {
        Supervisor,
        HR,
        Employee,
    }

    //Checks if the End date is before the Start date (wrong date)
    public void setUserEndDate(LocalDate userEndDate) throws Exception {
        LocalDate previousUserEndDate=this.userEndDate;
        try{
            this.userEndDate = userEndDate;
            this.setDefaultStartDate();
        }
        catch(Exception e){
            this.userEndDate=previousUserEndDate;
            throw new Exception("La fecha de fin es anterior a la fecha de inicio de contrato");
        }
    }

    public void setUserDays(Integer userDays) {
        this.userDays = userDays;
    }

    //Con esto, se pre-calculan los valores de vacaciones en funcion de la
    @PrePersist
    public void setDefaultStartDate() throws Exception {
        if (this.userStartDate == null) {
            this.userStartDate = LocalDate.now();
        }

        if(userEndDate==null) this.userDays=30;
        else{
            if(this.userStartDate.isAfter(this.userEndDate)){
                throw new Exception("Hubo un error calculando fechas");
            }
            // if((this.userDays == null)){
            int calculatedDays = calcularDiasDeVacaciones(this.userStartDate, this.userEndDate);
            this.userDays = calculatedDays;
            // }
        }
    }

    private int calcularDiasDeVacaciones(LocalDate userStartDate, LocalDate userEndDate) {
        long differenceMonths=MONTHS.between(userStartDate, userEndDate);
        if(differenceMonths<12){
            return (int) Math.floor(differenceMonths*2.5);
        }
        return 30;
    }
}