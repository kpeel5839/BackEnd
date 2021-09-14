package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //private static 변수로 선언한다음에 Map 형태로 선언해줌 그리고 거기다가는 hashMap을 해줌 이것은 당연히 store를 만들 떄 당연시 되는 것인듯
    //그렇기 때문에 외워야하는 부분일 듯
    private static long sequence = 0L; //유저 개수
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member); //저장소에 member id 집어 넣음
        return member; //이 member 집어 넣었다는 것을 보여줌
    }


    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null 이 반환될 가능성이 있으면 optional로 감싸서 반환
    } //store에 있는 id 반환 당연히 null 가능성 있어서 optional 로 감싸는 것 null 보다 optional 로 감싸는게 나음
//findById 는 그냥 바로 store에서 찾아도 되고 아마 findByName은 String 이라서 루프를 돌려서 찾아야 되는 듯
    @Override
    public Optional<Member> findByName(String name) {
       return  store.values().stream()
                .filter(member -> member.getName().equals(name)) //내가 찾는 name 이랑 같은 string 을 store에서 찾음
                .findAny(); // 루프를 돌면서 store에 있는 것들을 찾음
    }
//쨋든 save, findByName, findById , findAll 모두는 store에서 찾아서 반환하는 것
    //@Test 를 하여서 import 하고 MemoryMemberRepository repository 해서 이 repository 객체로 save 도 하고 findall ... 하면서 test 가능
    //test는 Assertions.assertThat(result).isEqualsTo(member1); Assertions.assertThat(result.size()).isEqualsTo(2); 이런 것들로 확인 가능 함
    //꼭MemberRepository 에서 interface 생성할떄 clearStore() 도 생성하고 store.clear()로 꼭 비워주는 행위를 해주어야 테스트케이스가 상호의존적으로 돌아가지 않는다.
    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values()); //arraylist에서 그냥 다 반환함 store에 있는 value를 다
    }

    public void clearStore(){
        store.clear();
    }
}
