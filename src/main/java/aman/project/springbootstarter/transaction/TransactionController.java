package aman.project.springbootstarter.transaction;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aman.project.springbootstarter.user.UserService;



@RestController
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionService transactionService;

	@RequestMapping("/transactions")
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}
	@RequestMapping("/transactions/{id}")
	public Optional<Transaction> getTransaction(@PathVariable Integer id)
	{
		return transactionService.getTransaction(id);

	}
	@RequestMapping("/transactionsByAccountId/{id}")
	public List<Transaction> getTransactionsByAccountId(@PathVariable Integer id) {
		return transactionService.getTransactionsByAccountId(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/transactions")
	public TransactionResponse addTransaction(@RequestBody TransactionRequest transaction) {
		return transactionService.addTransaction(transaction);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/transactions/{id}")
	public void deleteTransaction(@PathVariable Integer id) {
		transactionService.deleteTransaction(id); 
	}
}
