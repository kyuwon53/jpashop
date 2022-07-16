package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 회원 정보 저장, 조회, 목록 조회
 */
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 회원 정보를 DB에 저장
     *
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 회원을 id로 DB에서 조회
     *
     * @param id 회원 아이디
     * @return 조회된 회원
     */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * 회원 전체 목록 조회
     *
     * @return 회원 목록
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * 이름으로 회원 목록 조회
     *
     * @param name 회원 이름
     * @return 이름에 해당하는 회원 목록
     */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
