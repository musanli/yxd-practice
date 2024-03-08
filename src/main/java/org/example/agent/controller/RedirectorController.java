package org.example.agent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.agent.service.RedirectorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
@RequestMapping("agent")
@RestController
@RequiredArgsConstructor
public class RedirectorController {

    private final RedirectorService redirectorService;

    @RequestMapping("/redirector/**")
    public Object redirector(@RequestHeader HttpHeaders headers,
                             @RequestBody(required = false) String body,
                             @RequestParam(required = false) MultiValueMap<String, String> params,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        log.info("redirector request: {} {}", request.getMethod(), request.getRequestURI());
        return redirectorService.execute(headers, body, params,request, response);
    }


    @RequestMapping("/print")
    public Object printRequestInfo(@RequestHeader HttpHeaders headers,
                                   @RequestParam(required = false) MultiValueMap<String, String> params,
                                   @RequestBody(required = false) String body,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        log.info("headers: ", headers);
        log.info("body: ", body);
        log.info("getMethod: ", request.getMethod());
        log.info("getRequestURI: ", request.getRequestURI());
        log.info("getRequestURL: ", request.getRequestURL());
        log.info("getContentType: ", request.getContentType());
        log.info("getContextPath: ", request.getContextPath());
        log.info("getParameterMap: ", request.getParameterMap());
        log.info("getQueryString: ", request.getQueryString());


        objectObjectHashMap.put("headers: ", headers);
        objectObjectHashMap.put("body: ", body);
        objectObjectHashMap.put("getMethod: ", request.getMethod());
        objectObjectHashMap.put("getRequestURI: ", request.getRequestURI());
        objectObjectHashMap.put("getRequestURL: ", request.getRequestURL());
        objectObjectHashMap.put("getContentType: ", request.getContentType());
        objectObjectHashMap.put("getContextPath: ", request.getContextPath());
        objectObjectHashMap.put("getParameterMap: ", request.getParameterMap());
        objectObjectHashMap.put("getQueryString: ", request.getQueryString());
        return objectObjectHashMap;

    }
}
