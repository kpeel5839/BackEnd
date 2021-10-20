package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest //spinrgBootTest 하면 그냥 실제로 spring 돌리는 것처럼 test할 수가 있음
@Transactional //transactional 을 서로 겹치지 않게 계속 비워주는 것임
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Commit //commit annotation은 실제로 데이터베이스에 적용되게 하는 것임
    void 회원가입() {
        Member member = new Member();
        member.setName("spring100");

        Long saveId = memberService.join(member);

        Member result_Member = memberService.findOne(saveId).get();
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