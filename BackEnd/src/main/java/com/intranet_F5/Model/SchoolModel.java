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

@Entity
@Table(name = "School")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class SchoolModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long id;

    @Column(name = "Name")
    private String schoolName;

    @Column(name = "Address")
    private String schoolAddress;

    @Column(name = "Phone")
    private String schoolPhone;

    @Column(name = "BankHolidays")
    private List<LocalDate> schoolBankHs;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "SchoolSupervisorId", referencedColumnName = "Id")
    private UserModel SchoolSupervisor;

    @Column(name = "StateHolidays")
    @ManyToMany
    @JoinTable(
            name = "school_schooldate",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "school_date_id")
    )
    private List<SchoolDateModel> schoolStateHolidays;

    @Column(name = "State")
    @Enumerated(EnumType.STRING)
    private StateCode schoolStateCode;

    @OneToMany(mappedBy = "SchoolID",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("SchoolID")
    private List<UserModel> schoolUserList;

    public enum StateCode{
       AN,// Andalucía
       AR,// Aragón
       AS,// Asturias
       CB,// Cantabria
       CE,// Ciudad de Ceuta
       CL,// Castilla y León
       CM,// Castile-La Mancha
       CN,// Islas Canarias
       CT,// Cataluña
       EX,// Extremadura
       GA,// Galicia
       IB,// Illes Balears
       MC,// Murcia Region
       MD,// Comunidad de Madrid
       ML,// Ciudad de Melilla
       NC,// Comunidad Foral de Navarra
       PV,// País Vasco
       RI,// La Rioja
       VC,// Comunitat Valenciana               
    }

}
