package infrastructure;

/**
 * 구현의 편리함을 위해 인프라 스트럭처에 대한 의존을 일부 가질 수 있다.
 * XML 설정을 이용하는 것보다 편리하게 테이블 이름을 지정할 수 있다.
 *
 * 구현의 편리함은 DIP가 주는 다른 장점만큼 중요하기 때문에 DIP의 장점을 해치지 않는 범위에서
 * 응용 영역과 도메인 영역에서 구현 기술에 대한 의존을 가져가는 것이 나쁘지 않다.
 */
@Table(name = "TBL_ORDER")
@Entity
public class Order {
}
