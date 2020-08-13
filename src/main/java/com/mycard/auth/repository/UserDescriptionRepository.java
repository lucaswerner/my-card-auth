package com.mycard.auth.repository;

import com.mycard.auth.entity.UserDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDescriptionRepository extends JpaRepository<UserDescription, Long> {
}
