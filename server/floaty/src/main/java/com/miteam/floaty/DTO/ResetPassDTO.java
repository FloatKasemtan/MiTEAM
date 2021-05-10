package com.miteam.floaty.DTO;

public class ResetPassDTO {
    private String verify;
    private int id_reset;
    private String new_pass;

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public int getId_reset() {
        return id_reset;
    }

    public void setId_reset(int id_reset) {
        this.id_reset = id_reset;
    }

    public String getNew_pass() {
        return new_pass;
    }

    public void setNew_pass(String new_pass) {
        this.new_pass = new_pass;
    }
}
