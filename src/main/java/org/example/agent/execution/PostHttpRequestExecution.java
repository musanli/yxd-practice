package org.example.agent.execution;

import org.example.agent.execution.helper.RestTemplateExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PostHttpRequestExecution extends GeneralHttpRequestExecution {
    public PostHttpRequestExecution(RestTemplateExecutor executor) {
        super(executor);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }
}
