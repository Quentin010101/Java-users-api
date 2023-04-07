package com.example.javausersapi.repository;

import com.example.javausersapi.model.api.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {

    ApiUser getById(Long id);

    boolean existsById(Long id);

    @Query(value="SELECT u FROM ApiUser u ORDER BY rand()")
    List<ApiUser> findRandomUsers();

}
