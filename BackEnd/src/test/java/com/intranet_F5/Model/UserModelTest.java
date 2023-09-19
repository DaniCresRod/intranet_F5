package com.intranet_F5.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;
import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    UserModel myTestUser1;
    UserModel myTestUser2;
    UserModel myTestUser3;
    UserModel myTestUser4;
    UserModel myTestUser5;
    SchoolModel SchoolTest1;
    List<UserRequestModel> UserRequestModelList1;
    @BeforeEach
    public void initEach(){
        //TestDummy 1: User without StarDate, no School, no Requests
        myTestUser1= new UserModel(1L, "userName1","userSurname1", "userNif1", "user1@email.com", "userPhone1", null, LocalDate.now().plusDays(30), 30, "pass1", UserModel.UserType.Employee, null, null );

        //TestDummy 2: User less than a year old, null holiday days, no School, no Requests
        myTestUser2= new UserModel(2L, "userName2","userSurname2", "userNif2", "user2@email.com", "userPhone2", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-07-31"), null, "pass2", UserModel.UserType.Employee, null, null );

        //TestDummy 3: User less than a year old, 15 holiday days, no School, no Requests
        myTestUser3= new UserModel(3L, "userName3","userSurname3", "userNif3", "user3@email.com", "userPhone3", LocalDate.parse("2020-01-01"), LocalDate.parse("2023-01-31"), 15, "pass3", UserModel.UserType.Employee, null, null );

        //TestDummy 4: User more than a year old, no School, no Requests
         SchoolTest1=new SchoolModel(1L, "School1", "email1@school.com", "PhoneSchool1", null, null);
        myTestUser4= new UserModel(4L, "userName4","userSurname4", "userNif4", "user4@email.com", "userPhone4", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"), 30, "pass4", UserModel.UserType.Employee, SchoolTest1, null );

        //TestDummy 5: User more than a year old, with School, with Requests
        UserRequestModel requestTest1 = new UserRequestModel(1L, myTestUser5, LocalDate.parse("2023-04-02"), LocalDate.parse("2023-04-10"), false, UserRequestModel.RequestType.Holidays);
        UserRequestModelList1=new ArrayList<>();
        UserRequestModelList1.add(requestTest1);

        myTestUser5= new UserModel(5L, "userName5","userSurname5", "userNif5", "user5@email.com", "userPhone5", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"), 30, "pass5", UserModel.UserType.Employee, SchoolTest1, UserRequestModelList1 );

    }
    @Test
    void setDefaultStartDate() {
        myTestUser1.setDefaultStartDate();
        myTestUser2.setDefaultStartDate();
        myTestUser3.setDefaultStartDate();

        long difference=MONTHS.between(myTestUser2.getUserStartDate(), myTestUser2.getUserEndDate());
        int hldyDays=(int) Math.floor(difference*2.5);

        //Debe ponerse la fecha de inicio como la de hoy, cambiaran los dias de vacaciones
        assertEquals(LocalDate.now(), myTestUser1.getUserStartDate());
        assertNotEquals(30, myTestUser1.getUserDays());

        //Deben cambiar los dias de vacaciones a los que corresponden
        assertNotEquals(null, myTestUser2.getUserDays());
        assertEquals(hldyDays, myTestUser2.getUserDays());

        //Deben cambiar los dias de vacaciones a los que corresponden
        assertNotEquals(15, myTestUser3.getUserDays());
        assertEquals(30, myTestUser3.getUserDays());
    }

    @Test
    void getId() {
        assertEquals( myTestUser1.getId(),1 );
        assertEquals( myTestUser2.getId(),2 );
        assertEquals( myTestUser3.getId(),3 );
        assertEquals( myTestUser4.getId(),4 );
        assertEquals( myTestUser5.getId(),5 );
    }

    @Test
    void getUserName() {
        assertEquals( myTestUser1.getUserName(),"userName1" );
        assertEquals( myTestUser2.getUserName(),"userName2" );
        assertEquals( myTestUser3.getUserName(),"userName3");
        assertEquals( myTestUser4.getUserName(),"userName4");
        assertEquals( myTestUser5.getUserName(),"userName5");
    }

    @Test
    void getUserSurName() {
        assertEquals( myTestUser1.getUserSurName(),"UserSurName1" );
        assertEquals( myTestUser2.getUserSurName(),"UserSurName2" );
        assertEquals( myTestUser3.getUserSurName(),"UserSurName3");
        assertEquals( myTestUser4.getUserSurName(),"UserSurName4");
        assertEquals( myTestUser5.getUserSurName(),"UserSurName5");
    }

    @Test
    void getUserNif() {
        assertEquals( "UserNif1" , myTestUser1.getUserNif());
    }

    @Test
    void getUserEmail() {
        assertEquals( "UserEmail1", myTestUser1.getUserEmail());
    }

    @Test
    void getUserPhone() {
        assertEquals( "UserPhone1", myTestUser1.getUserPhone());
    }

    @Test
    void getUserStartDate() {
        assertEquals( null, myTestUser1.getUserStartDate());
        assertEquals( LocalDate.parse("2023-01-01"), myTestUser2.getUserStartDate());
        assertEquals( LocalDate.parse("2020-01-01"), myTestUser3.getUserStartDate());
        assertEquals( LocalDate.parse("2023-01-01"), myTestUser4.getUserStartDate());
        assertEquals( LocalDate.parse("2023-01-01"), myTestUser5.getUserStartDate());
    }

    @Test
    void getUserEndDate() {
        assertEquals( LocalDate.now().plusDays(30), myTestUser1.getUserEndDate());
        assertEquals( LocalDate.parse("2023-07-31"), myTestUser2.getUserEndDate());
        assertEquals( LocalDate.parse("2023-01-31"), myTestUser3.getUserEndDate());
        assertEquals( LocalDate.parse("2023-01-31"), myTestUser4.getUserEndDate());
        assertEquals( LocalDate.parse("2023-01-31"), myTestUser5.getUserEndDate());
    }

    @Test
    void getUserDays() {
        assertEquals( myTestUser1.getUserDays(),30 );
        assertEquals( myTestUser2.getUserDays(),null);
        assertEquals( myTestUser3.getUserDays(),15);
        assertEquals( myTestUser4.getUserDays(),30);
        assertEquals( myTestUser5.getUserDays(),30);
    }

    @Test
    void getUserPass() {
        assertEquals( myTestUser1.getUserDays(),"pass1" );
        assertEquals( myTestUser2.getUserDays(),"pass2");
        assertEquals( myTestUser3.getUserDays(),"pass3");
        assertEquals( myTestUser4.getUserDays(),"pass4");
        assertEquals( myTestUser5.getUserDays(),"pass5");
    }

    @Test
    void getUserType() {
        assertEquals( UserModel.UserType.Employee, myTestUser1.getUserType());
    }

    @Test
    void getSchoolID() {
        assertEquals( null, myTestUser1.getSchoolID());
        assertEquals( SchoolTest1, myTestUser4.getSchoolID());
        assertEquals( SchoolTest1.getId(), myTestUser5.getSchoolID().getId());
    }

    @Test
    void getUserRequests() {
        assertEquals( null, myTestUser1.getUserRequests());
        assertEquals( UserRequestModelList1, myTestUser5.getUserRequests());

        //Hay que reasignar el userId porque se inicializo como nulo, porque aun no estaba definido cuando se inicializa el userRequestModel
        (UserRequestModelList1.get(0)).setUserId(myTestUser5);
        assertEquals( (UserRequestModelList1.get(0)).getUserId().getId(), myTestUser5.getId());
    }

    @Test
    void setId() {
        myTestUser1.setId(5L);
        assertEquals( myTestUser1.getId(),5L );
    }

    @Test
    void setUserName() {
        myTestUser1.setUserName("Robustiano");
        assertEquals( myTestUser1.getUserName(),"Robustiano");
    }

    @Test
    void setUserSurName() {
        myTestUser1.setUserSurName("Valientus");
        assertEquals( myTestUser1.getUserSurName(),"Valientus");
    }

    @Test
    void setUserNif() {
        myTestUser1.setUserNif("Ok Nif");
        assertEquals( myTestUser1.getUserNif(),"Ok Nif");
    }

    @Test
    void setUserEmail() {
        myTestUser1.setUserEmail("Ok Email");
        assertEquals( myTestUser1.getUserEmail(),"Ok Email");
    }

    @Test
    void setUserPhone() {
        myTestUser1.setUserPhone("Ok Phone");
        assertEquals( myTestUser1.getUserPhone(),"Ok Phone");
    }

    @Test
    void setUserStartDate() {
        myTestUser1.setUserStartDate("1981-01-17");
        assertEquals( myTestUser1.getUserStartDate(),LocalDate.parse("1981-01-17"));
    }

    @Test
    void setUserEndDate() {
    }

    @Test
    void setUserDays() {
    }

    @Test
    void setUserPass() {
    }

    @Test
    void setUserType() {
    }

    @Test
    void setSchoolID() {
    }

    @Test
    void setUserRequests() {
    }
}