package com.neo.customer.entity;

import com.neo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ABODE
 * @Date 2025/03/07 10:38 PM
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address extends BaseEntity {

    private String state;

    private String city;

    private String zipCode;

    private String street;

    private String houseNumber;
}
