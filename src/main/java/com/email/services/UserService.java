package com.email.services;

import com.email.payload.AppUSerResponseDto;

public interface UserService {

  AppUSerResponseDto RegisterUser(AppUSerResponseDto dto);

  String verifyUser(String email, String otp);
}
