package co.edu.icesi.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;

@Controller
@RequestMapping("/unitmeasures")
public class UnitmeasureController {

	UnitmeasureRepositoryInterface unitmeasureRepository;

	public UnitmeasureController(UnitmeasureRepositoryInterface unitmeasureRepository) {
		this.unitmeasureRepository = unitmeasureRepository;
	}

	@GetMapping("{id}")
	public String getProductSubcategory(Model model, @PathVariable("id") long id) {
	
		Optional<Unitmeasure> unitmeasure = unitmeasureRepository.findById(id);
		
		if(unitmeasure.isEmpty()) {
			throw new IllegalArgumentException("Invalid id:" + id);

		}
		model.addAttribute("unitmeasure", unitmeasure.get());
		
		return "unitmeasures/information";
	}

}
