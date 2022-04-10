package com.example.bootsecurity.service;

import com.example.bootsecurity.entity.Role;
import com.example.bootsecurity.entity.User;
import com.example.bootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
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

    public List<User> saveList(List<User> users){
        return userRepository.saveAll(users);
    }

    public User findByName(String name){
        return userRepository.findByUsername(name);
    }
}
