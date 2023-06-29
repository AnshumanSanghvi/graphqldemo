package com.anshuman.graphqldemo.model.repository.projection;

public interface IAddressProjection {

    Integer getId();

    String getAddress();

    String getAddress2();

    String getDistrict();

    Integer getCityId();

    String getPostalCode();

    String getPhone();

    public static AddressProjection toPojo(IAddressProjection projection) {
        return new AddressProjection(projection.getId(), projection.getAddress(),
                projection.getAddress2(), projection.getDistrict(), projection.getCityId(),
                projection.getPostalCode(), projection.getPhone());
    }
}
