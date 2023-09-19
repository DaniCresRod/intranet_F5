package com.intranet_F5.Model;

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

    @Column(name = "StartDate")
    private LocalDate userStartDate;

    @Column(name = "EndDate")
    private LocalDate userEndDate;

    @Column(name = "Spent_Days")
    private int userDays;

    //Con esto, se pre-calculan los valores de vacaciones en funcion de la
    @PrePersist
    public void setDefaultStartDate() {
        if (this.userStartDate == null) {
            this.userStartDate = LocalDate.now();
        }
        if(this.userDays != 30) {
            int calculatedDays = calcularDiasDeVacaciones(this.userStartDate, this.userEndDate);
            this.userDays = calculatedDays;
        }
    }

    private int calcularDiasDeVacaciones(LocalDate userStartDate, LocalDate userEndDate) {
        long differenceMonths=MONTHS.between(userStartDate, userEndDate);
        if(differenceMonths<12){
            return (int) Math.floor(differenceMonths*2.5);
        }
        return 30;
    }

    @Column(name = "Password")
    private String userPass;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "School_Id")
    @JsonIgnoreProperties("schoolsList")
    private SchoolModel SchoolID;

    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("userId")
    private List<userRequestModel> userRequests;

    public enum UserType
    {
        Supervisor,
        HHRR,
        Employee,
    }

}