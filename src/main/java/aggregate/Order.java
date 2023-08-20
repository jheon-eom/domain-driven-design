package aggregate;

import domainconcept.ShippingInfo;

/**
 * 주문이라는 도메인의 하위 모델 구성
 * 주문
 * 배송지 정보
 * 주문자
 * 주문 목록
 * 총 결제 금액
 *
 * 애그리거트 루트를 통해 간접적으로 애그리거트 내의 다른 엔티티나 밸류 객체에 접근
 * 애그리거트의 내부 구현을 숨겨서 애그리거트 단위로 구현을 캡슐화할 수 있또록 돕는다.
 */
public class Order {
    private ShippingInfo shippingInfo;

    public void changeShippingInfo(ShippingInfo newInfo) {
        checkShippingInfoChangeable();

    }

    private void checkShippingInfoChangeable() {

    }
}
