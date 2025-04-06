package com.estsoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name); // select -- from member where name = ---
    // List<Member> findByIdAndName(int id, String name);
    List<Member> findByNameLike(String name);
}
