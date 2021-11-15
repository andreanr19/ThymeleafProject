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

import co.edu.icesi.model.Add;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.services.BusinessEntityService;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductVendorService;
import co.edu.icesi.services.UnitMeasureService;
import co.edu.icesi.services.VendorService;

@Controller
@RequestMapping("product-vendors")
public class ProductVendorController {

	private ProductService productService;
	private UnitMeasureService unitmeasureService;
	private BusinessEntityService businessentityService;
	private VendorService vendorService;
	private ProductVendorService productvendorService;

	@Autowired
	public ProductVendorController(ProductService productService, UnitMeasureService unitmeasureService,
			BusinessEntityService businessentityService, VendorService vendorService,
			ProductVendorService productvendorService) {
		this.productService = productService;
		this.unitmeasureService = unitmeasureService;
		this.businessentityService = businessentityService;
		this.vendorService = vendorService;
		this.productvendorService = productvendorService;
	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", productvendorService.findAll());
		return "/product-vendors/index";
	}

	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("productvendor", productvendorService.findById(id).get());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("vendors", vendorService.findAll());
		model.addAttribute("unitmeasures", unitmeasureService.findAll());
		return "product-vendors/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(@ModelAttribute("productvendor") @Validated(Add.class) Productvendor productvendor,
			BindingResult result, Model model, @PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {

			if (result.hasErrors()) {
				model.addAttribute("products", productService.findAll());
				model.addAttribute("vendors", vendorService.findAll());
				model.addAttribute("unitmeasures", unitmeasureService.findAll());

				return "product-vendors/edit";
			}
			System.out.println(productvendor.getVendor());
			System.out.println(productvendor.getProductid());
			productvendor.setProductid(productvendor.getProductid());
			productvendor.setVendor(productvendor.getVendor());

			productvendorService.edit(productvendor, productvendor.getUnitmeasurecode(), productvendor.getProductid(),
					productvendor.getVendor().getBusinessentityid());

		}
		return "redirect:/product-vendors";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		model.addAttribute("productvendor", new Productvendor());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("vendors", vendorService.findAll());
		model.addAttribute("unitmeasures", unitmeasureService.findAll());
		return "product-vendors/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(@ModelAttribute("productvendor") @Validated(Add.class) Productvendor productvendor,
			BindingResult result, @RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancel")) {
			if(!(productvendor.getMaxorderqty()>productvendor.getMinorderqty())) {
				result.addError(new ObjectError("sellstartdate", "Sell start date should be before sell end date"));
			}
			if (result.hasErrors()) {
				model.addAttribute("products", productService.findAll());
				model.addAttribute("vendors", vendorService.findAll());
				model.addAttribute("unitmeasures", unitmeasureService.findAll());
				return "product-vendors/add";
			}
			productvendor.setProductid(productvendor.getProductid());
			productvendor.setVendor(productvendor.getVendor());
			productvendorService.save(productvendor, productvendor.getUnitmeasurecode(), productvendor.getProductid(),
					productvendor.getVendor().getBusinessentityid());
		}
		return "redirect:/product-vendors";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(@PathVariable("id") Integer id, Model model) {
		Productvendor productvendor = productvendorService.findById(id).get();
		productvendorService.delete(productvendor);
		model.addAttribute("productvendors", productvendorService.findAll());
		return "product-vendors/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model, @PathVariable("id") Integer id) {
		Productvendor productvendor = productvendorService.findById(id).get();

		model.addAttribute("productvendor", productvendor);

		return "product-vendors/information";
	}
}
