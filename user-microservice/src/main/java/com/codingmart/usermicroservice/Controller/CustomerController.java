package com.codingmart.usermicroservice.Controller;


import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Customer;
import com.codingmart.usermicroservice.Service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
    public class CustomerController {

        CustomerService customerService;

        @GetMapping("/all")
        public APIResponse getallcustomers() {
            return customerService.getAllCustomers();
        }

        @GetMapping("/customerbyId/{id}")
        public APIResponse getcustomerbyId(@PathVariable long id) {
            return customerService.getCustomerById(id);
        }

        @PostMapping("/addcustomer")
        public void addcustomer(@RequestBody List<Customer> customers) {
            customerService.saveCustomers(customers);
        }

        @PostMapping("")
        public void add(@RequestBody Customer customers) {
            customerService.addCustomer(customers);
        }

        @PutMapping("/update")
        public Customer updateCustomer(@RequestBody Customer customers) {
            return customerService.updateCustomer(customers);

        }

        @DeleteMapping("/delete")
        public void deleteCustomerbyid(@PathVariable long id) {
            customerService.deleteCustomer(id);

        }

    }


