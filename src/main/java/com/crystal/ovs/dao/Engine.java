package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Engine {
    private final Integer id;
    private final FuelEngine fuelEngine;
    private final ElectricEngine electricEngine;
    private final Integer horsePower;
    private final Integer torque;
}
