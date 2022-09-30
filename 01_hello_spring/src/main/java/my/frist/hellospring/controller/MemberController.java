package my.frist.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import my.frist.hellospring.service.MemberService;

// Controller annotation이 있으면 스프링이 실행 시 스프링 컨테이너에서 관리함
@Controller
public class MemberController {
    
    // MemberService를 new로 새로운 객체를 생성하는 경우  
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // 스프링 실행/ 컨테이너 생성 시 특정 컨트롤러의 생성자에 Autowired가 있으면 연결해줌(여기서는 MemberService 연결)
    // !! MemberService에 @Service, Repository에는 @Repository라는 annotation이 있어야 스프링 컨테이너에서 인식할 수 있음
    // MemberController가 생성될 때 spring bean에 등록된 @Service 등을 넣어줌 -> 의존관계 주입(DI, Defendency Injection)
    // @service 등의 annotation을 통해 spring bean에 등록하는 방식을 component scan이라고 함
    // configuration 파일을 만들고 @Bean 등을 통해 코드로 직접 sporing bean에 등록하는 방식도 있음(여기서는 service/BeanConfig.java에 코딩, 이 방식의 경우 Controller를 제외한 service 등에서 @Service, @Autowired 등을 지워야함)(controller는 어차피 스프링이 관리하기 때문에 component scan으로 올라가고 따라서 controller의 @Autowired를 안지워도 됨)
    // 필드 주입 방식(@Autowired private MemberService memberService)은 아래와 같은 생성자 필요없으나, 중간에 바꿀 수 있는 방법이 없음. 
    // setter 주입 방식은 어디서든 호출할 수 있기 때문에 추천되지 않음
    // 생성자 주입 방식은 스프링 컨테이너가 올라가며 세팅되는 시점에 들어가고 끝나기 때문에 의존관계가 실행 중 동적으로 변하는 경우가 없어 권장
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService; 
    }
}
