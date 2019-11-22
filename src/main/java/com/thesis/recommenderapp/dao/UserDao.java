package com.thesis.recommenderapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.thesis.recommenderapp.domain.User;

public interface UserDao extends CrudRepository<User, Long> {

    Page<User> findAllByUserNameContainingIgnoreCase(String substring, Pageable pageable);

    User findByUserName(String userName);

}
