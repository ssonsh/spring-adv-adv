package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderServiceV1.request()");
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
