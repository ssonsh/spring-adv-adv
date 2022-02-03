package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    // 비즈니스 로직 = 핵심 로직
    // 실행 시간을 확인하기 위한 로직 = 부가 기능
    private void logic1() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime = {}", resultTime);
    }

    // 비즈니스 로직 = 핵심 로직
    // 실행 시간을 확인하기 위한 로직 = 부가 기능
    private void logic2() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime = {}", resultTime);
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1(){
        // 전략 생성
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();

        // 컨텍스트 생성 + 전략 주입
        ContextV1 contextV1 = new ContextV1(strategyLogic1);

        // 컨텍스트 실행
        contextV1.execute();


        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV1_2 = new ContextV1(strategyLogic2);
        contextV1_2.execute();
    }

    /**
     * 전략 패턴 사용 - 익명 내부 클래스 이용
     */
    @Test
    void strategyV2(){
        Strategy strategy = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직");
            }
        };

        Strategy strategyLogic1 = () -> log.info("비즈니스 로직 1 실행");
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        Strategy strategyLogic2 = () -> log.info("비즈니스 로직 2 실행");
        ContextV1 contextV1_2 = new ContextV1(strategyLogic2);
        contextV1_2.execute();
    }


    /**
     * 전략 패턴 사용 - 익명 내부 클래스 이용
     *  변수로 만들어 사용하지 않고 바로 사용하기
     */
    @Test
    void startegyV3(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        contextV1.execute();

        ContextV1 contextV1_2 = new ContextV1(() -> log.info("비즈니스 로직 2 실행"));
        contextV1_2.execute();
    }
}
