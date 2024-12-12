package com.email.services;

import com.email.entity.AppUser;
import com.email.payload.AppUSerResponseDto;
import com.email.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public AppUSerResponseDto RegisterUser(@RequestBody AppUSerResponseDto dto) {
        AppUSerResponseDto appUser=new AppUSerResponseDto();

        AppUser emailId = appUserRepository.findByEmailId(dto.getEmailId());
        if(emailId!=null){
           appUser.setMessage("User Already Exist......");
        }
        else {
            Random random=new Random();
            String nextInt = String.valueOf(random.nextInt(9999 + 1));

            AppUser newUser=new AppUser();
            newUser.setUserName(dto.getUserName());
            newUser.setEmailId(dto.getEmailId());
            newUser.setPassword(dto.getPassword());
            newUser.setOtp(nextInt);
            newUser.setVerified(false);

            AppUser save =appUserRepository.save(newUser);

            emailService.sendEmail(dto.getEmailId(),"Dear "+dto.getUserName(),"This is Your One Time Password (OTP) --->"+save.getOtp());

            appUser.setId(save.getId());
            appUser.setUserName(save.getUserName());
            appUser.setEmailId(save.getEmailId());
            appUser.setPassword(save.getPassword());
            appUser.setVerified(save.getVerified());
            appUser.setMessage("Email Send SuccessFully");
        }
        return appUser;
    }

    @Override
    public String verifyUser(String email,String otp) {
        AppUser emailId = appUserRepository.findByEmailId(email);

        if (emailId != null) {
           String  response = "USer is Already verified.......";
        } else if (otp.equals(emailId.getOtp())) {
            emailId.setVerified(true);
            AppUser save = appUserRepository.save(emailId);
            String re= "User Verified successfully...";
        } else {
             String re= "User Not Found....";
        }
        return "response";
    }

}
