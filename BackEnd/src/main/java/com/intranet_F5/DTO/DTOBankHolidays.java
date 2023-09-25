package com.intranet_F5.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class DTOBankHolidays {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime end;
    private String name;
    private String type;
    private boolean substitute=false;
    private String rule;
    private String note;



    public DTOBankHolidays() {
    }

    public DTOBankHolidays(LocalDateTime date, LocalDateTime start, LocalDateTime end, String name, String type, boolean substitute, String rule, String note) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.name = name;
        this.type = type;
        this.substitute = substitute;
        this.rule = rule;
        this.note = note;
    }
}
