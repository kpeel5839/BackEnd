package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.storeClear();
    }
    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("spring");
        memberService.join(member);
        Member result_Member = memberService.findOne(member.getId()).get();
        Assertions.assertThat(result_Member).isEqualTo(member);
    }
    @Test
    public void 중복_회원_예제(){
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("spring1");
        member2.setName("spring1");

        memberService.join(member1);
        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}