package com.anshuman.graphqldemo.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Immutable
@Table(name = "staff_list", schema = "public")
public class StaffList {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Size(max = 50)
    @Column(name = "address", length = 50)
    private String address;

    @Size(max = 10)
    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 50)
    @Column(name = "city", length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "sid")
    private Short sid;

}