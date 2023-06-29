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
@Table(name = CustomerList.TABLE_NAME, schema = "public")
public class CustomerList {
    public static final String TABLE_NAME = "customer_list";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_ADDRESS_NAME = "address";
    public static final String COLUMN_ZIPCODE_NAME = "\"zip code\"";
    public static final String COLUMN_PHONE_NAME = "phone";
    public static final String COLUMN_CITY_NAME = "city";
    public static final String COLUMN_COUNTRY_NAME = "country";
    public static final String COLUMN_NOTES_NAME = "notes";
    public static final String COLUMN_SID_NAME = "sid";


    @Id
    @Column(name = COLUMN_ID_NAME)
    private Integer id;

    @Column(name = COLUMN_NAME_NAME, length = Integer.MAX_VALUE)
    private String name;

    @Size(max = 50)
    @Column(name = COLUMN_ADDRESS_NAME, length = 50)
    private String address;

    @Size(max = 10)
    @Column(name = COLUMN_ZIPCODE_NAME, length = 10)
    private String zipCode;

    @Size(max = 20)
    @Column(name = COLUMN_PHONE_NAME, length = 20)
    private String phone;

    @Size(max = 50)
    @Column(name = COLUMN_CITY_NAME, length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = COLUMN_COUNTRY_NAME, length = 50)
    private String country;

    @Column(name = COLUMN_NOTES_NAME, length = Integer.MAX_VALUE)
    private String notes;

    @Column(name = COLUMN_SID_NAME)
    private Short sid;

}