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
import javax.persistence.Transient;

@Entity(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer oid;

	private Integer total;

	@Transient
	private Integer ufk;

	@Transient
	private Integer cartfk;

	@ManyToOne
	private User user;

	public Order() {

	}

	public Integer getUfk() {
		return ufk;
	}

	public void setUfk(Integer ufk) {
		this.ufk = ufk;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", total=" + total + ", ufk=" + ufk + ", user=" + user + "]";
	}

}