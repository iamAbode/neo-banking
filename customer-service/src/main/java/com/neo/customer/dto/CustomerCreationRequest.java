package com.neo.customer.dto;

import com.neo.common.dto.AddressDTO;
import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:09 AM
 */
@Data
@Builder
public class CustomerCreationRequest {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits")
    private String phone;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Address is required")
    private AddressDTO address;

    private EntityStatus status;

}
