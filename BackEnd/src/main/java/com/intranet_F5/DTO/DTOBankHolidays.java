package com.intranet_F5.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOBankHolidays {

    private String date;
    private String start;
    private String end;
    private String name;
    private String type;
    private boolean substitute=false;
    private String rule;
    private String note;

}
