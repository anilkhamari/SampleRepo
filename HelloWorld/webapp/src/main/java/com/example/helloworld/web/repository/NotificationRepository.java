package com.example.helloworld.web.repository;

import com.example.helloworld.web.model.Notification;
import com.example.helloworld.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByIdDesc(User user);
    long countByUserAndReadFalse(User user);
}
