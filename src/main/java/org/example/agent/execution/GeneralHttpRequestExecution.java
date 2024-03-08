package org.example.agent.execution;

import cn.hutool.core.date.TimeInterval;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.agent.execution.helper.RestTemplateExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class GeneralHttpRequestExecution implements HttpRequestExecution<Object, Object> {
    private final RestTemplateExecutor executor;

    @Override
    public ResponseEntity<Object> execute(String url, HttpHeaders httpHeaders, Object body) {
        url = "http://localhost:8080/agent/print";
        long startTime = System.currentTimeMillis();
        log.info("Request to {} start reqHeaders={}, body={}", url, httpHeaders, body);
        ResponseEntity<Object> execute = executor.execute(url, method(), httpHeaders, body);
        log.info("Request to {} end resHeaders={}, took {} ms", url, execute.getHeaders(), System.currentTimeMillis() - startTime);
        return execute;
    }


    @Override
    public HttpMethod method() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
