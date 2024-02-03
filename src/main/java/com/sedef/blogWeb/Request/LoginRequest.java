package com.sedef.blogWeb.Request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginRequest implements AuthenticationRequest{

    private String userName;

    private String password;
}
