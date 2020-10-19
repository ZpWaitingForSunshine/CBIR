package com.njust.hsicloud.web.model;

public class ImageWithBLOBs extends Image {
    private String bandnames;

    private String wavelength;

    public String getBandnames() {
        return bandnames;
    }

    public void setBandnames(String bandnames) {
        this.bandnames = bandnames == null ? null : bandnames.trim();
    }

    public String getWavelength() {
        return wavelength;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength == null ? null : wavelength.trim();
    }
}