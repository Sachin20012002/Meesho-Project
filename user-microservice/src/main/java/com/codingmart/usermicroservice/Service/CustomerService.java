package com.codingmart.usermicroservice.Service;

import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Customer;
import com.codingmart.usermicroservice.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepo;

    //getmapping
    public APIResponse getAllCustomers()
    {
        APIResponse apiresponse=new APIResponse();
        List<Customer> customers =  customerRepo.findAll();
        apiresponse.setData(customers);
        return apiresponse;
    }
    public APIResponse getCustomerById(long id)
    {
        APIResponse apiresponse=new APIResponse();
        Customer customer =customerRepo.findById(id).orElse(null);
        apiresponse.setData(customer);
        return apiresponse;
    }
    //postmapping
    public List<Customer> saveCustomers(List<Customer> customer) {
        return customerRepo.saveAll(customer);
    }
    //postmapping
    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }
    //putMapping
    public Customer updateCustomer(Customer customer)
    {
        Customer ex=customerRepo.findById(customer.getId()).orElse(null);
        ex.setId(customer.getId());
        ex.setName(customer.getName());
        return customerRepo.save(ex);
    }
    //deleteMapping
    public String deleteCustomer(long id) {
        customerRepo.deleteById(id);
        return "customer removed !! " + id;
    }

}
