package com.neo.common.dto;

import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:09 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private String customerId;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private Gender gender;

    private AddressDTO address;

    private EntityStatus status;

}
