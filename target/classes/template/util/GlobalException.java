package ${package_util};

import java.text.MessageFormat;

/**
 * 自定义异常类
 *
 * @author gaia-developer
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer rspCode;

    public GlobalException(Integer rspCode, String message) {
        super(message);
        this.rspCode = rspCode;
    }

    public Integer getCode() {
        return rspCode;
    }

    public void setCode(Integer code) {
        this.rspCode = code;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.rspCode, this.getMessage());
    }

}
