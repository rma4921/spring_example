package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
}
