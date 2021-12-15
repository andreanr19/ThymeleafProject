package co.edu.icesi.backrestcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.services.UnitMeasureService;

@RestController
@RequestMapping("/api/unitmeasures")
public class UnitmeasureRestController {

	@Autowired
	private UnitMeasureService unitmeasureService;
	
	@GetMapping
	public Iterable<Unitmeasure> getUnitmeasures(){
		return unitmeasureService.findAll();
	}
	
	@PostMapping
	public void saveUnitmeasure(@RequestBody Unitmeasure unitmeasure) {
		unitmeasureService.save(unitmeasure);
	}
	
	@PutMapping
	public void updateUnitmeasure(@RequestBody Unitmeasure unitmeasure) {
		unitmeasureService.editUnitmeasure(unitmeasure);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")long id) {
		unitmeasureService.delete(unitmeasureService.findById(id));
	}
	
}
