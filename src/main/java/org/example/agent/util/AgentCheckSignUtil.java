package org.example.agent.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class AgentCheckSignUtil {
//    long timeStamp = System.currentTimeMillis();
//    String sign = DigestUtils.md5Hex("accessKey=" + appAccessKey + "&secretKey=" + appSecretKey + "&timestamp=" + timeStamp);
    //header
//            httpHeaders.add(HttpHeaders.ACCEPT, ContentGenerator.valueOf(filterHeaderContent("application/json")).get());
//            httpHeaders.add("accessKey", ContentGenerator.valueOf(filterHeaderContent(appAccessKey)).get());
//            httpHeaders.add("timestamp", ContentGenerator.valueOf(filterHeaderContent(String.valueOf(timeStamp))).get());
//            httpHeaders.add("sign", ContentGenerator.valueOf(filterHeaderContent(sign)).get());

    /**
     * 请求URL + Head(accessKey,timestamp,sign) + body + 转发地址
     *
     * @param url
     * @param httpEntity
     * @return
     */
    String createSign(String url, HttpEntity httpEntity) {
        return null;
    }
//            String uri = uriComponentsBuilder.build().toUriString();
//        JSONObject paramsHolder = apiInvokeRequest.getParamsHolder();
//        List<Object> paramArray = apiInvokeRequest.getParamArray();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
//
//        HttpEntity<JSONObject> httpEntity = null;
//        HttpEntity<List<Object>> arrayHttpEntity = null;
//
//        if (!paramArray.isEmpty()) {
//            arrayHttpEntity = new HttpEntity<>(paramArray, httpHeaders);
//        } else {
//            httpEntity = new HttpEntity<>(paramsHolder, httpHeaders);
//        }
//        JSONObject body = httpEntity.getBody();
//        ResponseEntity<Object> responseEntity;
//
//        if (apiInvokeRequest.isEnableTrust()) {
//
//            responseEntity = restTemplateNoSsl.exchange(uri, HttpMethod.POST, httpEntity != null ? httpEntity : arrayHttpEntity,
//                    new ParameterizedTypeReference<Object>() {
//                    });
}
