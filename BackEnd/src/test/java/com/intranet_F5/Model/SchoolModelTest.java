package com.intranet_F5.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolModelTest {
    public SchoolModel SchoolTest2;
    @BeforeEach
    public void initEach(){
        //SchoolModel object1: No Students, no bankHolidays
        SchoolModel SchoolTest1=new SchoolModel(1L, "School1", "email1@school.com", "PhoneSchool1", null, null);

        //SchoolModel object2: 1 Student, no bankHolidays
        UserModel myTestUser1= new UserModel(1L, "userName1","userSurname1", "userNif1", "user1@email.com", "userPhone1", LocalDate.parse("2020-01-01"), LocalDate.parse("2023-01-31"), 30, "pass2", UserModel.UserType.Employee, null, null );
        List<UserModel>myUsersTestList1=new ArrayList<>();
        myUsersTestList1.add(myTestUser1);
        SchoolModel SchoolTest2=new SchoolModel(2L, "School2", "email2@school.com", "PhoneSchool2", null, myUsersTestList1);

        //SchoolModel object3: 1 Students, with bankHolidays
        List<LocalDate>myDatesTestList1=new ArrayList<>();
        myDatesTestList1.add(LocalDate.now());
        myDatesTestList1.add(LocalDate.parse("2023-09-08"));
        SchoolModel SchoolTest3=new SchoolModel(3L, "School3", "email3@school.com", "PhoneSchool3", myDatesTestList1, myUsersTestList1);

    }

    @Test
    void getId() {
    }

    @Test
    void getSchoolName() {
    }

    @Test
    void getSchoolEmail() {
    }

    @Test
    void getSchoolPhone() {
    }

    @Test
    void getSchoolBankHs() {
    }

    @Test
    void getSchoolsList() {
    }

    @Test
    void setId() {
    }

    @Test
    void setSchoolName() {
    }

    @Test
    void setSchoolEmail() {
    }

    @Test
    void setSchoolPhone() {
    }

    @Test
    void setSchoolBankHs() {
    }

    @Test
    void setSchoolsList() {
    }
}