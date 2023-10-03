package com.intranet_F5.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class userRequestModelTest {

    @BeforeEach
    public void initEach(){
        //TestRequest1
        UserModel myTestUser1= new UserModel(1L, "userName1","userSurname1", "userNif1", "user1@email.com", "userPhone1",null, LocalDate.parse("2020-01-01"), LocalDate.parse("2023-01-31"), 30, "pass1", UserModel.UserType.Formador, null,null, null, null );
        UserRequestModel requestTest1= new UserRequestModel(1L, myTestUser1,LocalDate.now(), LocalDate.parse("2023-04-02"), LocalDate.parse("2023-04-10"), 1, UserRequestModel.RequestType.Holidays, null);
    }

    @Test
    void getId() {
    }

    @Test
    void getUserId() {
    }

    @Test
    void getStartDate() {
    }

    @Test
    void getEndDate() {
    }

    @Test
    void isApproved() {
    }

    @Test
    void getUserReason() {
    }

    @Test
    void setId() {
    }

    @Test
    void setUserId() {
    }

    @Test
    void setStartDate() {
    }

    @Test
    void setEndDate() {
    }

    @Test
    void setApproved() {
    }

    @Test
    void setUserReason() {
    }
}