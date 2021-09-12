package hello.hellospring.repository;
import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // 가져왔을 때 null 이면 null 을 반환해도 되지만 optional로 감싸서 반환하는 방법도 사용함
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
