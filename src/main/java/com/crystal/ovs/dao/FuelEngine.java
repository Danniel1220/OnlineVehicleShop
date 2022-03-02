package com.crystal.ovs.dao;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class FuelEngine {
    private Integer id;
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
