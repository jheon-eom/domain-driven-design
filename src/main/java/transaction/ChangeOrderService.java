package transaction;


public class ChangeOrderService {
    private OrderRepository orderRepository;

    // 두 개 이상의 애그리거트를 변경해야 한다면
    // 응용 서비스에서 각 애그리거트의 상태를 변경한다.
    @Transactional
    public void changeShippingInfo(OrderId id, ShippingInfo newShippingInfo, boolean useNewShippingAddrAsMemberAddr) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new OrderNotFoundException();
        }
        order.shipTo(newShippingInfo);
        if (useNewShippingAddrAsMemberAddr) {
            Member member = findMember(order.getOrderer());
            member.changeAddress(newShippingInfo.getAddress());
        }
    }

    private Member findMember(Orderer orderer) {
        return new Member();
    }
}
