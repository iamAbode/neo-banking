package com.neo.transaction.converter;

import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.transaction.dto.TransactionCreationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:21 PM
 */
@Component
public class TransactionServiceConverter {

    public TransactionDTO convert(TransactionCreationRequest request){
        TransactionDTO transactionDTO = TransactionDTO.builder().build();
        BeanUtils.copyProperties(request, transactionDTO);
        return transactionDTO;

    }


}
