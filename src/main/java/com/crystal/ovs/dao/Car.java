package com.crystal.ovs.dao;

import lombok.*;
import java.awt.*;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Car {
    private final Integer id;
    private final String brand;
    private final String model;
    private final String VIN;
    private final Integer manufacturingYear;
    private final CarType carType;
    private int engineId;
    private int transmissionId;
    private TractionType tractionType;
    private final Integer numberOfDoors;
    private Color color;
}
