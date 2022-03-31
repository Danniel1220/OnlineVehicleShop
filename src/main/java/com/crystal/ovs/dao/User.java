package com.crystal.ovs.dao;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@ToString
public class User {
    private final Integer id;
    private String name;
    private String password;
    private UserRole role;
    private String email;
}
