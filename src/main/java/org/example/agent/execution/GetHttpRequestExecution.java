
package org.example.agent.execution;

import org.example.agent.execution.helper.RestTemplateExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class GetHttpRequestExecution extends GeneralHttpRequestExecution {
    public GetHttpRequestExecution(RestTemplateExecutor executor) {
        super(executor);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.GET;
    }
}
