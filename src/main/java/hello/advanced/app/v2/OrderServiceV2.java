package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderServiceV2.request()");
            orderRepository.save(status.getTraceId(), itemId);
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
