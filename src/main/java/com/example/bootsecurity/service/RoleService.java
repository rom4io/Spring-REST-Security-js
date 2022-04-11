package com.example.bootsecurity.service;

import com.example.bootsecurity.entity.Role;
import com.example.bootsecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id){
        return roleRepository.getById(id);
    }

    public List<Role> getRoleList(){
        return roleRepository.findAll();
    }
}
