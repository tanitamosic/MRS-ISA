package com.Projekat.service;

import com.Projekat.model.users.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    List<Role> findByName(String name);
}
