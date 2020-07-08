package aman.project.springbootstarter.account;
import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{
    List<Account> findByUser(Integer userId);

}
