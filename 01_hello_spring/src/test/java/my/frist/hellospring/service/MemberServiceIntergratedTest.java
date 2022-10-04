package my.frist.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import my.frist.hellospring.domain.Member;
import my.frist.hellospring.repository.MemberRepository;


@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행하는 것을 통합 테스트라고 함
@Transactional // 테스트 후 관련 데이터를 db에서 롤백해줌
public class MemberServiceIntergratedTest {
    
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void testJoin() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long saveMember = memberService.join(member);

        // then
        Member findMember = memberService.findMemberById(saveMember).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    
    @Test
    void checkNameDuplication() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 생성된 이름입니다.");
    }
}
