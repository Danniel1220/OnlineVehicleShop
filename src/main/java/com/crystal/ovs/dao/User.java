package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private final Integer id;
    private final String name;
    private final String password;
    private final UserRole role;
}
