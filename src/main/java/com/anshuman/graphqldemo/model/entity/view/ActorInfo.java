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
@Table(name = ActorInfo.TABLE_NAME, schema = "public")
public class ActorInfo {
    public static final String TABLE_NAME = "actor_info";
    public static final String COLUMN_ACTORID_NAME = "actor_id";
    public static final String COLUMN_FIRSTNAME_NAME = "first_name";
    public static final String COLUMN_LASTNAME_NAME = "last_name";
    public static final String COLUMN_FILMINFO_NAME = "film_info";


    @Id
    @Column(name = COLUMN_ACTORID_NAME)
    private Integer actorId;

    @Size(max = 45)
    @Column(name = COLUMN_FIRSTNAME_NAME, length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = COLUMN_LASTNAME_NAME, length = 45)
    private String lastName;

    @Column(name = COLUMN_FILMINFO_NAME, length = Integer.MAX_VALUE)
    private String filmInfo;

}