package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Controller + ResponseBody = RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<Member> showMembers() {
        return memberService.getMemberAll();
    }

    @ResponseBody
    @PostMapping("/members")
    public Member saveMember(@RequestBody Member member) {
        return memberService.insertMember(member);
    }

    // GET /members/{id} 특정 id 해당하는 값 가져오기 // findById 이용
    @ResponseBody
    @GetMapping("/members/{id}")
    public Member selectMemberById(@PathVariable Long id) {
        return memberService.selectMemberById(id);
    }

    // DELETE /students/{id} deleteById 이용
    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteStudent(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "삭제 성공";
    }

    @GetMapping("/hi")
    public String htmlPage() {
        return "hi";
    }
}
