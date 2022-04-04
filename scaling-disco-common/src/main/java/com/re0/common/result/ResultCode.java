package com.re0.common.result;


/**
 * 枚举了一些常用API操作码
 *
 * @author fangxi
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAILURE(501, "业务异常"),
    UN_AUTHORIZED(401, "请求未授权"),
    NOT_FOUND(404, "404 没找到请求"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    REQ_REJECT(403, "请求被拒绝"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    PARAM_MISS(400, "缺少必要的请求参数"),
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
