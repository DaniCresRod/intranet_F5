package com.intranet_F5.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
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
    UserRequestModel requestTest1;

    @BeforeEach
    public void initEach(){
        //TestDummy 1: User without StarDate, no School, no Requests
        myTestUser1= new UserModel(1L, "userName1","userSurname1", "userNif1", "user1@email.com", "userPhone1",null, null, LocalDate.now().plusDays(30), 30, "pass1", UserModel.UserType.Formador, null, null, null, null );

        //TestDummy 2: User less than a year old, null holiday days, no School, no Requests
        myTestUser2= new UserModel(2L, "userName2","userSurname2", "userNif2", "user2@email.com", "userPhone2",null, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-07-31"), null, "pass2", UserModel.UserType.Formador, null,null, null, null );

        //TestDummy 3: User less than a year old, 15 holiday days, no School, no Requests
        myTestUser3= new UserModel(3L, "userName3","userSurname3", "userNif3", "user3@email.com", "userPhone3",null, LocalDate.parse("2020-01-01"), LocalDate.parse("2023-01-31"), 15, "pass3", UserModel.UserType.Formador, null,null, null, null );

        //TestDummy 4: User more than a year old, no School, no Requests
        SchoolTest1=new SchoolModel(1L, "School1", "email1@school.com", "PhoneSchool1", null, null, null, SchoolModel.StateCode.AS, null);
        myTestUser4= new UserModel(4L, "userName4","userSurname4", "userNif4", "user4@email.com", "userPhone4",null, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"), 30, "pass4", UserModel.UserType.Formador,null, null, SchoolTest1, null );

        //TestDummy 5: User more than a year old, with School, with Requests
        requestTest1 = new UserRequestModel(1L, myTestUser5,LocalDate.now(), LocalDate.parse("2023-04-02"), LocalDate.parse("2023-04-10"), 1, UserRequestModel.RequestType.Holidays, null);
        UserRequestModelList1=new ArrayList<>();
        UserRequestModelList1.add(requestTest1);

        myTestUser5= new UserModel(5L, "userName5","userSurname5", "userNif5", "user5@email.com", "userPhone5",null, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"), 30, "pass5", UserModel.UserType.Formador,null, null, SchoolTest1, UserRequestModelList1 );

    }
    @Test
    void setDefaultStartDate() throws Exception {
        myTestUser1.defaultUserStartDate();
        myTestUser2.defaultUserStartDate();
        myTestUser3.defaultUserStartDate();

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
        assertEquals( myTestUser1.getUsername(),"userName1" );
        assertEquals( myTestUser2.getUsername(),"userName2" );
        assertEquals( myTestUser3.getUsername(),"userName3");
        assertEquals( myTestUser4.getUsername(),"userName4");
        assertEquals( myTestUser5.getUsername(),"userName5");
    }

    @Test
    void getUserSurName() {
        assertEquals( "userSurname1", myTestUser1.getUserSurName());
    }

    @Test
    void getUserNif() {
        assertEquals( "userNif1" , myTestUser1.getUserNif());
    }

    @Test
    void getUserEmail() {
        assertEquals( "user1@email.com", myTestUser1.getUserEmail());
    }

    @Test
    void getUserPhone() {
        assertEquals( "userPhone1", myTestUser1.getUserPhone());
    }

    @Test
    void getUserStartDate() {
        assertEquals( null, myTestUser1.getUserStartDate());
        assertEquals( myTestUser2.getUserStartDate(), LocalDate.parse("2023-01-01"));
    }

    @Test
    void getUserEndDate() {
        assertEquals( LocalDate.now().plusDays(30), myTestUser1.getUserEndDate());
        assertEquals( LocalDate.parse("2023-07-31"), myTestUser2.getUserEndDate());
    }

    @Test
    void getUserDays() {
        assertEquals( myTestUser1.getUserDays(),30 );
        assertEquals( myTestUser2.getUserDays(),null);
    }

    @Test
    void getUserPass() {
        assertEquals("pass1", myTestUser1.getUserPass());
    }

    @Test
    void getUserType() {
        assertEquals( UserModel.UserType.Formador, myTestUser1.getUserType());
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
        assertEquals( "Robustiano", myTestUser1.getUsername());
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
        assertEquals( "Ok Phone", myTestUser1.getUserPhone());
    }

    @Test
    void setUserStartDate() {
        myTestUser1.setUserStartDate(LocalDate.parse("1981-01-17"));
        assertEquals( myTestUser1.getUserStartDate(),LocalDate.parse("1981-01-17"));
    }

    @Test
    void setUserEndDate_when_bad_End_date() throws Exception {
        myTestUser1.defaultUserStartDate();

        //Test with a wrong date
        LocalDate previusEndDate=myTestUser1.getUserEndDate();
        myTestUser1.setUserEndDate(LocalDate.parse("1024-01-17"));
        assertEquals( previusEndDate, myTestUser1.getUserEndDate());

        //Test with a right date
        myTestUser1.setUserEndDate(LocalDate.parse("2024-01-17"));
        assertEquals( myTestUser1.getUserEndDate(), LocalDate.parse("2024-01-17"));
    }

    @Test
    void setUserDays() throws Exception {
        myTestUser1.defaultUserStartDate();
        myTestUser1.setUserDays(45);

        assertEquals(45, myTestUser1.getUserDays());

        myTestUser2.setUserDays(45);
        myTestUser2.defaultUserStartDate();

        assertNotEquals(null, myTestUser2.getUserDays());
        assertNotEquals(45, myTestUser2.getUserDays());
    }

//    @Test
//    void setUserPass() {
//        myTestUser1.setUserPass("Ok Pass");
//        assertEquals( "Ok Pass", myTestUser1.getUserPass());
//    }

    @Test
    void setUserType() {
        myTestUser1.setUserType(UserModel.UserType.HR);
        assertEquals( UserModel.UserType.HR, myTestUser1.getUserType());
    }

    @Test
    void setSchoolID() {
        myTestUser1.setSchoolID(SchoolTest1);
        List<UserModel> mySchoolUsersList=new ArrayList<>();
        mySchoolUsersList.add(myTestUser1);
        mySchoolUsersList.add(myTestUser2);
        SchoolTest1.setSchoolUserList(mySchoolUsersList);

        assertFalse(myTestUser1.getSchoolID()==null);
        assertEquals(SchoolTest1, myTestUser1.getSchoolID());
        assertTrue(SchoolTest1.getSchoolUserList().contains(myTestUser1));
        assertEquals(SchoolTest1.getSchoolUserList().get(0).getSchoolID().getId(),myTestUser1.getSchoolID().getId());

        SchoolModel SchoolTest2=new SchoolModel();
        myTestUser4.setSchoolID(SchoolTest2);

        assertNotEquals(SchoolTest1, myTestUser4.getSchoolID());
        assertEquals(SchoolTest2, myTestUser4.getSchoolID());
    }

    //The checking of the correct userId in request must be done in the request creation
    @Test
    void setUserRequests() {
        myTestUser1.setUserRequests(UserRequestModelList1);

        assertEquals(UserRequestModelList1, myTestUser1.getUserRequests());
        assertTrue(myTestUser1.getUserRequests().contains(requestTest1));

        myTestUser5.setUserRequests(null);
        assertEquals(null, myTestUser5.getUserRequests());
    }
}