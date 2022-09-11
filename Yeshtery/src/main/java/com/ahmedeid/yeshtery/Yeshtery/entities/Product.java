package com.ahmedeid.yeshtery.Yeshtery.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "photo_path")
    private String photoPath;
    private String category;
    private String description;
    private int accepted;
    private int rejected;
    @Column(name = "is_deleted")
    private int isDeleted;

}
