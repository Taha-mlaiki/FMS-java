package com.farm_management.fms.modules.auth.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginResponse {
          public  final String token;
          public final String message;
}
