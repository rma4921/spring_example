package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final이나 @NonNull이 붙은 필드만 포함하는 생성자를 자동으로 생성.
public class MemberService {
    private final MemberRepository memberRepository;

    /* lombok의 @RequiredArgsConstructor과 같은 기능
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }

    // member 테이블에 insert 쿼리
    public Member insertMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    // ID로 특정 멤버 조회
    public Member selectMemberById(Long id) {
        Optional<Member> optMember = memberRepository.findById(id);
        return optMember.orElseGet(Member::new);
        // return optMember.orElse(new Member()); // 안티패턴
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> selectMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> selectLikeMemberByName(String name) {
        name = "%" + name + "%";
        return memberRepository.findByNameLike(name);
    }

    @Transactional
    public Member updateMember(Long id, String name) {
        Optional<Member> optMember = memberRepository.findById(id)
                .map(member -> {
                    member.changeName(name);
                    return member;
                });
        return optMember.orElseGet(Member::new);
    }
}
