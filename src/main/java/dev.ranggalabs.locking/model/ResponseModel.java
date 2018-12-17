package dev.ranggalabs.locking.model;

/**
 * Created by erlangga on 09/12/18.
 */
public class ResponseModel {
    private String code;

    public ResponseModel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
