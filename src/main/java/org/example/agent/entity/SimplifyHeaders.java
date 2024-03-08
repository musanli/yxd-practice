//package org.example.agent.entity;
//
//import com.smartcloud.ccb.gatewaycommon.util.ApiUtils;
//import com.smartcloud.ccb.gatewaycommon.util.GatewaySpringContextUtils;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * 开启过滤的HEADERSy记录集合，只有安全列表中定义的HEADER才能读入该对象
// */
//public class SimplifyHeaders extends HttpHeaders {
//    private static final Set<String> SAFE_HEADERS = new HashSet<>();
//
//    private static final Byte[] LOCK = new Byte[0];
//
//    public static SimplifyHeaders valueOf(HttpHeaders originHeaders) {
//        SimplifyHeaders headers = new SimplifyHeaders();
//        headers.resetBy(originHeaders);
//
//        return headers;
//    }
//
//    /**
//     * 使用原数据重置内部记录
//     * @param headers 标准HEADERS集合
//     */
//    public void resetBy(HttpHeaders headers) {
//        if (null != headers) {
//            tryToSyncSafeFilters();
//            for (Entry<String, List<String>> entry : headers.entrySet()) {
//                if (SAFE_HEADERS.contains(entry.getKey())) {
//                    List<String> list = CollectionUtils.emptyIfNull(entry.getValue())
//                            .stream().map(ContentGenerator::valueOf)
//                            .map(ContentGenerator::get)
//                            .collect(Collectors.toList());
//                    // 过滤内容
//                    list.replaceAll(ApiUtils::filterHeaderContent);
//                    super.addAll(entry.getKey(), list);
//                }
//            }
//        }
//    }
//
//    /**
//     * 从配置项同步安全列表
//     */
//    public void tryToSyncSafeFilters() {
//        if (SAFE_HEADERS.size() == 0) {
//            synchronized (LOCK) {
//                if (SAFE_HEADERS.size() == 0) {
//                    Environment environment = GatewaySpringContextUtils.getBean(Environment.class);
//                    String safeValues = environment.getProperty("ccb.gateway.invoker.safe.http.reponse.headers");
//                    safeValues = StringUtils.defaultIfBlank(safeValues, HttpHeaders.LOCATION);
//                    Set<String> safe = Arrays.stream(safeValues.split(",")).filter(StringUtils::isNotBlank)
//                            .collect(Collectors.toSet());
//                    SAFE_HEADERS.clear();
//                    SAFE_HEADERS.addAll(safe);
//                }
//            }
//        }
//    }
//}
