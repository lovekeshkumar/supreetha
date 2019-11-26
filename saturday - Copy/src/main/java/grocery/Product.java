package grocery;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pid;

	@Transient
	private Integer cfk;

	@Column(unique = true)
	private String pname;

	private Integer pqty;
	private String unit;
	private Float price;
	private String imageUrl;
	@Transient
	private Integer uqty;
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cart> cart;
	

	public Product() {

	}

	
	public Integer getUqty() {
		return uqty;
	}

	public void setUqty(Integer uqty) {
		this.uqty = uqty;
	}

	public Product(Integer pid, Integer cfk, String pname, Integer pqty, String unit, Float price, Category category) {
		super();
		this.pid = pid;
		this.cfk = cfk;
		this.pname = pname;
		this.pqty = pqty;
		this.unit = unit;
		this.price = price;
		this.category = category;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCfk() {
		return cfk;
	}

	public void setCfk(Integer cfk) {
		this.cfk = cfk;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getPqty() {
		return pqty;
	}

	public void setPqty(Integer pqty) {
		this.pqty = pqty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", cfk=" + cfk + ", pname=" + pname + ", pqty=" + pqty + ", unit=" + unit
				+ ", price=" + price + ", category=" + category + "]";
	}

}
