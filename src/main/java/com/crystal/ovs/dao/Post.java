package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Post {
    private final Integer id;
    private final String title;
    private final String description;
    private final Double price;
    private final Integer available;
    private final Car car;
}
