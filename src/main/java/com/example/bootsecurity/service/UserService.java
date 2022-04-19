package com.example.bootsecurity.service;

import com.example.bootsecurity.entity.Role;
import com.example.bootsecurity.entity.User;
import com.example.bootsecurity.repository.RoleRepository;
import com.example.bootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService{

    private final UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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

    public void update(User user, String[] role) {
        Set<Role> rol = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                rol.add(showRole(2L));
            } else {
                rol.add(showRole(1L));
            }
        }
        user.setRoles(rol);
        userRepository.save(user);
    }

    public Role showRole(Long id) {
        Optional<Role> optionalUser = roleRepository.findById(id);

        return optionalUser.get();
    }
}
