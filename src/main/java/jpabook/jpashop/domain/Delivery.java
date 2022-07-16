package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;
    /**
     * 배송 상태
     * READY : 준비
     * COMP : 배송
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
