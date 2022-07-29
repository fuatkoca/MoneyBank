package com.tpebank.repository;

import com.tpebank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("select t from Transaction t where t.date between :sDate and :eDate order by t.date desc")
    List<Transaction> getBankStatement(@Param(value="sDate")LocalDateTime startDate, @Param(value="eDate")LocalDateTime endDate);


}
