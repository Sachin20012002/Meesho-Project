package com.codingmart.usermicroservice.Service;

import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Customer;
import com.codingmart.usermicroservice.Entity.Supplier;
import com.codingmart.usermicroservice.Entity.User;
import com.codingmart.usermicroservice.Repository.SupplierRepository;
import com.codingmart.usermicroservice.Repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

        @Autowired
        private SupplierRepository supplierRepo;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private VerificationTokenRepository verificationTokenRepo;



    //getmapping
    public APIResponse getAllSuppliers()
    {
        APIResponse apiresponse=new APIResponse();
        List<Supplier> suppliers = supplierRepo.findAll();
        apiresponse.setData(suppliers);
        return apiresponse;
    }
    public APIResponse getSupplierById(long id)
    {
        APIResponse apiresponse=new APIResponse();
        Supplier supplier =supplierRepo.findById(id).orElse(null);
        apiresponse.setData(supplier);
        return apiresponse;
    }
    //postmapping
    public List<Supplier> saveSuppliers(List<Supplier> supplier)
    {
        return supplierRepo.saveAll(supplier);
    }
    //postmapping
    public Supplier addSupplier(Supplier supplier)
    {
//	 supplier.setGstDetails(supplier.getGstdetails());
        supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
        return supplierRepo.save(supplier);

    }

    //putMapping
    public Supplier updateSupplier(Supplier supplier)
    {
        Supplier ex=supplierRepo.findById(supplier.getId()).orElse(null);
        ex.setId(supplier.getId());
        ex.setPassword(supplier.getPassword());
        ex.setGstDetails(supplier.getGstDetails());
        return supplierRepo.save(ex);
    }
    //deleteMapping
    public String deleteSupplier(long id) {
        supplierRepo.deleteById(id);
        return "customer removed !! " + id;
    }
}

