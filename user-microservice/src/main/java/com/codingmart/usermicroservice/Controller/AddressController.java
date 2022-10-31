package com.codingmart.usermicroservice.Controller;


import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Address;
import com.codingmart.usermicroservice.Service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

        AddressService addressService;

        @GetMapping("/all")
        public APIResponse getAllAddress()
        {
            return addressService.getAllAddress();
        }
        @GetMapping("/addressbyId/{id}")
        public APIResponse getAddressById(@PathVariable long id)
        {
            return addressService.getAddressById(id);
        }
        @PostMapping("/addAddress")
        public void addAddress(@RequestBody List<Address> address)
        {
            addressService.saveAddress(address);
        }
        @PostMapping("")
        public void add(@RequestBody Address address)
        {
            addressService.addAddress(address);
        }
        @PutMapping("/update")
        public Address updateAddress(@RequestBody Address address)
        {
            return  addressService.updateAddress(address);

        }
        @DeleteMapping("/delete")
        public void deleteAddressById(@PathVariable long id)
        {
            addressService.deleteAddress(id);

        }
}

