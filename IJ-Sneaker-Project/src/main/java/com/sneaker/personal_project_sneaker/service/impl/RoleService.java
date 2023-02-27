package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.repository.IRoleRepository;
import com.sneaker.personal_project_sneaker.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void setDefaultRole(int accountId, Integer roleId) {
        roleRepository.setDefaultRole(accountId, roleId);
    }
}
