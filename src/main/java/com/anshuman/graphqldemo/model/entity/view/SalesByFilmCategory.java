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
@Table(name = SalesByFilmCategory.TABLE_NAME, schema = "public")
public class SalesByFilmCategory {
    public static final String TABLE_NAME = "sales_by_film_category";
    public static final String COLUMN_CATEGORY_NAME = "category";
    public static final String COLUMN_TOTALSALES_NAME = "total_sales";

    @Id
    @Size(max = 25)
    @Column(name = COLUMN_CATEGORY_NAME, length = 25)
    private String category;

    @Column(name = COLUMN_TOTALSALES_NAME)
    private BigDecimal totalSales;

}