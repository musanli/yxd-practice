package org.example.agent.execution.helper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

@Component
public class RestTemplateExecutor {
    private final String AGENT_TRUST = "agent-trust";
    @Resource(name = "serverGatewayRestTemplate")
    private RestTemplate restTemplate;

    @Resource(name = "serverGatewayRestTemplateNoSsl")
    private RestTemplate restTemplateNoSsl;

    public ResponseEntity<Object> execute(String url, HttpMethod method, HttpHeaders httpHeaders, Object body) {
        return getRestTemplate(httpHeaders).exchange(url, method, new HttpEntity<>(body, httpHeaders), new ParameterizedTypeReference<Object>() {
        });
    }

    private RestTemplate getRestTemplate(HttpHeaders httpHeaders) {
        boolean isTrust = Stream.ofNullable(httpHeaders.get(AGENT_TRUST)).flatMap(List::stream).filter(itm -> Boolean.valueOf(itm)).findFirst().isPresent();
        return isTrust ? restTemplateNoSsl : restTemplate;

    }
}
