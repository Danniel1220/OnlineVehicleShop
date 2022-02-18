package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter

public class FuelEngine {
    private final Integer id;
    private final FuelType fuelType;
    private final Float fuelConsumption;
    private final Integer numberOfCylinders;
    private final Float CO2Emissions;
    private final EngineLayout engineLayout;
    private final boolean hasTurbine;
    private final boolean hasSuperCharger;
    private final StrokeType strokeType;
    private final Float displacement;
}
