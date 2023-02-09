package com.example.examm4.service.impl;

import com.example.examm4.model.Contract;
import com.example.examm4.repository.IContractRepository;
import com.example.examm4.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements IBaseService<Contract> {
    @Autowired
    private IContractRepository contractRepository;

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findById(Integer id) {
        return null;
    }

    @Override
    public void insert(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public void update(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public void delete(Contract contract) {
        contractRepository.delete(contract);
    }
    public Contract findContractById(String id) {
        return contractRepository.findContractById(id);
    }
    public List<Contract> findByCustomerAndService(String customerFind, String serviceFind) {
        return contractRepository.findContractByCustomerAndService(customerFind, serviceFind);
    }
}
