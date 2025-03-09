package com.neo.customer.converter;

import com.neo.common.dto.CustomerDTO;
import com.neo.customer.dto.CustomerCreationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Author ABODE
 * @Date 2025/03/08 3:53 PM
 */
@Component
public class CustomerServiceConverter {

    public <E, D> D convertEntityToData(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert entity to DTO", e);
        }
    }

    public CustomerDTO convert(CustomerCreationRequest request){
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        BeanUtils.copyProperties(request, customerDTO);
        return customerDTO;
    }


}
