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
@Table(name = "actor_info", schema = "public")
public class ActorInfo {

    @Id
    @Column(name = "actor_id")
    private Integer actorId;

    @Size(max = 45)
    @Column(name = "first_name", length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "film_info", length = Integer.MAX_VALUE)
    private String filmInfo;

}