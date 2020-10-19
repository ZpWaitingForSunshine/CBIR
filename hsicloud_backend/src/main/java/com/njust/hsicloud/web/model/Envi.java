package com.njust.hsicloud.web.model;

public class Envi {
    private Integer id;

    private String filename;

    private Integer samples;

    private Integer rows;

    private Integer bands;

    private Integer headeroffset;

    private String filetype;

    private Integer datatype;

    private String interleave;

    private Integer byteorder;

    private String bandnames;

    private String wavelength;

    private String timeofexposure;

    private Integer imagebin;

    private Integer autoexproix;

    private Integer autoexproiy;

    private Integer autoexproiwidth;

    private Integer autoexproiheight;

    private Float gain;

    private Float exposurecoefficient;

    private Float lctftemperature;

    private String hdfs;

    private String thumbnailurl;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getSamples() {
        return samples;
    }

    public void setSamples(Integer samples) {
        this.samples = samples;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getBands() {
        return bands;
    }

    public void setBands(Integer bands) {
        this.bands = bands;
    }

    public Integer getHeaderoffset() {
        return headeroffset;
    }

    public void setHeaderoffset(Integer headeroffset) {
        this.headeroffset = headeroffset;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public Integer getDatatype() {
        return datatype;
    }

    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
    }

    public String getInterleave() {
        return interleave;
    }

    public void setInterleave(String interleave) {
        this.interleave = interleave == null ? null : interleave.trim();
    }

    public Integer getByteorder() {
        return byteorder;
    }

    public void setByteorder(Integer byteorder) {
        this.byteorder = byteorder;
    }

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

    public String getTimeofexposure() {
        return timeofexposure;
    }

    public void setTimeofexposure(String timeofexposure) {
        this.timeofexposure = timeofexposure == null ? null : timeofexposure.trim();
    }

    public Integer getImagebin() {
        return imagebin;
    }

    public void setImagebin(Integer imagebin) {
        this.imagebin = imagebin;
    }

    public Integer getAutoexproix() {
        return autoexproix;
    }

    public void setAutoexproix(Integer autoexproix) {
        this.autoexproix = autoexproix;
    }

    public Integer getAutoexproiy() {
        return autoexproiy;
    }

    public void setAutoexproiy(Integer autoexproiy) {
        this.autoexproiy = autoexproiy;
    }

    public Integer getAutoexproiwidth() {
        return autoexproiwidth;
    }

    public void setAutoexproiwidth(Integer autoexproiwidth) {
        this.autoexproiwidth = autoexproiwidth;
    }

    public Integer getAutoexproiheight() {
        return autoexproiheight;
    }

    public void setAutoexproiheight(Integer autoexproiheight) {
        this.autoexproiheight = autoexproiheight;
    }

    public Float getGain() {
        return gain;
    }

    public void setGain(Float gain) {
        this.gain = gain;
    }

    public Float getExposurecoefficient() {
        return exposurecoefficient;
    }

    public void setExposurecoefficient(Float exposurecoefficient) {
        this.exposurecoefficient = exposurecoefficient;
    }

    public Float getLctftemperature() {
        return lctftemperature;
    }

    public void setLctftemperature(Float lctftemperature) {
        this.lctftemperature = lctftemperature;
    }

    public String getHdfs() {
        return hdfs;
    }

    public void setHdfs(String hdfs) {
        this.hdfs = hdfs == null ? null : hdfs.trim();
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl == null ? null : thumbnailurl.trim();
    }
}