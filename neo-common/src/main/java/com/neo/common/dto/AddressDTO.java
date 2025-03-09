package com.neo.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:10 AM
 */
@Data
@Builder
public class AddressDTO {

    private String state;

    private String city;

    private String zipCode;

    private String street;

    private String houseNumber;

}
