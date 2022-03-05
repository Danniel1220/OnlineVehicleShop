package com.crystal.ovs.dao;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Transmission {
    private Integer id;
    private TransmissionType type;
    private Integer numberOfGears;
}
