package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    /**
     * 회원 이름, 주문 상태로 주문 내역 검색
     * 최대 1000건 조회
     *
     * @param orderSerch
     * @return
     */
    public List<Order> findAll(OrderSearch orderSerch) {

        return em.createQuery("select o from Order o join o.member m" +
                        "where o.status = :status" +
                        "and m.name like :name", Order.class)
                .setParameter("status", orderSerch.getOrderStatus())
                .setParameter("name", orderSerch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }
}
