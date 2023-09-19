package com.intranet_F5.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "approved", columnDefinition = "boolean default false")
    private boolean approved;

    @Column(name = "reason")
    @Enumerated(EnumType.STRING)
    private RequestType userReason;


    public enum RequestType
    {
        Holidays
    }
}
