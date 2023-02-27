package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.entity.Customer;
import com.sneaker.personal_project_sneaker.repository.ICustomerRepository;
import com.sneaker.personal_project_sneaker.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.removeCustomer(id);
    }

    @Override
    public Customer findCustomerByAccount_Id(Integer id) {
        return customerRepository.findCustomerByAccount_Id(id);
    }
}
