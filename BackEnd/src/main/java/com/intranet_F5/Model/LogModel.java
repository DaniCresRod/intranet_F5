package com.intranet_F5.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "logs")
@CrossOrigin(origins="*")
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "id_request")
    private long RequestId;

    @Column(name = "log_date")
    private LocalDate logDate;

    @Column(name = "log_text")
    private String logText;

    @Column(name = "id_creator_user")
    private long userId;
}
