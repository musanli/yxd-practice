package org.example.agent.execution;

import lombok.RequiredArgsConstructor;
import org.example.agent.entity.GatewayResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface HttpRequestExecution<T, R> {

    ResponseEntity<R> execute(String url, HttpHeaders httpHeaders, T body);

    HttpMethod method();

}
