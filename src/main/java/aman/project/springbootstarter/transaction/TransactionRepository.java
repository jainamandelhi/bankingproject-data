package aman.project.springbootstarter.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
    //@Query(value = "SELECT * FROM transactions WHERE sender_id = :id", nativeQuery = true)
    List<Transaction> getTransactionsByAccountId(
            @Param("id") Integer id);
}
