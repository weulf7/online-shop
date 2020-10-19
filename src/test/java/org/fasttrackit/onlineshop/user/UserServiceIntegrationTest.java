package org.fasttrackit.onlineshop.user;

import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.service.UserService;
import org.fasttrackit.onlineshop.transfer.SaveUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class UserServiceIntegrationTest {

    //field-injection
    @Autowired
    private UserService userService;

    @Test
    public void createUser_whenValidRequest_thenReturnSavedUser(){
        SaveUserRequest saveUserRequest= new SaveUserRequest();

        saveUserRequest.setFirstName("Test First Name");
        saveUserRequest.setLastName("Test Last Name");

        User user = userService.createUser(saveUserRequest);

        assertThat(user,notNullValue());
        assertThat(user.getId(),greaterThan(0L));
        assertThat(user.getLastName(),is(saveUserRequest.getLastName()));
        assertThat(user.getFirstName(),is(saveUserRequest.getFirstName()));

    }

    @Test
    public void createUser_whenMissingFirstName_thenThrowException(){
        SaveUserRequest request=new SaveUserRequest();
        request.setFirstName(null);
        request.setLastName("Test Last Name");

        Exception exception=null;
        try {
            userService.createUser(request);
        }catch (Exception e){
            exception=e;
        }
    assertThat(exception,notNullValue());
        assertThat("Unexpected exception type.",exception instanceof TransactionSystemException);
    }
}
