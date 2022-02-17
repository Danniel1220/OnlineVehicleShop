package com.crystal.dao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Engine {
    private final Integer id;
    private final FuelEngine fuelEngine;
    private final ElectricEngine electricEngine;
    private final Integer horsePower;
    private final Integer torque;
}
