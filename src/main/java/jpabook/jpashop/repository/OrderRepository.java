package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    /**
     * 주문 내역 저장
     *
     * @param order
     */
    public void save(Order order) {
        em.persist(order);
    }

    /**
     * id에 해당하는 주문 내역 조회
     *
     * @param id
     * @return
     */
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSerch){}
}
