package swahili.cafe.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swahili.cafe.model.User;
import swahili.cafe.model.dto.LoginRequestDTO;
import swahili.cafe.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequestDTO requestDTO) {
        authenticateUser(requestDTO.getUsername(), requestDTO.getPassword());
        User loginUser = userService.findUserByUsername(requestDTO.getUsername());
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }


    private void authenticateUser(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
