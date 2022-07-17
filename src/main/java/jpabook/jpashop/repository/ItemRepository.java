package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    /**
     * 상품 저장
     * 상품이 DB에 없다면 즉, 새로 생성한 아이템이라면 신규 등록
     * 상품이 있을 경우, merge => update라고 생각
     *
     * @param item
     * @return
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    /**
     * id에 해당하는 상품 조회
     *
     * @param id
     * @return 조회된 상품
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /**
     * 상품 목록 조회
     *
     * @return 상품 목록
     */
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
