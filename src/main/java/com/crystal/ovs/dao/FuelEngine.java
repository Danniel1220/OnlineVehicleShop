package com.crystal.ovs.dao;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class FuelEngine {
    private Integer id;
    private FuelType fuelType;
    private Float fuelConsumption;
    private final Integer numberOfCylinders;
    private Float CO2Emissions;
    private EngineLayout engineLayout;
    private final boolean hasTurbine;
    private final boolean hasSuperCharger;
    private StrokeType strokeType;
    private final Float displacement;
}
