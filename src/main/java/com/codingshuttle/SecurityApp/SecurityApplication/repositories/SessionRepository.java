package com.codingshuttle.SecurityApp.SecurityApplication.repositories;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.Session;
import com.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {

    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
