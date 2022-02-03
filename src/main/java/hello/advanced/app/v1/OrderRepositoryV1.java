package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepositoryV1.request()");


            // 저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }

            sleep(1000);

            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            // 예외를 꼭 다시 던져주어야 한다.
            // trace.exception 기능으로 예외를 먹어버리고 끝나면
            // 비즈니스 흐름이 바뀌어버리는 것이다.
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
