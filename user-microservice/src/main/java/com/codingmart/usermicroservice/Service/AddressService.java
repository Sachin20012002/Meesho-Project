package com.codingmart.usermicroservice.Service;

import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Address;
import com.codingmart.usermicroservice.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    public AddressRepository addressRepo;

    //getmapping
    public APIResponse getAllAddress()
    {
        APIResponse apiresponse=new APIResponse();
        List<Address> addr =  addressRepo.findAll();
        apiresponse.setData(addr);
        return apiresponse;
    }
    public APIResponse getAddressById(long id)
    {
        APIResponse apiresponse=new APIResponse();
        Address address=addressRepo.findById(id).orElse(null);
        apiresponse.setData(address);
        return apiresponse;
    }
    //postmapping
    public List<Address> saveAddress(List<Address> addr) {
        return addressRepo.saveAll(addr);
    }
    //postmapping
    public void addAddress(Address address) {
        addressRepo.save(address);
    }
    //putMapping
    public Address updateAddress(Address address)
    {
        Address ad=addressRepo.findById(address.getId()).orElse(null);
        ad.setArea(address.getArea());
        ad.setTown(address.getTown());
        ad.setState(address.getState());
        ad.setCountry(address.getCountry());
        ad.setPinCode(address.getPinCode());
        return addressRepo.save(ad);
    }
    //deleteMapping
    public String deleteAddress(long id) {
        addressRepo.deleteById(id);
        return "address removed !! " + id;
    }




}


