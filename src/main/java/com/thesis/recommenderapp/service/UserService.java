package com.thesis.recommenderapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thesis.recommenderapp.dao.UserDao;
import com.thesis.recommenderapp.domain.RegistrationRequest;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.service.exceptions.UsernameAlreadyExistsException;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(Long id) {
        return userDao.findById(id).get();
    }

    public User getUserByUserName(String name) {
        return userDao.findByUserName(name);
    }

    public Iterable<User> getUsersBySubstring(String substring) {
        return userDao.findAllByUserNameContainingIgnoreCase(substring);
    }

    public List<User> getFriends(String name) {
        return userDao.findByUserName(name).getFriends();
    }

    public void saveUser(User user) {
        user.setEnabled(true);
        userDao.save(user);
    }

    public void registerUser(RegistrationRequest registrationRequest) {
        if (usernameExists(registrationRequest.getUserName())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        User user = new User();
        user.setUserName(registrationRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        saveUser(user);
    }

    private boolean usernameExists(String userName) {
        User user = userDao.findByUserName(userName);
        return user != null;
    }
}
