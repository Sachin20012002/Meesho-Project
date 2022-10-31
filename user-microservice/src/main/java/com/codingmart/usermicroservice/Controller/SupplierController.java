package com.codingmart.usermicroservice.Controller;


import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Supplier;
import com.codingmart.usermicroservice.Entity.User;
import com.codingmart.usermicroservice.Event.RegistrationCompleteEvent;
import com.codingmart.usermicroservice.Service.MailSenderService;
import com.codingmart.usermicroservice.Service.SupplierService;
import com.codingmart.usermicroservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ApplicationEventPublisher publisher;


    @GetMapping("/all")
    public APIResponse getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/supplierById/{id}")
    public APIResponse findSupplierById(@PathVariable long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping("")
    public Supplier addSupplier(@RequestBody Supplier supplier, final HttpServletRequest request) {

        return supplierService.addSupplier(supplier);
//	    publisher.publishEvent(new RegistrationCompleteEvent(sup,applicationUrl(request)));
//		return "success";
    }

    @PostMapping("/addSupplier")
    public void addSuppliers(@RequestBody List<Supplier> suppliers)
    {
         supplierService.saveSuppliers(suppliers);
    }

    @PutMapping("/update")
    public Supplier updateSupplier(@RequestBody Supplier supplier) {
        return supplierService.updateSupplier(supplier);

    }

    @DeleteMapping("/delete")
    public void deletesupplierbyid(@PathVariable long id) {
        supplierService.deleteSupplier(id);

    }
}

