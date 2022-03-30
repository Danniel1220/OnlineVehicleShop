package com.crystal.ovs.dao;

import lombok.*;

import java.awt.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Post {
    private Integer id;
    private final String title;
    private final String description;
    private final Float price;
    private final Integer available;
    private final int carId;
}
