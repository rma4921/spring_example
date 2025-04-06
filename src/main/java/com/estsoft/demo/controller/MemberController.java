package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller // Controller + ResponseBody = RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/hi")
    public String htmlPage() {
        return "hi";
    }

    @ResponseBody
    @GetMapping("/members")
    public List<MemberDTO> showMembers() {
        List<Member> memberAll = memberService.getMemberAll();
        return memberAll.stream().map(MemberDTO::new)
                .toList();
    }

    @ResponseBody
    @PostMapping("/members")
    public Member saveMember(@RequestBody Member member) {
        return memberService.insertMember(member);
    }

    // GET /members/{id} 특정 id 해당하는 값 가져오기 // findById 이용
    @ResponseBody
    @GetMapping("/members/{id}")
    public MemberDTO selectMemberById(@PathVariable Long id) {
        Member member = memberService.selectMemberById(id);
        return new MemberDTO(member);
    }

    // DELETE /students/{id} deleteById 이용
    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteStudent(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "삭제 성공";
    }

    // GET /search/members?name=---
    @ResponseBody
    @GetMapping("/search/members")
    public List<MemberDTO> selectMemberByName(@RequestParam("name") String name) {
        List<Member> memberSearch = memberService.selectMemberByName(name);
        return memberSearch.stream().map(MemberDTO::new).toList();
    }

    @ResponseBody
    @GetMapping("/search/members/like")
    public List<MemberDTO> selectLikeMemberByName(@RequestParam("name") String name) {
        List<Member> memberLike = memberService.selectLikeMemberByName(name);
        return memberLike.stream().map(MemberDTO::new).toList();
    }

    @ResponseBody
    @PutMapping("/members/{id}")
    public MemberDTO updateMember(@PathVariable Long id, @RequestBody Member newMember) {
        Member member = memberService.updateMember(id, newMember.getName());
        return new MemberDTO(member);
    }

}
