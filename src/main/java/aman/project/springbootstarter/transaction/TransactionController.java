package aman.project.springbootstarter.transaction;


import java.util.List;

import aman.project.springbootstarter.transaction.model.TransactionRequest;
import aman.project.springbootstarter.transaction.model.TransactionResponse;
import aman.project.springbootstarter.transaction.model.TransactionResponseView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private TransactionService transactionService;


	@RequestMapping(method = RequestMethod.GET, value = "/transactions")
	@ApiOperation(value = "Get transactions",
			notes = "Hit the api to get the list of all the transactions")
	public List<TransactionResponseView> getAllTransactions()
	{
		return transactionService.getAllTransactions();
	}


	@RequestMapping(method = RequestMethod.GET, value = "/transactions/{id}")
	@ApiOperation(value = "Get transaction by id",
			notes = "Provide transaction id to get information about the transaction")
	public TransactionResponseView getTransaction(@PathVariable Integer id)
	{
		return transactionService.getTransaction(id);
	}

	@ApiOperation(value = "Add transaction",
			notes = "Add details of the debit and credit account to make a transaction")
	@RequestMapping(method = RequestMethod.POST, value = "/transactions")
	public TransactionResponse addTransaction(@RequestBody TransactionRequest transaction) {
		return transactionService.addTransaction(transaction);
	}
}
