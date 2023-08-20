package repository;

public class CancelOrderService {
    private OrderRepository orderRepository;

    @Transactional // 응용 서비스는 트랜잭션을 관리한다.
    public void cancel(OrderNumber number) {
        Order order = orderRepository.findByNumber(number);
        if (order == null) {
            throw new NoOrderException(number);
        }
        order.cancel();
    }
}
