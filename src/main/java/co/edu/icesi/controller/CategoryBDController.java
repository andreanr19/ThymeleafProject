package co.edu.icesi.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.businessdelegate.Businessdelegateimp;
import co.edu.icesi.frontmodel.Productcategory;

@Controller
@RequestMapping("/categories")
public class CategoryBDController {
	
	@Autowired
	private Businessdelegateimp businessDelegate;	
	public CategoryBDController(Businessdelegateimp businessDelegate) {
		this.businessDelegate= businessDelegate;
	}
	
	@GetMapping("")
	public String index(@RequestParam(required = false, value = "id") Integer id, Model model) {
		if(id==null) {
			model.addAttribute("categories", businessDelegate.findAllProductcategories());
			
		}else {
			ArrayList<Productcategory> categories = new ArrayList<>();
			categories.add(businessDelegate.findByProductcategoryId(id));
			model.addAttribute("categories", categories);
			
		}
		return "/categories/index";
	}
	
	
	@GetMapping("/add")
	public String addProductcategory(Model model) {
		model.addAttribute("inst", new Productcategory());
		return "categories/add-category";
	}

	@PostMapping("/add")
	public String saveProductcategory(@ModelAttribute("category") @Validated Productcategory category, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("category", category);
				return "categories/add-category";
			}
			businessDelegate.saveProductcategory(category);
		}
		return "redirect:/categories/";
	}

	@GetMapping("/del/{id}")
	public String deleteProductCategory(@PathVariable("id") Integer id, Model model) {
		Productcategory category = businessDelegate.findByProductcategoryId(id);
		businessDelegate.deleteProductcategory(category);
		return "redirect:/categories/";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Productcategory category = businessDelegate.findByProductcategoryId(id);
		model.addAttribute("category", category);
		return "categories/update-category";
	}
	@PostMapping("/edit/{id}")
	public String updateProductcategory(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("inst") @Validated Productcategory category, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "categories/update-category";
			}
			businessDelegate.editCategory(category);
		}
		return "redirect:/categories/";
	}
	
	@GetMapping("{id}")
	public String getProductcategory(Model model, @PathVariable("id") Integer id) {

		Productcategory productcategory = businessDelegate.findByProductcategoryId(id);

		if (productcategory ==null) {
			throw new IllegalArgumentException("Invalid id:" + id);
		}
		model.addAttribute("productcategory", productcategory);

		return "categories/information";

	}
}








