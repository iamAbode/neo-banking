package com.neo.identity.entity;

import com.neo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * @Author ABODE
 * @Date 2025/03/09 7:00 PM
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String email;
    private String password;
    private String roles;
}
