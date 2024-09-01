package com.crud.simple.repository;

import com.crud.simple.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, UUID> {
    @Transactional
    @Modifying
    @Query("delete from UserApp u where upper(u.email) = upper(?1)")
    void deleteByEmailIgnoreCase(@NonNull String email);

    @Query("select u from UserApp u where upper(u.email) = upper(?1)")
    Optional<UserApp> findByEmailIgnoreCase(@NonNull String username);

    @Query("select u from UserApp u where u.id = ?1")
    UserApp findByIdNotOptional(@NonNull UUID id);
}