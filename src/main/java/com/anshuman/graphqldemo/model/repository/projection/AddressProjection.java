package com.anshuman.graphqldemo.model.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AddressProjection implements IAddressProjection {
    private Integer id;
    private String addressLine;
    private String addressLine2;
    private String district;
    private Integer cityId;
    private String postalCode;
    private String phone;
}
