package aggregateroot;

import java.util.List;

public class Order {
    private ShippingInfo shippingInfo;
    private OrderState state;
    private Money totalAmounts;
    private List<OrderLine> orderLines;

    private void calculateTotalAmounts() {
        int sum = orderLines.stream()
                .mapToInt(ol -> ol.getPrice() * ol.getQuantity())
                .sum();
        this.totalAmounts = new Money(sum);
    }

    // 애그리거트 루트는 도메인 규칙을 구현한 기능을 제공한다.
    public void changeSippingInfo(ShippingInfo newShippingInfo) {
        verifyNtYetShipped();
        setShippingInfo(newShippingInfo);
    }

    // 애그리거트 외부에서 애그리거트에 속한 객체를 직접 변경해선 안 된다.
    private void setShippingInfo(ShippingInfo newShippingInfo) {
        this.shippingInfo = newShippingInfo;
    }

    private void verifyNtYetShipped() {
        if (this.state != OrderState.PAYMENT_WAITING && this.state != OrderState.PREPARING) {
            throw new IllegalArgumentException("already shipped");
        }
    }
}
