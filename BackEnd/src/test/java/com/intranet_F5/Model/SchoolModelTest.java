package com.intranet_F5.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolModelTest {
    SchoolModel SchoolTest1;
    SchoolModel SchoolTest2;
    SchoolModel SchoolTest3;
    UserModel myTestUser1;
    List<LocalDate>myDatesTestList1;
    List<UserModel>myUsersTestList1;

    @BeforeEach
    public void initEach(){
        //SchoolModel object1: No Students, no bankHolidays
        SchoolTest1=new SchoolModel(1L, "School1", "email1@school.com", "PhoneSchool1", null, null);

        //SchoolModel object2: 1 Student, no bankHolidays
        myTestUser1= new UserModel(1L, "userName1","userSurname1", "userNif1", "user1@email.com", "userPhone1", LocalDate.parse("2020-01-01"), LocalDate.parse("2023-01-31"), 30, "pass2", UserModel.UserType.Employee, null, null );
        myUsersTestList1=new ArrayList<>();
        myUsersTestList1.add(myTestUser1);
        SchoolTest2=new SchoolModel(2L, "School2", "email2@school.com", "PhoneSchool2", null, myUsersTestList1);

        //SchoolModel object3: 1 Students, with bankHolidays
        myDatesTestList1=new ArrayList<>();
        myDatesTestList1.add(LocalDate.now());
        myDatesTestList1.add(LocalDate.parse("2023-09-08"));
        SchoolTest3=new SchoolModel(3L, "School3", "email3@school.com", "PhoneSchool3", myDatesTestList1, myUsersTestList1);
    }

    @Test
    void getId() {
        assertEquals(1L, SchoolTest1.getId());
    }

    @Test
    void getSchoolName() {
        assertEquals("School1", SchoolTest1.getSchoolName());
    }

    @Test
    void getSchoolEmail() {
        assertEquals("email1@school.com", SchoolTest1.getSchoolEmail());
    }

    @Test
    void getSchoolPhone() {
        assertEquals("PhoneSchool1", SchoolTest1.getSchoolPhone());
    }

    @Test
    void getSchoolBankHs() {
        assertTrue(SchoolTest2.getSchoolBankHs()==null);
        assertFalse(SchoolTest3.getSchoolBankHs()==null);
        assertTrue(SchoolTest3.getSchoolBankHs().contains(LocalDate.parse("2023-09-08")));
    }

    @Test
    void getSchoolsUserList() {
        assertTrue(SchoolTest1.getSchoolUserList()==null);
        assertFalse(SchoolTest2.getSchoolUserList()==null);
        assertTrue(SchoolTest3.getSchoolUserList().contains(myTestUser1));
    }

    @Test
    void setId() {
        SchoolTest1.setId(3L);
        assertEquals(3L, SchoolTest1.getId());
    }

    @Test
    void setSchoolName() {
        SchoolTest1.setSchoolName("Name School OK");
        assertEquals("Name School OK", SchoolTest1.getSchoolName());
    }

    @Test
    void setSchoolEmail() {
        SchoolTest1.setSchoolEmail("Email School OK");
        assertEquals("Email School OK", SchoolTest1.getSchoolEmail());
    }

    @Test
    void setSchoolPhone() {
        SchoolTest1.setSchoolPhone("Phone School OK");
        assertEquals("Phone School OK", SchoolTest1.getSchoolPhone());
    }

    @Test
    void setSchoolBankHs() {
        SchoolTest1.setSchoolBankHs(myDatesTestList1);

        assertEquals(myDatesTestList1, SchoolTest1.getSchoolBankHs());
        assertEquals(null, SchoolTest2.getSchoolBankHs());
        assertEquals(LocalDate.parse("2023-09-08"), SchoolTest3.getSchoolBankHs().get(1));
    }

    @Test
    void setSchoolsList() {
        SchoolTest1.setSchoolUserList(myUsersTestList1);

        assertEquals(myUsersTestList1, SchoolTest1.getSchoolUserList());
        assertEquals("userName1",myUsersTestList1.get(0).getUserName());
    }
}