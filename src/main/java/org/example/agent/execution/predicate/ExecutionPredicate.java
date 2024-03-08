package org.example.agent.execution.predicate;

import javax.servlet.http.HttpServletRequest;

/**
 * method
 * 时间
 * 签名
 * 地址
 */
public interface ExecutionPredicate {
    /**
     * 验证请求内容是否合法
     * @param request
     * @return
     */
    boolean test(HttpServletRequest request);
}
