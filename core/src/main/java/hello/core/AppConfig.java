package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { // 구체적인 객체를 주입한다

    // @Bean memberService -> new MemoryMemberRepository() 호출
    // @Bean memberOrderService -> new MemoryMemberRepository() 호출

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    } // 생성자 주입

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

    // 역할(인터페이스)에 따른 구현(클래스)이 한 눈에 보이도록 리팩토링
}
