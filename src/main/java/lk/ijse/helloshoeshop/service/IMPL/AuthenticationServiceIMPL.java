package lk.ijse.helloshoeshop.service.IMPL;

import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.UserDTO;
import lk.ijse.helloshoeshop.entity.UserEntity;
import lk.ijse.helloshoeshop.repository.UserServiceDAO;
import lk.ijse.helloshoeshop.secureAndResponse.response.JwtAuthResponse;
import lk.ijse.helloshoeshop.secureAndResponse.secure.SignIn;
import lk.ijse.helloshoeshop.secureAndResponse.secure.SignUp;
import lk.ijse.helloshoeshop.service.AuthenticationService;
import lk.ijse.helloshoeshop.service.JwtService;
import lk.ijse.helloshoeshop.util.UtilMatter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {

    private final ConversionData conversionData;
    private final UserServiceDAO userDao;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthResponse signUp(SignUp signUp) {
        UserDTO userDTO = UserDTO.builder()
                .id(UtilMatter.generateId())
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(signUp.getRole())
                .build();
        UserEntity saveUser = userDao.save(conversionData.toUserEntity(userDTO));
        String generateToken = jwtService.generateToken(saveUser);
        return JwtAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword())
        );
        UserEntity user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generateToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JwtAuthResponse refreshToken(String refreshToken) {
        var userEntity = userDao
                .findByEmail(jwtService.extractUserName(refreshToken))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return JwtAuthResponse.builder().
                token(jwtService.generateToken(userEntity)).build();
    }
}