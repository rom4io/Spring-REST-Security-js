package com.example.bootsecurity.service;

import com.example.bootsecurity.entity.User;
import com.example.bootsecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.getById(id);
    }

    public void saveUser(User user){

        userRepository.save(user);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public User findByName(String email){
        return userRepository.findByEmail(email);
    }
}
