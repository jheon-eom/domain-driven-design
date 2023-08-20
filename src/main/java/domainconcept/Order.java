package domainconcept;

public class Order {
    private ShippingInfo shippingInfo;

    // 도메인 모델 엔티티는 도메인 기능도 함께 제공
    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        checkShippingInfoChangeable(); // 변경 가능 여부 확인
        setShippingInfo(newShippingInfo);
    }

    private void setShippingInfo(ShippingInfo newShippingInfo) {
        if (newShippingInfo == null) {
            throw new IllegalArgumentException();
        }
        this.shippingInfo = newShippingInfo;
    }

    private void checkShippingInfoChangeable() {
        // this.shippingInfo의 상태 조회
    }
}
