package com.intranet_F5.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userRequest")
public class UserRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="UserId", nullable=false)
    @JsonIgnoreProperties("userRequests")
    private UserModel userId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status", columnDefinition = "boolean default false")
    private int status;

    @Column(name = "reason")
    @Enumerated(EnumType.STRING)
    private RequestType userReason;

    //Relacion unidireccional
    @Column(name = "logListId")
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_request")
    private List<LogModel> logList;

    public enum RequestType
    {
        Holidays
    };
}
