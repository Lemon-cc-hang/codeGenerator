package ${package_util};

/**
 * 返回实体
 *
 * @author gaia-developer
 */
public class RspData {

    /**
     * success
     */
    public static final int RSP_CODE__OK = 0;

    /**
     * 参数为空/无效
     */
    public static final int RSP_CODE_INVALID_PARAMETER = 102;

    /**
     * 增删改失败
     */
    public static final int RSP_CODE__ERROR = 201;

    /**
     * 查询为空
     */
    public static final int RSP_CODE__SELECT_NULL = 202;

    /**
     * 不允许此操作
     */
    public static final int RSP_CODE__NOT_ALLOW = 203;

    /**
     * 用户名密码错误
     */
    public static final int RSP_CODE__CHECK_ERROR = 301;

    /**
     * 信息已存在
     */
    public static final int RSP_CODE__INFO_EXIST = 302;

    public int rspCode;

    public String rspMsg;

    public Object rspData;

    public RspData() {
    }

    public RspData(int rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }

    public RspData(int rspCode, String rspMsg, Object rspData) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
        this.rspData = rspData;
    }

    public static RspData success() {
        return new RspData(RSP_CODE__OK, "success");
    }

    public static RspData success(Object data) {
        return new RspData(RSP_CODE__OK, "success", data);
    }

    public static RspData success(int code, Object data) {
        return new RspData(code, "success", data);
    }

    public static RspData success(int code, String message, Object data) {
        return new RspData(code, message, data);
    }

    public static RspData error(int errCode) {
        return new RspData(errCode, "error");
    }

    public static RspData noPermissions(String msg) {
        return new RspData(401, msg, null);
    }

    public static RspData errorInfo(int errCode, String msg) {
        return new RspData(errCode, msg, null);
    }

    public static RspData error(int errCode, String msg) {
        return new RspData(errCode, msg, null);
    }

    public static RspData unknown() {
        return new RspData(101, null, null);
    }

    public static RspData invalidParameter() {
        return new RspData(RSP_CODE_INVALID_PARAMETER, null, null);
    }

    public static RspData invalidParameter(String rspMsg) {
        return new RspData(RSP_CODE_INVALID_PARAMETER, rspMsg, null);
    }
}