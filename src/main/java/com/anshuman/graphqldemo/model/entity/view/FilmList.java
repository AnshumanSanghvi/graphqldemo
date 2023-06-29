package com.anshuman.graphqldemo.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

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
@Table(name = FilmList.TABLE_NAME, schema = "public")
public class FilmList {
    public static final String TABLE_NAME = "film_list";
    public static final String COLUMN_FID_NAME = "fid";
    public static final String COLUMN_TITLE_NAME = "title";
    public static final String COLUMN_DESCRIPTION_NAME = "description";
    public static final String COLUMN_CATEGORY_NAME = "category";
    public static final String COLUMN_PRICE_NAME = "price";
    public static final String COLUMN_LENGTH_NAME = "length";
    public static final String COLUMN_RATING_NAME = "rating";
    public static final String COLUMN_ACTORS_NAME = "actors";


    @Id
    @Column(name = COLUMN_FID_NAME)
    private Integer fid;

    @Size(max = 255)
    @Column(name = COLUMN_TITLE_NAME)
    private String title;

    @Column(name = COLUMN_DESCRIPTION_NAME, length = Integer.MAX_VALUE)
    private String description;

    @Size(max = 25)
    @Column(name = COLUMN_CATEGORY_NAME, length = 25)
    private String category;

    @Column(name = COLUMN_PRICE_NAME, precision = 4, scale = 2)
    private BigDecimal price;

    @Column(name = COLUMN_LENGTH_NAME)
    private Short length;

    @Column(name = COLUMN_ACTORS_NAME, length = Integer.MAX_VALUE)
    private String actors;

/*
    TODO [JPA Buddy] create field to map the 'rating' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = COLUMN_RATING_NAME, columnDefinition = "mpaa_rating(0, 0)")
    private Object rating;
*/
}