package com.codingmart.usermicroservice.Service;

import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Bank;
import com.codingmart.usermicroservice.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankService {
    @Autowired
    public BankRepository bankRepo;

    //getmapping
    public APIResponse getAllBanks()
    {
        APIResponse apiresponse=new APIResponse();
        List<Bank> bank =  bankRepo.findAll();
        apiresponse.setData(bank);
        return apiresponse;
    }
    public APIResponse getBankById(long id)
    {
        APIResponse apiresponse=new APIResponse();
        Bank bank=bankRepo.findById(id).orElse(null);
        apiresponse.setData(bank);
        return apiresponse;
    }
    //postmapping
    public List<Bank> saveBanks(List<Bank> bank) {
        return bankRepo.saveAll(bank);
    }
    //postmapping
    public void addBank(Bank bank) {
        bankRepo.save(bank);
    }
    //putMapping
    public Bank updateBank(Bank bank)
    {
        Bank ban=bankRepo.findById(bank.getId()).orElse(null);
        ban.setAccountno(bank.getAccountno());
        ban.setIfsccode(bank.getIfsccode());
        return bankRepo.save(ban);
    }
    //deleteMapping
    public String deleteBank(long id) {
        bankRepo.deleteById(id);
        return "address removed !! " + id;
    }


}

