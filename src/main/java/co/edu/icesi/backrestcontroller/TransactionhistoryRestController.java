package co.edu.icesi.backrestcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dao.TransactionhistoryDAO;
import co.edu.icesi.model.Transactionhistory;

@RestController
@RequestMapping("/api/transaction-histories")
public class TransactionhistoryRestController {

	@Autowired
	private TransactionhistoryDAO transactionhistoryDAO;

	@GetMapping
	public Iterable<Transactionhistory> getTransactionhistories() {
		return transactionhistoryDAO.findAll();
	}

	@GetMapping("/{transactionid}")
	public Transactionhistory transactionhistoryFindById(@PathVariable("transactionid") Integer transactionid) {
		return transactionhistoryDAO.findById(transactionid);
	}

	@PostMapping
	public void saveTransactionhistory(@RequestBody Transactionhistory transactionhistory) {

		transactionhistoryDAO.save(transactionhistory);
	}
	@PutMapping
	public void updateTransactionhistory(Transactionhistory th) {
		transactionhistoryDAO.update(th);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		transactionhistoryDAO.delete(transactionhistoryDAO.findById(id));
	}
	
	@GetMapping("/search/findByReferenceOrder")
	public List<Transactionhistory> findByReferenceOrderId(Integer referencerorder){
		return transactionhistoryDAO.findByReferenceOrderId(referencerorder);
	}
	
	
}
