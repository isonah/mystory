package com.isonah.usermanagement.config;

/**
 * @author ielksseyer
 */
public class JwtObject {

    private String alg;

    private String value;

    public String getAlg() {
        return alg;
    }

    public JwtObject setAlg(String alg) {
        this.alg = alg;
        return this;
    }

    public String getValue() {
        return value;
    }

    public JwtObject setValue(String value) {
        this.value = value;
        return this;
    }
}
