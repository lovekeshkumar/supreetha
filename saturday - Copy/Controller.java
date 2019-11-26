package com.grocery.project;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grocery.Category;
import grocery.CategoryRepository;
import grocery.Product;
import grocery.ProductRepository;
import grocery.Status;
import grocery.User;
import grocery.UsersRepository;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("grocery")
public class Controller {
	@Autowired
	UsersRepository usersRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	ProductRepository productRepo;

	// Sign In
	@PostMapping("/signin")
	public User signIn(HttpServletRequest req, @RequestBody User user) {
		User temp = new User();
		temp = usersRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword());

		if (temp.getId() > 0 && temp.getPassword().equals(user.getPassword())) {
			HttpSession session = req.getSession(true);
			session.setAttribute("userName", temp.getUserName());
		}
		return temp;
	}

	// Sign Up
	@PostMapping("/signup")
	public User signUp(HttpServletRequest req, @RequestBody User user) {

		User result = null;
		try {
			result = usersRepo.save(user);

			if (result == null)
				return null;

			HttpSession session = req.getSession(true);
			session.setAttribute("userName", result.getUserName());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Sign Out
	@PostMapping("/signout")
	public Status logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (session != null && session.getAttribute("userName") != null) {
			session.invalidate();
			return new Status(true);
		}
		return new Status(false);
	}

	// Check if user has signed in already
	public boolean validate(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("userName") == null)
			return false;
		return true;
	}
	// add category

	@PostMapping("/category/save")
	public Category saveCategory(@RequestBody Category cat) {
		return categoryRepo.save(cat);

	}

	// view category

	@GetMapping("/category/all")
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		Iterable<Category> iterable = categoryRepo.findAll();
		for (Category category : iterable) {
			categories.add(category);
		}
		return categories;
	}

	// add product
	@PostMapping("/product/add")
	public Product saveProduct(@RequestBody Product product) {
		product.setCategory(new Category());
		product.getCategory().setCid(product.getCfk());
		return productRepo.save(product);
	}

	// view all product
	@GetMapping("/product/all")
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		Iterable<Product> iterable = productRepo.findAll();
		for (Product product : iterable) {
			products.add(product);
		}
		return products;
	}
	
	//edit product
	@PutMapping("/product/edit")
	public Product editProduct(@RequestBody Product product) {
		return productRepo.save(product);
		
	}

	
	//delete product by id
	@DeleteMapping("/product/delete/{id}")
	public Status deleteProduct(@PathVariable Integer id) {
		productRepo.deleteById(id);
		return new Status(true);
	}
	
	

	// search product by category

	@GetMapping("/product/all/{id}")
	public List<Product> getProductsByCategoryId(@PathVariable Integer id) {
		Category c = new Category();
		c.setCid(id);
		return productRepo.findByCategory(c);
	}
}
