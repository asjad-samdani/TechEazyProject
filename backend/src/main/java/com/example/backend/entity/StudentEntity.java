package com.example.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String password;
    private String email;
    private String role;

    @OneToMany(mappedBy = "student", orphanRemoval = false, fetch = FetchType.EAGER)
    private List<Enrollment> enrollments = new ArrayList<>();

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // // return List.of();
    // return List.of(new SimpleGrantedAuthority(role));

    // }

    // public String getPassword() {
    // return password;
    // }

    // @Override
    // public String getUsername() {
    // return email;
    // }

}
