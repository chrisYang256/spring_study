package my.first.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 모든 API의 실행시간을 재야 하는 상황을 예로 활용
// AOP 적용 전 의존관계: Controller -> Service
// AOP 적용 후 의존관계: Controller -> 프록시Servise(가짜 Service) -> (joinPoint.proceed()) -> Service
// AOP 적용 후 전체그림: 프록시Ctr -> Ctr -> 프록시Ser -> Ser -> 프록시Rep -> Rep
@Aspect // AOP: Aspect Oriented Programming
@Component //spring bean에 등록해도 되고 annotation을 넣어도 됨
public class TimeTraceAop {
    
    @Around("execution(* my.first.hellospring..*(..))") // 공통관심사항(cross-sutting concern)를 등록해 주는 방법
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println(">> Start: " + joinPoint.toString());

        try {
            return joinPoint.proceed(); // 다음 메서드 진행
        } finally {
            long finish = System.currentTimeMillis();
            long runningTime = finish - start;

            System.out.println(">> END: " + joinPoint.toString() + " " + runningTime + "ms");
        }
    }
}
