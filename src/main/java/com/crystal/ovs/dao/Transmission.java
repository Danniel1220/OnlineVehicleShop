package com.crystal.ovs.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Transmission {
    private final Integer id;
    private final TransmissionType type;
    private final Integer numberOfGears;
}
