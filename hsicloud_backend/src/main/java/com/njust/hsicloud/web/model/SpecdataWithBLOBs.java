package com.njust.hsicloud.web.model;

public class SpecdataWithBLOBs extends Specdata {
    private String wavelength;

    private String relectivity;

    public String getWavelength() {
        return wavelength;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength == null ? null : wavelength.trim();
    }

    public String getRelectivity() {
        return relectivity;
    }

    public void setRelectivity(String relectivity) {
        this.relectivity = relectivity == null ? null : relectivity.trim();
    }
}