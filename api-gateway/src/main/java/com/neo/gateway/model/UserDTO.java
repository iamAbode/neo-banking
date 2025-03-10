package com.neo.gateway.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Author ABODE
 * @Date 2025/03/09 11:38 PM
 */
@Data
@Builder
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private String roles;
}
