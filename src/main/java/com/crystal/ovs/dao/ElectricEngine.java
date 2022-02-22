package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class ElectricEngine {
    private final Integer id;
    private final String type;
    private final Integer batteryCapacity;
    private final Integer range;
}
