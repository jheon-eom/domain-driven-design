package transaction;

public interface OrderRepository {

    Order findById(OrderId id);
}
