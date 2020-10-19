package com.njust.hsicloud.web.pojo;

public class ImageSpectral {
    private int imageid;
    private int specid;
    private double abundance;
    private int id;
    private String wavelength;
    private String relectivity;
    private String specname;
    private int speclibid;

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public int getSpecid() {
        return specid;
    }

    public void setSpecid(int specid) {
        this.specid = specid;
    }

    public double getAbundance() {
        return abundance;
    }

    public void setAbundance(double abundance) {
        this.abundance = abundance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWavelength() {
        return wavelength;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength;
    }

    public String getRelectivity() {
        return relectivity;
    }

    public void setRelectivity(String relectivity) {
        this.relectivity = relectivity;
    }

    public String getSpecname() {
        return specname;
    }

    public void setSpecname(String specname) {
        this.specname = specname;
    }

    public int getSpeclibid() {
        return speclibid;
    }

    public void setSpeclibid(int speclibid) {
        this.speclibid = speclibid;
    }


}
