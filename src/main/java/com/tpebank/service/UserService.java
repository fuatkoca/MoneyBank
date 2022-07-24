package com.tpebank.service;

import com.tpebank.domain.Role;
import com.tpebank.domain.User;
import com.tpebank.domain.enums.RoleType;
import com.tpebank.dto.request.RegisterRequest;
import com.tpebank.exception.ConflictException;
import com.tpebank.exception.ResourceNotFoundException;
import com.tpebank.exception.message.ExceptionMessages;
import com.tpebank.repository.RoleRepository;
import com.tpebank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {


    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest registerRequest){

        if(userRepository.existsByUserName(registerRequest.getUserName())){
            throw new ConflictException(String.format(ExceptionMessages.USERNAME_ALREADY_EXIST_MESSAGE,registerRequest.getUserName()));
        }

        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ConflictException(String.format(ExceptionMessages.EMAIL_ALREADY_EXIST_MESSAGE,registerRequest.getEmail()));
        }
        if(userRepository.existsBySsn(registerRequest.getSsn())){

            throw new ConflictException(String.format(ExceptionMessages.SSN_ALREADY_EXIST_MESSAGE,registerRequest.getSsn()));
        }

        Set<Role> userRoles= new HashSet<>();

        Role role = roleRepository.findByName(RoleType.ROLE_CUSTOMER).orElseThrow(() -> new ResourceNotFoundException(
                String.format(ExceptionMessages.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_CUSTOMER.name())));

        userRoles.add(role);

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setSsn(registerRequest.getSsn());
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAddress(registerRequest.getAddress());
        user.setDateOfBirth(registerRequest.getDateOfBirth());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(userRoles);

        userRepository.save(user);
    }



}
