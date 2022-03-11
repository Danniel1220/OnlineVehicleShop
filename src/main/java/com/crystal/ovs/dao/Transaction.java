package com.crystal.ovs.dao;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Transaction {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private Date date;
}
