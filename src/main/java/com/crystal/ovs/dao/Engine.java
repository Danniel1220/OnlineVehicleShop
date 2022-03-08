package com.crystal.ovs.dao;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
@Setter
public class Engine {
    private final Integer id;
    private Integer fuelEngineId;
    private Integer electricEngineId;
    private Integer horsePower;
    private Integer torque;
}
