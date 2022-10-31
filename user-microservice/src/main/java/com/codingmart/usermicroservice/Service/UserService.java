package com.codingmart.usermicroservice.Service;

import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.PasswordResetToken;
import com.codingmart.usermicroservice.Entity.UserData;
import com.codingmart.usermicroservice.Entity.VerificationToken;
import com.codingmart.usermicroservice.Repository.PasswordResetTokenRepository;
import com.codingmart.usermicroservice.Repository.UserRepository;
import com.codingmart.usermicroservice.Repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public  UserRepository userRepo;

    @Autowired
    public SupplierService supplierService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepo;

    // getmapping
    public APIResponse getAllUsers() {
        APIResponse apiresponse = new APIResponse();
        List<UserData> userData = userRepo.findAll();
        apiresponse.setData(userData);
        return apiresponse;
    }

    // getmapping
    public APIResponse getUserById(long id) {
        APIResponse apiresponse = new APIResponse();
        UserData userData = userRepo.findById(id).orElse(null);
        apiresponse.setData(userData);
        return apiresponse;
    }

    // postmapping
    public UserData addUser(UserData userData) {
        if (Objects.nonNull(userData.getSupplier())) {
            userData.setSupplier(supplierService.addSupplier(userData.getSupplier()));

        }
        return userRepo.save(userData);

    }

    // postmapping
    public List<UserData> saveUser(List<UserData> userData) {
        return userRepo.saveAll(userData);
    }

    // putMapping
    public UserData updateUser(UserData userData) {
        UserData ex = userRepo.findById(userData.getId()).orElse(null);
        ex.setMobileNo(userData.getMobileNo());
        ex.setEmailId(userData.getEmailId());
        return userRepo.save(ex);
    }

    // deleteMapping
    public String deleteUser(long id) {
        userRepo.deleteById(id);
        return "user removed !! " + id;
    }

    public void saveVerificationTokenForUser(String token, UserData userData) {
        VerificationToken verificationtoken = new VerificationToken(token, userData);
        verificationTokenRepo.save(verificationtoken);

    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationtoken = verificationTokenRepo.findByToken(token);
        if (verificationtoken == null) {
            return "invalid";
        }
        UserData userData = verificationtoken.getUserData();
        Calendar cal = Calendar.getInstance();
        if (verificationtoken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
            verificationTokenRepo.delete(verificationtoken);
            return "expired";

        }
        userData.setEnabled(true);
        userRepo.save(userData);
        return "valid";
    }

    public UserData findUserByEmailId(String emailId)
    {
        return userRepo.findByEmailId(emailId);
    }

    public void createPasswordResetTokenForUser(UserData userData, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, userData);
        passwordTokenRepo.save(passwordResetToken);
    }



    public String ValidatePasswordResetToken(String token)
	{
		PasswordResetToken passwordResetToken = passwordTokenRepo.findByToken(token);
		if (passwordResetToken == null)
		{
			return "invalid";
		}
		UserData userData = passwordResetToken.getUserData();
		Calendar cal = Calendar.getInstance();
		if (passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			passwordTokenRepo.delete(passwordResetToken);
			return "expired";

		}
			return "valid";
	}


    public Optional<UserData> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordTokenRepo.findByToken(token).getUserData());
    }
    public void changePassword(UserData userData, String newPassword) {
        userData.getSupplier().setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(userData);
    }

    public boolean checkIfValidOldPassword(UserData userData, String oldPassword)
    {
        return passwordEncoder.matches(oldPassword, userData.getSupplier().getPassword());
    }
}

//	public VerificationToken generateNewVerificationToken(String oldToken) {
//	        VerificationToken verificationToken = verificationtokenrepo.findByToken(oldToken);
//	        verificationToken.setToken(UUID.randomUUID().toString());
//	        verificationtokenrepo.save(verificationToken);
//	        return verificationToken;
//
//	}


