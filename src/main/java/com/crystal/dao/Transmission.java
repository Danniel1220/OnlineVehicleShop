package com.crystal.dao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Transmission {
    private final Integer id;
    private final TransimissionType type;
    private final Integer numberOfGears;
}
