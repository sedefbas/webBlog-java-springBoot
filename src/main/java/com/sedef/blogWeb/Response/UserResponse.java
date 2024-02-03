package com.sedef.blogWeb.Response;

import lombok.Data;

@Data
public class UserResponse {
    String message;
    Long userId;
    String accessToken;
}
