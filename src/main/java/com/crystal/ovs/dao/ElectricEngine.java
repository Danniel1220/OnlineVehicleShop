package com.crystal.ovs.dao;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ElectricEngine {
    private final Integer id;
    private String type;
    private Integer batteryCapacity;
    private Integer range;
}
