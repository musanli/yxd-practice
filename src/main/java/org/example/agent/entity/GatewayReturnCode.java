package org.example.agent.entity;

/**
 * gateway return code
 *
 * @author hantong6
 */
public enum GatewayReturnCode {

    OK(1000, "请求成功"),
    PARAMS_IS_NULL(1001, "参数为空"),
    PARAMS_NOT_VALID(1002, "参数无效"),
    PARAMS_NOT_COMPLETE(1003, "参数缺失"),
    NAME_IS_NULL(1004, "名称为空"),
    VERSION_IS_NULL(1005, "版本为空"),
    PROTOCOL_IS_NULL(1006, "协议类型为空"),
    ACTION_IS_NULL(1007, "动作类型为空"),
    ACTION_NOT_VALID(1008, "动作类型无效"),
    API_PATH_IS_NULL(1009, "api路径为空"),
    REQUIRED_PARAMS_IS_NULL(1010, "api参数为空"),
    TIMEOUT_IN_MILLIS_INVALID(1011, "超时设置无效"),
    APP_INFO_NOT_FOUND(1012, "找不到app信息"),
    APP_IS_DISABLED(1013, "app下线状态"),
    API_INFO_NOT_FOUND(1014, "找不到api信息"),
    INVOKE_EXECUTOR_NOT_FOUND(1015, "找不到协议执行器"),
    INVOKE_URL_IS_NULL(1016, "调用url为空"),
    INVOKE_FAIL(1017, "调用失败"),
    CLIENT_NOT_INIT(1018, "客户端未初始化"),
    DELETE_CONTENT_FAIL(1019, "删除备份内容失败"),
    GET_VOLUMES_FAIL(1020, "获取文件卷失败"),
    ADD_CONTENT_FAIL(1021, "添加备份内容失败"),
    EDIT_CONTENT_FAIL(1022, "修改备份内容失败"),
    DELETE_MEDIA_RESOURCE_FAIL(1023, "删除介质资源失败"),

    ADD_CLIENT_CLUSTER(3003,"添加客户端集群成功"),


    SYSTEM_ERROR(1999, "系统异常"),

    INITIATE_TASK(202,"发起任务成功");



    private final int code;
    private final String msg;

    GatewayReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
