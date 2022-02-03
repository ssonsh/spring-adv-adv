package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

     @GetMapping("/v2/request")
    public String request(String itemId){
         TraceStatus status = null;
         try {
             status = trace.begin("OrderControllerV2.request()");
             orderService.orderItem(status.getTraceId(), itemId);
             trace.end(status);
             return "ok";
         }catch(Exception e){
             trace.exception(status, e);
             // 예외를 꼭 다시 던져주어야 한다.
             // trace.exception 기능으로 예외를 먹어버리고 끝나면
             // 비즈니스 흐름이 바뀌어버리는 것이다.
             throw e;
         }
     }
}
