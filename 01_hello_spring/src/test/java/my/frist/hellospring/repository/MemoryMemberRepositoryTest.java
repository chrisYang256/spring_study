package my.frist.hellospring.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import my.first.hellospring.domain.Member;
import my.first.hellospring.repository.MemoryMemberRepository;

// 클래스 단위 테스트 시 싱행되는 메서드의 순서는 무작위임
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // !! 메서드별로 저장한 데이터가 중복되는 경우 값은 값에 다른 메모리 주소가 저장되므로 오류 발생함
    // 따러서 한개 메서드 단위의 테스트가 끝날 때 마다 데이터를 clear해줘야함
    @AfterEach // 메서드 종료 시 마다 반복적으로 코드를 실행해줌.(Callback)
    public void AfterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, result); // junit
        Assertions.assertThat(member).isEqualTo(result); // assertj
        // Assertions.assertEquals(member, null);
        System.out.println(">> Save result: " + result);
    }
    
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        System.out.println("findAll size of result: " + result.size());

        Assertions.assertThat(result.size()).isEqualTo((2));
    }
}
