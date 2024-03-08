package org.example.agent.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;

public class AgentSimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory implements ClientHttpRequestFactory {
    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return super.createRequest(uri, httpMethod);
    }
}
