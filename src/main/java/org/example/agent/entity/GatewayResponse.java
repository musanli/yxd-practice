package org.example.agent.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.http.HttpHeaders;

import java.io.Serializable;

/**
 * gateway response
 *
 * @author hantong6
 */
@Data
public class GatewayResponse<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private int returnCode;

    private String returnMsg;

    private String detailMsg = StrUtil.EMPTY;

    private T data;

//    private SimplifyHeaders headers;

    public static <T> GatewayResponse<T> success() {
        GatewayResponse<T> gatewayResponse = new GatewayResponse<>();
        gatewayResponse.setReturnCode(GatewayReturnCode.OK.getCode());
        gatewayResponse.setReturnMsg(GatewayReturnCode.OK.getMsg());
        return gatewayResponse;
    }

    public static <T> GatewayResponse<T> success(T data) {
        GatewayResponse<T> gatewayResponse = success();
        gatewayResponse.setData(data);
        return gatewayResponse;
    }

//    public static <T> GatewayResponse<T> success(T data , HttpHeaders headers) {
//        GatewayResponse<T> gatewayResponse = success();
//        gatewayResponse.setData(data);
//        gatewayResponse.setHeaders(SimplifyHeaders.valueOf(headers));
//
//        return gatewayResponse;
//    }

    public static <T> GatewayResponse<T> fail(GatewayReturnCode gatewayReturnCode) {
        GatewayResponse<T> gatewayResponse = new GatewayResponse<>();
        gatewayResponse.setReturnCode(gatewayReturnCode.getCode());
        gatewayResponse.setReturnMsg(gatewayReturnCode.getMsg());
        return gatewayResponse;
    }

    public static <T> GatewayResponse<T> fail(GatewayReturnCode gatewayReturnCode, String detailMsg) {
        GatewayResponse<T> gatewayResponse = fail(gatewayReturnCode);
        gatewayResponse.setDetailMsg(detailMsg);
        return gatewayResponse;
    }
}
