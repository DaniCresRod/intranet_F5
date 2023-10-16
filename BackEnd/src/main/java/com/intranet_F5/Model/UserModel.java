package com.intranet_F5.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


import static java.time.temporal.ChronoUnit.MONTHS;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@CrossOrigin(origins = "*")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserModel implements UserDetails {

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

    @Column(name = "Email", unique = true)
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

    //@JsonIgnore
    @Column(name = "Password")
    private String userPass;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private UserType userType=UserType.Formador;

    @Column(name = "Dept")
    @Enumerated(EnumType.STRING)
    private UserDept userDept;

    @Column(name = "userPhoto", columnDefinition = "TEXT")
    private String userImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_Id")
//    @JsonIgnoreProperties({"schoolUserList", "SchoolSupervisor" })
//    @JsonIgnoreProperties("SchoolID")
    @JsonIgnoreProperties("schoolUserList")
    private SchoolModel SchoolID;

    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnoreProperties("userId")
    @JsonIgnoreProperties("userRequests")
    private List<UserRequestModel> userRequests;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String myRol=UserType.Formador.toString();
        if(this.getUserType()!=null){
            myRol= this.getUserType().toString();
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(myRol));
        return roles;
    }

    //@JsonIgnore
    @Override
    public String getPassword() {
        return this.userPass;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum UserType
    {
        Supervisor,
        HR,
        Formador,
    }
    public enum UserDept
    {
        RRHH,
        Pedagógico,
        Supervisión,
    }

    //PrePersist se ejcuta cada vez que se hace una nueva insercion en la base de datos
    @PrePersist
    public void defaultUserStartDate(){
        if(this.userStartDate==null){
            this.userStartDate=LocalDate.now();
        }
        if((this.userDays==null)||(this.userDays>30)){
        }
        this.userDays=this.SetHldysDays(this.getUserStartDate(),this.getUserEndDate());
        if(this.userDept==null) this.userDept=UserDept.Pedagógico;
    }

    public void setUserEndDate(LocalDate userEndDate) throws Exception {
        LocalDate previousUserEndDate=this.userEndDate;
        if(this.userDays==null)this.userDays=0;
        int holidayDaysUsed=this.SetHldysDays(this.getUserStartDate(),this.getUserEndDate())-this.userDays;

        try{
            if(userEndDate!=null && previousUserEndDate!=null){
                if(userEndDate.isAfter(previousUserEndDate) && userEndDate.isAfter(this.userStartDate)){
                    this.userEndDate = userEndDate;
                    this.userDays=this.SetHldysDays(this.getUserStartDate(),this.getUserEndDate())-holidayDaysUsed;
                }
                else{
                    throw new Exception();
                }
            }
            else if(userEndDate!=null){
                this.userEndDate = userEndDate;
                this.userDays=this.SetHldysDays(this.getUserStartDate(),this.getUserEndDate())-holidayDaysUsed;
            }
            else{
                this.userEndDate = userEndDate;
                this.userDays=30-holidayDaysUsed;
            }

        }
        catch(Exception e){
            this.userEndDate=previousUserEndDate;
            throw new Exception("La fecha de fin de contrato no es correcta");
        }
    }

    private int SetHldysDays(LocalDate userStartDate, LocalDate userEndDate) {
        if(userEndDate!=null){
            long differenceMonths=MONTHS.between(userStartDate, userEndDate);
            if(differenceMonths<12){
                return (int) Math.floor(differenceMonths*2.5);
            }
        }
        return 30;
    }

    public void setUserDays(Integer userDays) {
        this.userDays = userDays;
    }




}