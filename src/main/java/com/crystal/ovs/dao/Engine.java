package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Engine {
    private final Integer id;
    private final Integer fuelEngineId;
    private final Integer electricEngineId;
    private final Integer horsePower;
    private final Integer torque;
}
