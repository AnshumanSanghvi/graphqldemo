package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Payment;
import org.springframework.data.repository.ListCrudRepository;

public interface PaymentRepository extends ListCrudRepository<Payment, Integer> {
}