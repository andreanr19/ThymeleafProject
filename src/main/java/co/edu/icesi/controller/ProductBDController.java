package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.businessdelegate.BusinessDelegate;
import co.edu.icesi.businessdelegate.Businessdelegateimp;
import co.edu.icesi.model.Add;
import co.edu.icesi.frontmodel.Product;
import co.edu.icesi.services.ProductCategoryService;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductSubcategoryService;
import co.edu.icesi.services.UnitMeasureService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/products")
@Log4j2
public class ProductBDController {

	@Autowired
	private Businessdelegateimp businessDelegate;

	public ProductBDController(Businessdelegateimp businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("products", businessDelegate.findAllProducts());
		return "/products/index";
	}

	//DONE
	@GetMapping("/edit/{id}")
	public String editProduct(Model model, @PathVariable("id") Integer id) {
		Product product = businessDelegate.findProductById(id);

		model.addAttribute("product", product);
//		model.addAttribute("productcategories", businessDelegate.findAllProductcategories());
		model.addAttribute("productsubcategories", businessDelegate.findAllProductsubcategories());
		model.addAttribute("unitmeasures", businessDelegate.findAllUnitmeasures());
		return "/products/edit";
	}

	//DONE
	@PostMapping("/edit/{id}")
	public String postEditProduct(@ModelAttribute("product") @Validated(Add.class) Product product,
			BindingResult result, Model model, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
			if (!product.getSellstartdate().before(product.getSellenddate())) {
				log.info("ererererererer");
				result.addError(new ObjectError("sellstartdate", "Sell start date should be before sell end date"));
			}
			if (result.hasErrors()) {

//				model.addAttribute("productcategories", businessDelegate.findAllProductcategories());
				model.addAttribute("productsubcategories", businessDelegate.findAllProductsubcategories());
				model.addAttribute("unitmeasures", businessDelegate.findAllUnitmeasures());
				return "products/edit";
			}
			businessDelegate.editProduct(product);
		}
		return "redirect:/products";
	}

	// DONE
	@GetMapping("/add")
	public String addProduct(Model model, @ModelAttribute("product") Product product) {

//		model.addAttribute("productcategories", businessDelegate.findAllProductcategories());
		model.addAttribute("productsubcategories", businessDelegate.findAllProductsubcategories());
		model.addAttribute("unitmeasures", businessDelegate.findAllUnitmeasures());

		return "/products/add";
	}

	// DONE
	@PostMapping("/add")
	public String addProduct(@ModelAttribute("product") @Validated(Add.class) Product product, BindingResult result,
			@RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("product", product);
//				model.addAttribute("productcategories", businessDelegate.findAllProductcategories());
				model.addAttribute("productsubcategories", businessDelegate.findAllProductsubcategories());
				model.addAttribute("unitmeasures", businessDelegate.findAllUnitmeasures());
				return "/products/add";

			}
			businessDelegate.saveProduct(product);
		}
		return "redirect:/products";
	}

	//DONE
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		Product product = businessDelegate.findProductById(id);
		businessDelegate.deleteProduct(product);
		model.addAttribute("products", businessDelegate.findAllProducts());
		return "products/index";
	}

	//DONE
	@GetMapping("/{id}")
	public String getProduct(Model model, @PathVariable("id") Integer id) {
		Product product = businessDelegate.findProductById(id);

		model.addAttribute("product", product);

		return "products/information";
	}

}
