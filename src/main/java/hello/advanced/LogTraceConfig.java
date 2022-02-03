package hello.advanced;

import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){

        // AS-IS
        // return new FieldLogTrace();

        // TO-BE
        return new ThreadLocalLogTrace();
    }
}
