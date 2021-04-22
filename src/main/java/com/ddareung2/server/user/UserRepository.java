package com.ddareung2.server.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddareung2.server.model.AdminItem;

@Repository
public interface UserRepository extends JpaRepository<AdminItem, Long> {
	
	AdminItem findAccountByUsername(String username);

}
