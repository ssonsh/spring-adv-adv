package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴
     */
    @Test
    void callbackV1(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비즈니스 로직 1 실행"));
        timeLogTemplate.execute(() -> log.info("비즈니스 로직 2 실행"));
    }
}
