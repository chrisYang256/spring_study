package my.frist.hellospring.service;

import java.util.List;
import java.util.Optional;

import my.frist.hellospring.domain.Member;
import my.frist.hellospring.repository.MemberRepository;
import my.frist.hellospring.repository.MemoryMemberRepository;

public class MemberService {
    
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberServiceTest와 MemoryMemberRepository를 공통으로 사용할 수 있도록 위 코드를 변경
    private final MemberRepository memberRepository;
    
    // 이처럼 memberRepository를 외부(현재 MemberServiceTest)에서 넣어 주는 방식을 DI(Defendency Injection)이라고 함.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateDupicateMember(Member member) {
        /** 
        Optional<Member> result = memberRepository.findByName(member.getName()); ... 
        result.ifPresent(m -> { ... 
        이것을 줄일 수 있음.
        */
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 생성된 이름입니다.");
            }
        );
    }

    // 회원가입
    public Long join(Member member) {
        validateDupicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    // 전체 회원 조회
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    // 아이디로 회원 조회
    public Optional<Member> findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
