package com.njust.cbir.web.model;

public class Image {
    private Integer imageid;

    private String imagename;

    private Short interleave;

    private Short rows;

    private Integer headeroffset;

    private Short samples;

    private String filetype;

    private Byte datatype;

    private Short bands;

    private Byte byteorder;

    private Double wavebegin;

    private Double waveend;

    private String thumbnailurl;

    private String url;

    private String bandnames;

    private Double size;

    private String wavelength;

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename == null ? null : imagename.trim();
    }

    public Short getInterleave() {
        return interleave;
    }

    public void setInterleave(Short interleave) {
        this.interleave = interleave;
    }

    public Short getRows() {
        return rows;
    }

    public void setRows(Short rows) {
        this.rows = rows;
    }

    public Integer getHeaderoffset() {
        return headeroffset;
    }

    public void setHeaderoffset(Integer headeroffset) {
        this.headeroffset = headeroffset;
    }

    public Short getSamples() {
        return samples;
    }

    public void setSamples(Short samples) {
        this.samples = samples;
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

    public Short getBands() {
        return bands;
    }

    public void setBands(Short bands) {
        this.bands = bands;
    }

    public Byte getByteorder() {
        return byteorder;
    }

    public void setByteorder(Byte byteorder) {
        this.byteorder = byteorder;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getBandnames() {
        return bandnames;
    }

    public void setBandnames(String bandnames) {
        this.bandnames = bandnames == null ? null : bandnames.trim();
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getWavelength() {
        return wavelength;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength == null ? null : wavelength.trim();
    }
}