package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    Iterable<User> findAllByUserNameContainingIgnoreCase(String substring);

    User findByUserName(String userName);

}
