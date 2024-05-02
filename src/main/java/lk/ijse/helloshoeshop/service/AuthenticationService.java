package lk.ijse.helloshoeshop.service;

import lk.ijse.helloshoeshop.secureAndResponse.response.JwtAuthResponse;
import lk.ijse.helloshoeshop.secureAndResponse.secure.SignIn;
import lk.ijse.helloshoeshop.secureAndResponse.secure.SignUp;

public interface AuthenticationService {
    JwtAuthResponse signUp(SignUp signUp);
    JwtAuthResponse signIn(SignIn signin);
    JwtAuthResponse refreshToken(String refreshToken);
}
