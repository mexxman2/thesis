package com.thesis.recommenderapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.thesis.recommenderapp.domain.User;

public interface UserDao extends CrudRepository<User, Long> {

    Iterable<User> findAllByUserNameContainingIgnoreCase(String substring);

    User findByUserName(String userName);

}
