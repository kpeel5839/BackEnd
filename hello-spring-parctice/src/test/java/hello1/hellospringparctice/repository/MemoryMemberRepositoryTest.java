package hello1.hellospringparctice.repository;

import hello1.hellospringparctice.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //AfterEach 는 하나하나의 메소드를 테스트 한다음에 실행하는 메소드 항상 실행됨
    public void storeClear(){
        repository.clean();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member); //repository.findById 의 return value 가 member 라는 것도 잘 기억하고 있어야 한다
        Member result = repository.findById(member.getId()).get(); //.get() 은 해당객체를 Optional 에서 꺼내는 작업이다.
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result); //존나 재밌따 이거 수업듣고서 해야하는 거네 그래야지 궁금한 것도 해결되고
    }
    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findByName("spring").get();
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        List<Member> memberList = new ArrayList<>();
//        memberList.add(member1);
//        memberList.add(member2);

        List<Member> result = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()) .isEqualTo(2);
    }
}
