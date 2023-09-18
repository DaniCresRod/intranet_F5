package com.intranet_F5.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

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

    @Column(name = "Surname_1")
    private String userSurName1;

    @Column(name = "Surname_2")
    private String userSurName2;

    @Column(name = "Nif")
    private String userNif;

    @Column(name = "Email")
    private String userEmail;

    @Column(name = "Phone")
    private String userPhone;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "SchoolID")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id")
    @JsonIgnoreProperties("schoolsList")
    private SchoolModel SchoolID;

    public enum UserType
    {
        SuperAdmin,
        Autorizator,
        HHRR,
        Worker,
    }

}