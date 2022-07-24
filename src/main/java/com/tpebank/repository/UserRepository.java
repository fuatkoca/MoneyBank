package com.tpebank.repository;

import com.tpebank.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
    Boolean existsBySsn(String ssn);

    Optional<User> findByUserNameAndEnabledTrue(String userName);



}
