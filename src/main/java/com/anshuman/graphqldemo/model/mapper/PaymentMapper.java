package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Payment;
import com.anshuman.graphqldemo.resource.dto.PaymentRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CustomerMapper.class, StaffMapper.class, RentalMapper.class})
public interface PaymentMapper {
    Payment toEntity(PaymentRecord paymentRecord);

    PaymentRecord toDto(Payment payment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment partialUpdate(PaymentRecord paymentRecord, @MappingTarget Payment payment);
}