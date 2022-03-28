package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Setter
public class User {
    private final Integer id;
    private String name;
    private String password;
    private UserRole role;
    private String email;
}
