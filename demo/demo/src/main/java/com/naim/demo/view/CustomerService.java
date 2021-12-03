package com.naim.demo.view;

import com.naim.demo.dao.CustomerDAO;
import com.naim.demo.exception.CustomerNotFoundException;
import com.naim.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDao;

    public Customer addCustomer(Customer customer) {
        return customerDao.save(customer);
    }
    public Page<Customer> getCustomers(
            @RequestParam Optional<Integer> page
    ) {
        return customerDao.findAll(
                PageRequest.of(
                        page.orElse(0), 10)
        );
    }

    public Customer getCustomer(int customerId) {

        Optional<Customer> optionalCustomer = customerDao.findById(customerId);

        if(!optionalCustomer.isPresent())
            throw new CustomerNotFoundException("Customer ID not found!");

        return optionalCustomer.get();
    }

   public Customer updateCustomer(int customerId, Customer customer){
       customer.setCustomerId(customerId);
       return customerDao.save(customer);
    }

    public void deleteCustomer (int customerId) {
        customerDao.deleteById(customerId);
    }
}
