package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticateUserDto;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.TokenJwtDto;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authUser(@RequestBody @Valid AuthenticateUserDto authenticateUserDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(authenticateUserDto.username(), authenticateUserDto.password());
        var userAuthenticated = authenticationManager.authenticate(authenticationToken);
        var JwtToken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(JwtToken));
    }
}
