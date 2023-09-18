package com.intranet_F5.Model;

import com.intranet_F5.DTO.UserBasic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userRequest")
public class userRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_basic")
    private UserBasic basicUser;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "approved", columnDefinition = "boolean default false")
    private boolean approved;




    public enum RequestType
    {
        Holidays
    }
}
