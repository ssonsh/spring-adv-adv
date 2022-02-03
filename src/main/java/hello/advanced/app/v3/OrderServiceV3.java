package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderServiceV3.request()");
            orderRepository.save(itemId);
            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            // 예외를 꼭 다시 던져주어야 한다.
            // trace.exception 기능으로 예외를 먹어버리고 끝나면
            // 비즈니스 흐름이 바뀌어버리는 것이다.
            throw e;
        }
    }
}
