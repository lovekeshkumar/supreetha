package grocery;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
	List<Order> findByUser(User id);

	@Query(value = "select carts.cartid, carts.total, carts.uqty, products.image_url, products.pname from carts , products where products.pid = carts.product_pid AND carts.user_id = ?1 AND carts.order_oid=?2", nativeQuery = true)
	List<?> getProductOrderedDetails(Integer user_id, Integer order_oid);
}
