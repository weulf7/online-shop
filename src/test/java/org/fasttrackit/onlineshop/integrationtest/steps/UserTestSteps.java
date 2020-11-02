package org.fasttrackit.onlineshop.integrationtest.steps;

import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.service.UserService;
import org.fasttrackit.onlineshop.transfer.user.SaveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class UserTestSteps {

    @Autowired
    private UserService userService;


    public User createUser() {
        SaveUserRequest saveUserRequest= new SaveUserRequest();

        saveUserRequest.setFirstName("Test First Name");
        saveUserRequest.setLastName("Test Last Name");

        User user = userService.createUser(saveUserRequest);

        assertThat(user,notNullValue());
        assertThat(user.getId(),greaterThan(0L));
        assertThat(user.getLastName(),is(saveUserRequest.getLastName()));
        assertThat(user.getFirstName(),is(saveUserRequest.getFirstName()));

        return user;
    }
}
