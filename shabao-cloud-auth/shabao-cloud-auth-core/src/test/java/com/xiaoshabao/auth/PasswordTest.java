package com.xiaoshabao.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class PasswordTest {
  
  @Test
  public void password() {
   String password= PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password");
   System.out.println(password);
  }

}
