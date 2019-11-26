package grocery;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {
	List<Cart> findByUser(User id);
	List<Cart> findByUserAndStatus(User id, Boolean status);
}
