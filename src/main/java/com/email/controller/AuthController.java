package com.email.controller;


import com.email.payload.AppUSerResponseDto;
import com.email.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/user-register}")
    public ResponseEntity<AppUSerResponseDto> sendEmail(@RequestBody AppUSerResponseDto appUserDto){
        AppUSerResponseDto registered = userService.RegisterUser(appUserDto);
        return new ResponseEntity<>(registered,HttpStatus.CREATED);
    }


    @PostMapping("/user-verify")
    public ResponseEntity <?> verifiedUser(@RequestBody String email, @RequestParam String otp){
      String result= userService.verifyUser(email,otp);
      return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
