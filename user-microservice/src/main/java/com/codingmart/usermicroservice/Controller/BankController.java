package com.codingmart.usermicroservice.Controller;


import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Bank;
import com.codingmart.usermicroservice.Service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
    public class BankController {
        BankService bankService;

        @GetMapping("/all")
        public APIResponse getAllBanks()
        {
            return bankService.getAllBanks();
        }
        @GetMapping("/bankbyId/{id}")
        public APIResponse getbankbyId(@PathVariable long id)
        {
            return bankService.getBankById(id);
        }
        @PostMapping("/addbanks")
        public void addBanks(@RequestBody List<Bank> bank)
        {
            bankService.saveBanks(bank);
        }
        @PostMapping("")
        public void add(@RequestBody Bank bank)
        {
            bankService.addBank(bank);
        }
        @PutMapping("/update")
        public Bank updateBank(@RequestBody Bank bank)
        {
            return  bankService.updateBank(bank);

        }
        @DeleteMapping("/delete")
        public void deleteBankById(@PathVariable long id)
        {
            bankService.deleteBank(id);

        }


    }

