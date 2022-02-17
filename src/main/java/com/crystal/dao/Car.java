package com.crystal.dao;

import lombok.AllArgsConstructor;

import java.awt.*;

@AllArgsConstructor
public class Car {
    private final Integer id;
    private final String brand;
    private final String model;
    private final String VIN;
    private final Integer manufacturingYear;
    private final CarType carType;
    private final Engine engine;
    private final Transmission transmission;
    private final TractionType tractionType;
    private final Integer numberOfDoors;
    private final Color color;
}
