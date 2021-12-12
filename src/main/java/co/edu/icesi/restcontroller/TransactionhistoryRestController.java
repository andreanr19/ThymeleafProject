package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.services.TransactionHistoryService;

@RestController
@RequestMapping("/")
public class TransactionhistoryRestController {

	private TransactionHistoryService transactionhistoryService;

	@Autowired
	public TransactionhistoryRestController(TransactionHistoryService transactionhistoryService) {
		this.transactionhistoryService = transactionhistoryService;
	}
	
	@GetMapping("/transaction-historiesRest/")
	public Iterable<Transactionhistory> getTransactionhistories(){
		return transactionhistoryService.findAll();
	}
	
	@GetMapping("/transaction-historiesRest/{transactionid}")
	public Transactionhistory transactionhistoryFindById(@PathVariable("transactionid") Integer transactionid) {
		return  transactionhistoryService.findById(transactionid);
	}
	
	@PostMapping("/transaction-historiesRest")
	public Transactionhistory saveTransactionhistory(@RequestBody Transactionhistory transactionhistory, 
			@RequestParam(value = "productid", required = true) Integer productid){
				
		transactionhistoryService.saveCorrect(transactionhistory, productid);
		return transactionhistory;
			}
}
