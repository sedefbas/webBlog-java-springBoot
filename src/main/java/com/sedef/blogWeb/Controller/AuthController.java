package com.sedef.blogWeb.Controller;

import com.sedef.blogWeb.Businnes.concretes.JwtService;
import com.sedef.blogWeb.Businnes.concretes.UserService;
import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Model.User;
import com.sedef.blogWeb.Request.AuthenticationRequest;
import com.sedef.blogWeb.Request.LoginRequest;
import com.sedef.blogWeb.Request.RegisterRequest;
import com.sedef.blogWeb.Response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@SecurityRequirement(name = "bearerAuth") //buradaki apiler için token gerektiği belirtilmiş oluyor.
@Tag(name = "Auth :) ehehehh ı am just making joke ")
public class AuthController {
    private UserService service;

    private JwtService jwtService;

    private  AuthenticationManager authenticationManager;

    public AuthController(UserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(
            description = "get endpoint for auth controller",
            summary ="this is a summary for management get endpoint" ,
            responses = {
                    @ApiResponse(
                    description = "succes :) oley",
                    responseCode = "200"
            ),
                    @ApiResponse(
                    description = "Unauthorized / Inavalid  not lucky day",
                    responseCode = "200"
                    )
            }
    )
    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World! this is FOLSDEV";
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) throws NotFoundException {
        UserResponse userResponse = new UserResponse();
        if(service.getByUserName(request.getUserName()) !=null){
            userResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
        User user = service.addUser(request);
        String jwtToken = generateToken(request);
        UserResponse response =new UserResponse();
        response.setAccessToken("Bearer " + jwtToken);
        response.setUserId(user.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }




    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) throws NotFoundException {
        String jwtToken = generateToken(request);
        User user = service.getByUserName(request.getUserName());
        UserResponse response =new UserResponse();
        response.setAccessToken("Bearer " + jwtToken);
        response.setUserId(user.getId());
        return response;
    }


    public String generateToken(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getUserName());
        }
        log.info("invalid username " + request.getUserName());
        throw new UsernameNotFoundException("invalid username {} " +request.getUserName());
    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is USER!";
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is ADMIN!";
    }


}
