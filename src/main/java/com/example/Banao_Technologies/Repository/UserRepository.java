package com.example.Banao_Technologies.Repository;

import com.example.Banao_Technologies.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByOtp(String otp);

    User findByEmail(String email);
}
