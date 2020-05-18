package com.vzw.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vzw.customerservice.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
