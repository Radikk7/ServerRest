package com.example.serverrest.repository;

import com.example.serverrest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Userrepository extends JpaRepository<User,Long> {
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    List<User> findAllByStatusAndIdAfter(Boolean status,Long id);
    List<User> findAllByIdIsAfter(Long id);
    List<User> findAllByStatus(Boolean status);
    List<User> findAllByName(String name);
    List<User> findAllByEmail(String email);
}
