package com.gamedoora.backend.userservices.assembler;


import com.gamedoora.backend.userservices.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServicesAssembler {
    private RolesRepository rolesRepository;
   


    public RolesRepository getRolesRepository() {
        return rolesRepository;
    }

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

  }
