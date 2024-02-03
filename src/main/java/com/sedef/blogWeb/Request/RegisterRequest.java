package com.sedef.blogWeb.Request;

import com.sedef.blogWeb.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest implements AuthenticationRequest{

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    Set<Role> authorities;

}
