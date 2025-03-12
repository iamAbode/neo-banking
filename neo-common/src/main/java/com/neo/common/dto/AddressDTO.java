package com.neo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:10 AM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private String state;

    private String city;

    private String zipCode;

    private String street;

    private String houseNumber;

}
