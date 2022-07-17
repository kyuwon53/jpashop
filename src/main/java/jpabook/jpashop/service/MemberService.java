package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 회원에 관련된 처리를 합니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     *
     * @param member 회원 정보
     * @return 가입된 멤버 아이디
     */
    @Transactional
    public Long join(Member member) {
        validateDuplidateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 검사
     *
     * @param member 검사할 회원
     * @throws IllegalStateException 이미 존재하는 회원일 때
     */
    private void validateDuplidateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 목록 조회
     *
     * @return 회원 전체 목록
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * id에 해당하는 회원 조회
     *
     * @param memberId 조회할 회원 아이디
     * @return 조회된 회원원
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
