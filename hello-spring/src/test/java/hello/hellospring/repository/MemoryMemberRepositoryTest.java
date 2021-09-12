package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.*;
//보통 test케이스를 먼저 만들고 구현을 한다고 한다. 그게 tdd
public class MemoryMemberRepositoryTest { //class 로 memorymember repository test 클래스 만들고 거기서 실행한다.
    MemoryMemberRepository repository = new MemoryMemberRepository(); //memoryMemberRepository repository 로 만들어서 객체 선언해서 테스트 케이스를 할 수 있도록 한다.
    //그니까 저장소를 만든 것임 객체로
    @AfterEach //test를 할때에는 절대로 상호 의존적으로 만들면 안되기 때문에 항상 AfterEach 즉 테스트 한 클래스마당 실행할 때마다 store를 다시 클리어시킨다.
    //interface 에다가도 clearStore() 함수를 새로 만들고 store.clear(); 를 해주어서 store.clear 기능을 구현을 해놓는다.
    public void afterEach(){
        repository.clearStore(); //이게 없으면 어떠한 test가 먼저 돌아가는지 모르기 때문에 clearStore를 해주어서 각 테스트 케이스가 끝날때마다 비워주어 어떠한 케이스가 먼저 실행 되더라도 test결과가 변해서는 안되게 설정한다.
    }
    @Test //이렇게 test api import 하면 method 부르면 테스트 가능
    public void save() { //save 성능 테스트
        Member member = new Member(); //객체 선언한 뒤
        member.setName("spring"); //이 객체 이름 설정
    //options key + enter 로 import 할 수 있다는 것도 엄청난 꿀팁인 듯
        repository.save(member); //이 객체 리포지토리에 세이브
        repository.findById(member.getId()); //id 찾기 findById 랑 save를 같이 실험한 것

        Member result = repository.findById(member.getId()).get();//이렇게 member 객체로 result 만들어서 저장소에 있는 맴버들 아이디 겟 하기
        assertThat(member).isEqualTo(result); //비교하는 값이 동일한지 확인하는 함수 확인하는 assertThat(member) result 랑 같은지 같으면 잘 작동하는 것
    }
    @Test
    public void findByName(){ //findByName 성능 테스트
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); //member1 을 repository 에다가 저장
        
        Member member2 = new Member(); //member2 도 회원가입
        member2.setName("spring2");
        repository.save(member2);
        Member result = repository.findByName("spring2").get(); //이것은 이제 findByName으로 찾는 것
       assertThat(member2).isEqualTo(result);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //repository 에 두개 저장하고 List<Member> 로 받아서 findAll() 기능 확인하기

        List<Member> result = repository.findAll(); //List<Member> 로 리포지토리 즉 테스트 케이스로 findAll()을 불러서 store 에 저장되어 있는 것들을 가져오기

        assertThat(result.size()).isEqualTo(2); //저장한 개수 대로 결과가 나오는 지 확인 3으로 하면 당연히 틀림
    }
}
