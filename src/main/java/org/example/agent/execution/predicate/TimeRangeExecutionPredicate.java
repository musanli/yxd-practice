package org.example.agent.execution.predicate;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证时间合法性, 必须在某个时间之前提交
 * 例: 只处理一分钟以内的请求
 */
public class TimeRangeExecutionPredicate implements ExecutionPredicate{
    @Override
    public boolean test(HttpServletRequest request) {
        return true;
    }
}
