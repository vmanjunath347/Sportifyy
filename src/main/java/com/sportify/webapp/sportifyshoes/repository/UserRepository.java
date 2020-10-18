package com.sportify.webapp.sportifyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.webapp.sportifyshoes.entity.users;

@Repository
public interface UserRepository extends JpaRepository<users, Long> {

}
