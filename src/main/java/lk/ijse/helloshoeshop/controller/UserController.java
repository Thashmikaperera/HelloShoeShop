package lk.ijse.helloshoeshop.controller;

import lk.ijse.helloshoeshop.secureAndResponse.response.JwtAuthResponse;
import lk.ijse.helloshoeshop.secureAndResponse.secure.SignIn;
import lk.ijse.helloshoeshop.secureAndResponse.secure.SignUp;
import lk.ijse.helloshoeshop.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthResponse> signup(@RequestBody SignUp signup){
        return ResponseEntity.ok(authenticationService.signUp(signup));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @GetMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refresh(
            @RequestParam ("refresh Token") String refreshToken
    ){
        System.out.println(refreshToken);
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}