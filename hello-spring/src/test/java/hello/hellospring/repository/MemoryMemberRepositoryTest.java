package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemberRepository memberRepository = new MemoryMemberRepository();
    
    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");
        memberRepository.save(member);
        Member result_Member = memberRepository.findById(member.getId()).get();
        Assertions.assertThat(result_Member).isEqualTo(member);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("spring1");
        member2.setName("spring2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        Member result_Member  = memberRepository.findByName("spring1").get();
        Assertions.assertThat(result_Member).isEqualTo(member1);
    }

    @Test
    void findAll() {
        Member member1 =new Member();
        Member member2 = new Member();
        member1.setName("spring1");
        member2.setName("spring2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result_List = memberRepository.findAll();
        Assertions.assertThat(result_List.size()).isEqualTo(2);
    }
}