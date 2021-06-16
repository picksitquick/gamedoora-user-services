package com.gamedoora.backend.userservices.api;


import com.gamedoora.backend.userservices.assembler.UserServicesAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserServicesController {
private UserServicesAssembler userServicesAssembler;


    public UserServicesAssembler getUserServicesAssembler() {
        return userServicesAssembler;
    }

    @Autowired
    public void setUserServicesAssembler(UserServicesAssembler userServicesAssembler) {
        this.userServicesAssembler = userServicesAssembler;
    }
}
