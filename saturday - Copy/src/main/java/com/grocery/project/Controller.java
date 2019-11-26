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

import grocery.Cart;
import grocery.CartRepository;
import grocery.Category;
import grocery.CategoryRepository;
import grocery.Order;
import grocery.OrderRepository;
import grocery.Product;
import grocery.ProductRepository;
import grocery.Status;
import grocery.User;
import grocery.UsersRepository;

@RestController
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("grocery")
public class Controller {
	@Autowired
	UsersRepository usersRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	CartRepository cartRepo;

	@Autowired
	OrderRepository orderRepo;

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
	public Category saveCategory(@RequestBody Category cat,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		return categoryRepo.save(cat);

	}

	// view category

	@GetMapping("/category/all")
	public List<Category> getCategories(HttpServletRequest req) {
		if(!validate(req))
			 return null;
		List<Category> categories = new ArrayList<Category>();
		Iterable<Category> iterable = categoryRepo.findAll();
		for (Category category : iterable) {
			categories.add(category);
		}
		return categories;
	}

	// delete category by id
	@DeleteMapping("/category/delete/{cid}")
	public Status deleCategory(@PathVariable Integer cid,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		categoryRepo.deleteById(cid);
		return new Status(true);
	}

	// add product
	@PostMapping("/products/save")
	public Product saveProduct(@RequestBody Product product,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		product.setCategory(new Category());
		product.getCategory().setCid(product.getCfk());
		return productRepo.save(product);
	}

	// view all product
	@GetMapping("/products/all")
	public List<Product> getProducts(HttpServletRequest req) {
		if(!validate(req))
			 return null;
		List<Product> products = new ArrayList<Product>();
		Iterable<Product> iterable = productRepo.findAll();
		for (Product product : iterable) {
			products.add(product);
		}
		return products;
	}

//	//edit product
//	@PutMapping("/products/edit")
//	public Product editProduct(@RequestBody Product product) {
//		return productRepo.save(product);
//		
//	}

	// delete product by id
	@DeleteMapping("/products/delete/{id}")
	public Status deleteProduct(@PathVariable Integer id,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		productRepo.deleteById(id);
		return new Status(true);
	}

	// search product by category

	@GetMapping("/products/all/{id}")
	public List<Product> getProductsByCategoryId(@PathVariable Integer id,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		Category c = new Category();
		c.setCid(id);
		return productRepo.findByCategory(c);
	}

	// add product to cart
	@PostMapping("/cart/save")
	public Cart saveCart(@RequestBody Cart cart,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		cart.setUser(new User());
		cart.getUser().setId(cart.getFk());
		cart.setProduct(new Product());
		cart.getProduct().setPid(cart.getPfk());
		return cartRepo.save(cart);

	}

//find cart item by user id

	// find orders by user id
	@GetMapping("/cart/all/{id}")
	public List<Cart> getCart(@PathVariable Integer id,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		User c = new User();
		c.setId(id);
//		return cartRepo.findByUser(c);
		return cartRepo.findByUserAndStatus(c, false);
	}

//delete cart item by cartid

	@DeleteMapping("/cart/delete/{cartid}")
	public Status deleCart(@PathVariable Integer cartid,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		cartRepo.deleteById(cartid);
		return new Status(true);
	}

	// add to orders
	@PostMapping("/order/add")
	public Order saveOrder(@RequestBody Order order,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		order.setUser(new User());
		order.getUser().setId(order.getUfk());
		Order temp = orderRepo.save(order);
		return temp;
	}

	@PutMapping("/cart/edit")
	public Cart editStatus(@RequestBody Cart cartItem,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		System.out.println(cartItem);
		cartItem.setOrder(new Order());
		cartItem.getOrder().setOid(cartItem.getOfk());
		return cartRepo.save(cartItem);
	}

	// find orders by user id
	@GetMapping("/order/all/{id}")
	public List<Order> getOrder(@PathVariable Integer id,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		User c = new User();
		c.setId(id);
		return orderRepo.findByUser(c);
	}

	// view all order details
	@GetMapping("/order/details/{id}/{oid}")
	public List<?> getOrderedDetails(@PathVariable Integer id, @PathVariable Integer oid,HttpServletRequest req) {
		if(!validate(req))
			 return null;
		List<?> resp = orderRepo.getProductOrderedDetails(id, oid);
		System.out.println(resp);
		return resp;
	}

}
