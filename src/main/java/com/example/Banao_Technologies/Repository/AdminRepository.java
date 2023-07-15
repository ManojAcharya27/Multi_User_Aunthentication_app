package com.example.Banao_Technologies.Repository;

import com.example.Banao_Technologies.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

  Admin findByEmail(String email);

}
