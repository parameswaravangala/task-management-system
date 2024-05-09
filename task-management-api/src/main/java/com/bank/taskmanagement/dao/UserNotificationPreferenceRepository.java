package com.bank.taskmanagement.dao;

import com.bank.taskmanagement.entity.UserNotificationPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationPreferenceRepository extends JpaRepository<UserNotificationPreference, Integer> {
}