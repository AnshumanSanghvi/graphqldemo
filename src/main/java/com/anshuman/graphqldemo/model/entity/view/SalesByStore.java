package com.anshuman.graphqldemo.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = SalesByStore.TABLE_NAME, schema = "public")
public class SalesByStore {
    public static final String TABLE_NAME = "sales_by_store";
    public static final String COLUMN_STORE_NAME = "store";
    public static final String COLUMN_MANAGER_NAME = "manager";
    public static final String COLUMN_TOTALSALES_NAME = "total_sales";


    @Id
    @Column(name = COLUMN_STORE_NAME, length = Integer.MAX_VALUE)
    private String store;

    @Column(name = COLUMN_MANAGER_NAME, length = Integer.MAX_VALUE)
    private String manager;

    @Column(name = COLUMN_TOTALSALES_NAME)
    private BigDecimal totalSales;

}