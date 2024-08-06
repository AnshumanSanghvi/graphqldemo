package com.anshuman.graphqldemo.model.repository.projection;

import java.util.List;

public interface IAddressProjection {

    Integer getId();

    String getAddressLine();

    String getAddressLine2();

    String getDistrict();

    Integer getCityId();

    String getPostalCode();

    String getPhone();

    static AddressProjection toPojo(IAddressProjection projection) {
        return new AddressProjection(projection.getId(), projection.getAddressLine(),
                projection.getAddressLine2(), projection.getDistrict(), projection.getCityId(),
                projection.getPostalCode(), projection.getPhone());
    }

    static List<AddressProjection> toPojoList(List<IAddressProjection> projectionList) {
        return projectionList.stream().map(IAddressProjection::toPojo).toList();
    }
}
