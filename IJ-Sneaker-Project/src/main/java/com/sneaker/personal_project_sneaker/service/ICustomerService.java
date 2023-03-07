package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.entity.Customer;

public interface ICustomerService extends IGeneralService<Customer>{

//    Customer findCustomerByAccount_Id(Integer id);

    Customer findCustomerByEmail (String email);
}
