package my.first.hellospring.repository;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import my.first.hellospring.domain.Member;

// interface가 interface를 받을 때는 extends, interface는 다중 상속 가능
// interface SpringDataJpaMemberRepository extends JpaRepository를 통해 spring data jpa가 자동으로 구현체를 만들고 spring bean에 등록해줌
// 이후 config에서 injection 등을 하고 service에 DI해주면 됨
@Primary // 해당 타입의 bean이 여러개인 경우 @Primary 등으로 우선순위를 주어야함.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    
    @Override
    Optional<Member> findByName(String name);
}
