package org.example.agent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.agent.execution.HttpRequestExecution;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedirectorService {

    private final List<HttpRequestExecution> executions;

    public Object execute(HttpHeaders headers,
                          String body,
                          MultiValueMap<String, String> params,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        HttpRequestExecution httpRequestExecution = getHttpRequestExecution(request.getMethod());
        Object requestData = MediaType.APPLICATION_FORM_URLENCODED.equals(headers.getContentType()) ? params : body;
        ResponseEntity responseEntity = httpRequestExecution.execute(request.getRequestURI(), headers, requestData);
        responseEntity.getHeaders().forEach((key, value) -> response.addHeader(key, value.get(0)));
        return responseEntity.getBody();
    }


    HttpRequestExecution getHttpRequestExecution(String method) {
        return executions.stream().
                filter(itm -> itm.method().equals(HttpMethod.resolve(method)))
                .findFirst()
                .get();
    }
}
