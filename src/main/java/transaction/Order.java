package transaction;

/**
 * 트랜잭션의 범위는 작을수록 좋다.
 * 한 트랜잭션이 한 개의 테이블을 수정하는 것과 여러 개의 테이블을 수정하는 것은 큰 성능 차이가 발생한다.
 * 한 트랜잭션에는 한 개의 애그리거트만 수정되어야 한다.
 */
public class Order {
    private Orderer orderer;

    public void shipTo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
//        if (useNewShippingAddrAsMemberAddr) {
//            // 다른 애그리거트의 상태를 변경하면 안 됨
//            // 애그리거트가 자신의 책임 범위를 넘어 다른 애그리거트의 상태까지 관리
//            orderer.getMember().changeAddress(newShippingInfo.getAddress());
//        }
    }

    private void setShippingInfo(ShippingInfo newShippingInfo) {

    }

    private void verifyNotYetShipped() {

    }

    public Orderer getOrderer() {

        return new Orderer();
    }
}
