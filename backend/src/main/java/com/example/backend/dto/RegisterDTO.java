package com.example.backend.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    String name;
    String address;
    List<Integer> subject_ids = new ArrayList<>();
    String email;
    String password;
    String role;

    public UserEntity toUserEntity() {
        UserEntity studententity = new UserEntity();
        studententity.setName(this.name);
        studententity.setAddress(this.address);
        studententity.setEmail(this.email);
        studententity.setPassword(this.password);
        studententity.setRole(this.role);
        return studententity;
    }

}
