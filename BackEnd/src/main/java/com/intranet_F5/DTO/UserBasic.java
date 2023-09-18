package com.intranet_F5.DTO;

import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@NoArgsConstructor
public class UserBasic {

    private long id;
    private String name;
    private SchoolModel schoolList;


    public UserBasic(long id, String userName, SchoolModel schoolList) {
    }
}
