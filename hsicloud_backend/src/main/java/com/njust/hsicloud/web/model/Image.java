package com.njust.hsicloud.web.model;

public class Image {
    private Integer id;

    private String filename;

    private Short samples;

    private Short rows;

    private Short bands;

    private Integer headeroffset;

    private String filetype;

    private Byte datatype;

    private String interleave;

    private Byte byteorder;

    private String timeofexposure;

    private Integer imagebin;

    private Integer autoexproix;

    private Integer autoexproiy;

    private Integer autoexproiwidth;

    private Integer autoexproiheight;

    private Double gain;

    private Double exposurecoefficient;

    private String lctftemperature;

    private Double wavebegin;

    private Double waveend;

    private String thumbnailurl;

    private String hdfsurl;

    private Double size;

    private Double blocksize;

    private Integer blocknumber;

    private Long createtime;

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

    public Short getSamples() {
        return samples;
    }

    public void setSamples(Short samples) {
        this.samples = samples;
    }

    public Short getRows() {
        return rows;
    }

    public void setRows(Short rows) {
        this.rows = rows;
    }

    public Short getBands() {
        return bands;
    }

    public void setBands(Short bands) {
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

    public Byte getDatatype() {
        return datatype;
    }

    public void setDatatype(Byte datatype) {
        this.datatype = datatype;
    }

    public String getInterleave() {
        return interleave;
    }

    public void setInterleave(String interleave) {
        this.interleave = interleave == null ? null : interleave.trim();
    }

    public Byte getByteorder() {
        return byteorder;
    }

    public void setByteorder(Byte byteorder) {
        this.byteorder = byteorder;
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

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Double getExposurecoefficient() {
        return exposurecoefficient;
    }

    public void setExposurecoefficient(Double exposurecoefficient) {
        this.exposurecoefficient = exposurecoefficient;
    }

    public String getLctftemperature() {
        return lctftemperature;
    }

    public void setLctftemperature(String lctftemperature) {
        this.lctftemperature = lctftemperature == null ? null : lctftemperature.trim();
    }

    public Double getWavebegin() {
        return wavebegin;
    }

    public void setWavebegin(Double wavebegin) {
        this.wavebegin = wavebegin;
    }

    public Double getWaveend() {
        return waveend;
    }

    public void setWaveend(Double waveend) {
        this.waveend = waveend;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl == null ? null : thumbnailurl.trim();
    }

    public String getHdfsurl() {
        return hdfsurl;
    }

    public void setHdfsurl(String hdfsurl) {
        this.hdfsurl = hdfsurl == null ? null : hdfsurl.trim();
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(Double blocksize) {
        this.blocksize = blocksize;
    }

    public Integer getBlocknumber() {
        return blocknumber;
    }

    public void setBlocknumber(Integer blocknumber) {
        this.blocknumber = blocknumber;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}