package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {

    List<User> findAllByUserNameContainingIgnoreCase(String substring);

    User findByUserName(String userName);

}
