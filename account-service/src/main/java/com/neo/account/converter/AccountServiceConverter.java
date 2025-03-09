package com.neo.account.converter;

import com.neo.account.dto.AccountCreationRequest;
import com.neo.account.dto.AccountCreationServiceParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Author ABODE
 * @Date 2025/03/08 2:14 PM
 */
@Component
public class AccountServiceConverter {
    public AccountCreationServiceParam convert(AccountCreationRequest request){
        AccountCreationServiceParam serviceParam = AccountCreationServiceParam.builder().build();
        BeanUtils.copyProperties(request, serviceParam);
        return serviceParam;
    }

}
