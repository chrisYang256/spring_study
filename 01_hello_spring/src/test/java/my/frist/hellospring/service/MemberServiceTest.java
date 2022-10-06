package my.frist.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import my.first.hellospring.domain.Member;
import my.first.hellospring.repository.MemoryMemberRepository;
import my.first.hellospring.service.MemberService;


// 자바 코드만 테스트 하는 것을 단위 테스트라고 함(통합 테스트보다 상대적으로 중요 )
public class MemberServiceTest {
    
    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // memberService와 MemoryMemberRepository를 공유하기 위해 코드 변경
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void AfterEach() {
        memberRepository.clearStore();
    }

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

        // try {
        //     memberService.join(member2);
        //     // fail();
        // } catch (IllegalStateException e) {
        //     Assertions.assertThat(e.getMessage()).isEqualTo("이미 생성된 이름입니다.");
        //     // Assertions.assertThat(e.getMessage()).isEqualTo("이미 생성된 이름입니다.123");
        // }

        // try/catch문이 아닌 다음 테스트 코드로 점검 가능
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 생성된 이름입니다.");
        
        // assertThrows(NullPointerException.class, () -> memberService.join(member2));
    }

    @Test
    void testFindMemberById() {

    }

    @Test
    void testGetAllMember() {

    }
}
