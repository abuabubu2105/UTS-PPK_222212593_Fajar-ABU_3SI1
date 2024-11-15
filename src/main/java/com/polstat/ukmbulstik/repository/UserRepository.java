package com.polstat.ukmbulstik.repository;

import com.polstat.ukmbulstik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
