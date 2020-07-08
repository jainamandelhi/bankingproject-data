package aman.project.springbootstarter.transaction;

import aman.project.springbootstarter.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
    List<Transaction> findBySenderAccountOrReceiverAccount(Integer accountId, Integer accountId2);
}
