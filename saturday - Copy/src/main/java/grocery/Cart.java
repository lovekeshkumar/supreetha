package grocery;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity(name = "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartid;

	private Integer uqty;
	private Float total;
	private boolean status;

	@Transient
	private Integer fk;

	@Transient
	private Integer pfk;

	@Transient
	private Integer ofk;

	public Integer getOfk() {
		return ofk;
	}

	public void setOfk(Integer ofk) {
		this.ofk = ofk;
	}

	@ManyToOne
	private User user;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Order order;

	public Cart() {

	}

	public Integer getCartid() {
		return cartid;
	}

	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}

	public Integer getUqty() {
		return uqty;
	}

	public void setUqty(Integer uqty) {
		this.uqty = uqty;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getFk() {
		return fk;
	}

	public void setFk(Integer fk) {
		this.fk = fk;
	}

	public Integer getPfk() {
		return pfk;
	}

	public void setPfk(Integer pfk) {
		this.pfk = pfk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Cart(Integer cartid, Integer uqty, Float total, boolean status, Integer fk, Integer pfk) {
		super();
		this.cartid = cartid;
		this.uqty = uqty;
		this.total = total;
		this.status = status;
		this.fk = fk;
		this.pfk = pfk;
	}

	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", uqty=" + uqty + ", total=" + total + ", status=" + status + ", fk=" + fk
				+ ", pfk=" + pfk + "]";
	}

}
