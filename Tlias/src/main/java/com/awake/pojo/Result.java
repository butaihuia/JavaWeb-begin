package com.awake.pojo;
//result 结果类
import lombok.Data;
import org.springframework.stereotype.Component;
@Data
public class Result {
    private Integer code;//1成功,0失败
    private String message;//错误信息
    private Object data;// 数据
    public static Result success() {//无参成功结果
        Result result = new Result();
        result.code=1;
        result.message="操作成功";
        return result;
    }

    public static Result success(Object data) {//有参传递数据结果
        Result result = success();
        result.data=data;
        return result;
    }

    public static Result error(String message) {//传递错误信息结果
        Result result = new Result();
        result.code=0;
        result.message=message;
        return result;
    }

}
