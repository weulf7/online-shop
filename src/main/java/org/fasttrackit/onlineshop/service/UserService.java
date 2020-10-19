package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistence.UserRepository;
import org.fasttrackit.onlineshop.transfer.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);

    //Inversion of Control(IoC)
    private final UserRepository userRepository;

    //Dependency Injection
    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public User createUser(SaveUserRequest request){
        LOGGER.info("Creating user: {}",request);

        User user=new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return userRepository.save(user);
    }

    public User getUser(long id){
        LOGGER.info("Retrieving user {}",id);

        return userRepository.findById(id)
                //lambda expression
                .orElseThrow(() -> new ResourceNotFoundException("User "+id+" does not exist."));

    }

    public void deleteUser(long id){
        LOGGER.info("Deleting user {}",id);
        userRepository.deleteById(id);

    }

    public User updateUser(SaveUserRequest request,long id){
        LOGGER.info("Updating user {}: {}",id,request);

        User existingUser = getUser(id);
//
//        existingUser.setFirstName(request.getFirstName());
//        existingUser.setLastName(request.getLastName());

        BeanUtils.copyProperties(request,existingUser);

        return userRepository.save(existingUser);

    }


}
