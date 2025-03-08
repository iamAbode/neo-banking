package com.neo.customer.entity;

import com.neo.common.entity.BaseEntity;
import com.neo.common.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * @Author ABODE
 * @Date 2025/03/07 9:53 PM
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String customerId;

    private String name;

    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
