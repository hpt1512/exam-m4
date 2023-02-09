package com.example.examm4.repository;

import com.example.examm4.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IContractRepository extends JpaRepository<Contract, String> {
    Contract findContractById(String id);

    @Query(value="select * from contract where customer_id like concat(\"%\" , ? , \"%\") and type_service like concat(\"\" , ? , \"%\");", nativeQuery=true)
    List<Contract> findContractByCustomerAndService(String idCustomerFind, String serviceFind);
}
