package com.crystal.dao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ElectricEngine {
    private final Integer id;
    private final String type;
    private final Integer batteryCapacity;
    private final Integer range;
}
