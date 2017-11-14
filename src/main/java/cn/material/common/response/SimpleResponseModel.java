package cn.material.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import cn.material.common.constants.MessageConstants;

/**
 * 响应ajax的数据载体的实现
 * 响应单条数据时使用该类
 *
 *
 * Created by Mr.wang on 2016/11/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)	//值为null的属性不参与序列化
public class  SimpleResponseModel<T> implements ResponseModel<T> {

    /**
     *  响应状态
     */
    private int status ;

    /**
     * 响应信息
     */
    private String message ;

    /**
     * 响应数据载体，单条
     */
    private T data ;

    public SimpleResponseModel(){}

    public SimpleResponseModel(T data) {
        this.data = data ;
    }

    public SimpleResponseModel(int status, String message, T data) {

        this.setStatus(status);
        this.setMessage(message) ;
        this.setData(data) ;
    }


    
    public int getStatus() {
        return status ;
    }

    
    public SimpleResponseModel setStatus(int status) {
        this.status = status ;
        return this ;
    }

    
    public String getMessage() {
        return this.message ;
    }

    
    public SimpleResponseModel setMessage(String message) {
          this.message = message ;
          return this ;
    }

    
    public T getData() {
        return this.data ;
    }

    
    public SimpleResponseModel setData(T data) {
        this.data = data ;
        return this ;
    }

    
    public ResponseModel error() {

        status = MessageConstants.OPERATION_ERROR_CODE ;
        message = MessageConstants.OPERATION_ERROR_MSG ;
        return this ;
    }

    
    public SimpleResponseModel error(String message) {

        status = MessageConstants.OPERATION_ERROR_CODE ;
        this.message = message ;
        return this;
    }

    
    public SimpleResponseModel success() {

        this.status = MessageConstants.OPERATION_SUCCESS_CODE ;
        this.message = MessageConstants.OPERATION_SUCCESS_MSG ;
        return this ;
    }

    
    public SimpleResponseModel success(String message) {

        this.status = MessageConstants.OPERATION_SUCCESS_CODE ;
        this.message = message ;
        return this;
    }
}
