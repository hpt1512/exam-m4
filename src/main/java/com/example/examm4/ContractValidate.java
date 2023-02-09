package com.example.examm4;

import com.example.examm4.model.Contract;
import com.example.examm4.service.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
public class ContractValidate implements Validator {
    @Autowired
    private ContractServiceImpl contractService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contract contract = (Contract) target;
        Date date = new Date();

        if (contractService.findContractById(contract.getId()) != null) {
            errors.rejectValue("id","id.validate",null,"Mã giao dịch trùng");
        }

        if (contract.getDate() == null) {
            errors.rejectValue("date","date.validate.null",null,"Bạn chưa chọn ngày");
        }
        else {
            if(date.after(contract.getDate())) {
                errors.rejectValue("date","date.validate.start",null,"Ngày trong giao dịch không được trước ngày hiện tại");
            }
        }
    }
}
