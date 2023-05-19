package com.sip.ams.controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);
}
