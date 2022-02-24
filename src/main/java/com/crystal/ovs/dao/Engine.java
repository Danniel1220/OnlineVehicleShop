package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Engine {
    private final Integer id;
    private final FuelEngine fuelEngineId;
    private final ElectricEngine electricEngineId;
    private final Integer horsePower;
    private final Integer torque;
}
