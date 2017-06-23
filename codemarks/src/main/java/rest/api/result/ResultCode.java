package rest.api.result;

/**
 * Created by 李恒名 on 2017/6/13.
 * <p>
 * 响应码枚举
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    Unauthorized(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    public int code;

     ResultCode(int code) {
        this.code = code;
    }
}
