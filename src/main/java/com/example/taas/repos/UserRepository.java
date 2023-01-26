package com.example.taas.repos;

import com.example.taas.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    User findTop1ByEmail(String email);

}
