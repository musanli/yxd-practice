package org.example.agent.config;

import com.alibaba.fastjson.JSONObject;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RestTemplateWrapper {
    public RestTemplate wrapper(RestTemplate restTemplate) {
        ClientHttpRequestFactory requestFactory = restTemplate.getRequestFactory();
        restTemplate.setRequestFactory((u, httpMethod) -> {
//            URI uri = new URI(u.getScheme(),
//                    u.getUserInfo(), u.getHost(), u.getPort(),
//                    u.getPath(), u.getQuery(),
//                    u.getFragment());
//            new URI("http://localhost:8081/agent/print")
            log.info("uri: {}, httpMethod: {}", u, httpMethod);
//            todo 特殊处理 uri
            return requestFactory.createRequest(u, httpMethod);
        });
        restTemplate.getInterceptors().add((request, body, execution) -> {
            log.info("request: {}, body: {}", request, body);
//            todo 特殊处理 request
            return execution.execute(request, body);
        });
        return restTemplate;
    }


    public static void main(String[] args) {
        RestTemplateWrapper restTemplateWrapper = new RestTemplateWrapper();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//        restTemplate.getMessageConverters().add(new JsonbHttpMessageConverter());
        restTemplateWrapper.wrapper(restTemplate);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name1", "name1-1");
        jsonObject.put("name2", "name2-2");
        jsonObject.put("name3", "name3-3");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity httpEntity = new HttpEntity<>("name=234", httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8080/agent/print", HttpMethod.GET, httpEntity, String.class);
        log.info(exchange.getBody());
    }
}

