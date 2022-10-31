package com.codingmart.usermicroservice.Service;

import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.PasswordResetToken;
import com.codingmart.usermicroservice.Entity.User;
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
        List<User> users = userRepo.findAll();
        apiresponse.setData(users);
        return apiresponse;
    }

    // getmapping
    public APIResponse getUserById(long id) {
        APIResponse apiresponse = new APIResponse();
        User user = userRepo.findById(id).orElse(null);
        apiresponse.setData(user);
        return apiresponse;
    }

    // postmapping
    public User addUser(User user) {
        if (Objects.nonNull(user.getSupplier())) {
            user.setSupplier(supplierService.addSupplier(user.getSupplier()));

        }
        return userRepo.save(user);

    }

    // postmapping
    public List<User> saveUser(List<User> users) {
        return userRepo.saveAll(users);
    }

    // putMapping
    public User updateUser(User user) {
        User ex = userRepo.findById(user.getId()).orElse(null);
        ex.setMobileNo(user.getMobileNo());
        ex.setEmailId(user.getEmailId());
        return userRepo.save(ex);
    }

    // deleteMapping
    public String deleteUser(long id) {
        userRepo.deleteById(id);
        return "user removed !! " + id;
    }

    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationtoken = new VerificationToken(token, user);
        verificationTokenRepo.save(verificationtoken);

    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationtoken = verificationTokenRepo.findByToken(token);
        if (verificationtoken == null) {
            return "invalid";
        }
        User user = verificationtoken.getUser();
        Calendar cal = Calendar.getInstance();
        if (verificationtoken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
            verificationTokenRepo.delete(verificationtoken);
            return "expired";

        }
        user.setEnabled(true);
        userRepo.save(user);
        return "valid";
    }

    public User findUserByEmailId(String emailId)
    {
        return userRepo.findByEmailId(emailId);
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordTokenRepo.save(passwordResetToken);
    }



    public String ValidatePasswordResetToken(String token)
	{
		PasswordResetToken passwordResetToken = passwordTokenRepo.findByToken(token);
		if (passwordResetToken == null)
		{
			return "invalid";
		}
		User user = passwordResetToken.getUser();
		Calendar cal = Calendar.getInstance();
		if (passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			passwordTokenRepo.delete(passwordResetToken);
			return "expired";

		}
			return "valid";
	}


    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordTokenRepo.findByToken(token).getUser());
    }
    public void changePassword(User user, String newPassword) {
        user.getSupplier().setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword)
    {
        return passwordEncoder.matches(oldPassword, user.getSupplier().getPassword());
    }
}

//	public VerificationToken generateNewVerificationToken(String oldToken) {
//	        VerificationToken verificationToken = verificationtokenrepo.findByToken(oldToken);
//	        verificationToken.setToken(UUID.randomUUID().toString());
//	        verificationtokenrepo.save(verificationToken);
//	        return verificationToken;
//
//	}


