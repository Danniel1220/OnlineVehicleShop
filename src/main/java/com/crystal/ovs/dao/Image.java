package com.crystal.ovs.dao;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
@Setter
public class Image {
    private Integer id;
    private String imageUrl;
    private Integer postId;
}
