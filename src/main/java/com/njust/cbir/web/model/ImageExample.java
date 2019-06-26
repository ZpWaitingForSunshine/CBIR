package com.njust.cbir.web.model;

import java.util.ArrayList;
import java.util.List;

public class ImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andImageidIsNull() {
            addCriterion("ImageID is null");
            return (Criteria) this;
        }

        public Criteria andImageidIsNotNull() {
            addCriterion("ImageID is not null");
            return (Criteria) this;
        }

        public Criteria andImageidEqualTo(Integer value) {
            addCriterion("ImageID =", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidNotEqualTo(Integer value) {
            addCriterion("ImageID <>", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidGreaterThan(Integer value) {
            addCriterion("ImageID >", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ImageID >=", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidLessThan(Integer value) {
            addCriterion("ImageID <", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidLessThanOrEqualTo(Integer value) {
            addCriterion("ImageID <=", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidIn(List<Integer> values) {
            addCriterion("ImageID in", values, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidNotIn(List<Integer> values) {
            addCriterion("ImageID not in", values, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidBetween(Integer value1, Integer value2) {
            addCriterion("ImageID between", value1, value2, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidNotBetween(Integer value1, Integer value2) {
            addCriterion("ImageID not between", value1, value2, "imageid");
            return (Criteria) this;
        }

        public Criteria andImagenameIsNull() {
            addCriterion("ImageName is null");
            return (Criteria) this;
        }

        public Criteria andImagenameIsNotNull() {
            addCriterion("ImageName is not null");
            return (Criteria) this;
        }

        public Criteria andImagenameEqualTo(String value) {
            addCriterion("ImageName =", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameNotEqualTo(String value) {
            addCriterion("ImageName <>", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameGreaterThan(String value) {
            addCriterion("ImageName >", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameGreaterThanOrEqualTo(String value) {
            addCriterion("ImageName >=", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameLessThan(String value) {
            addCriterion("ImageName <", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameLessThanOrEqualTo(String value) {
            addCriterion("ImageName <=", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameLike(String value) {
            addCriterion("ImageName like", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameNotLike(String value) {
            addCriterion("ImageName not like", value, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameIn(List<String> values) {
            addCriterion("ImageName in", values, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameNotIn(List<String> values) {
            addCriterion("ImageName not in", values, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameBetween(String value1, String value2) {
            addCriterion("ImageName between", value1, value2, "imagename");
            return (Criteria) this;
        }

        public Criteria andImagenameNotBetween(String value1, String value2) {
            addCriterion("ImageName not between", value1, value2, "imagename");
            return (Criteria) this;
        }

        public Criteria andInterleaveIsNull() {
            addCriterion("Interleave is null");
            return (Criteria) this;
        }

        public Criteria andInterleaveIsNotNull() {
            addCriterion("Interleave is not null");
            return (Criteria) this;
        }

        public Criteria andInterleaveEqualTo(Short value) {
            addCriterion("Interleave =", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotEqualTo(Short value) {
            addCriterion("Interleave <>", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveGreaterThan(Short value) {
            addCriterion("Interleave >", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveGreaterThanOrEqualTo(Short value) {
            addCriterion("Interleave >=", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLessThan(Short value) {
            addCriterion("Interleave <", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLessThanOrEqualTo(Short value) {
            addCriterion("Interleave <=", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveIn(List<Short> values) {
            addCriterion("Interleave in", values, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotIn(List<Short> values) {
            addCriterion("Interleave not in", values, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveBetween(Short value1, Short value2) {
            addCriterion("Interleave between", value1, value2, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotBetween(Short value1, Short value2) {
            addCriterion("Interleave not between", value1, value2, "interleave");
            return (Criteria) this;
        }

        public Criteria andRowsIsNull() {
            addCriterion("Rows is null");
            return (Criteria) this;
        }

        public Criteria andRowsIsNotNull() {
            addCriterion("Rows is not null");
            return (Criteria) this;
        }

        public Criteria andRowsEqualTo(Short value) {
            addCriterion("Rows =", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotEqualTo(Short value) {
            addCriterion("Rows <>", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsGreaterThan(Short value) {
            addCriterion("Rows >", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsGreaterThanOrEqualTo(Short value) {
            addCriterion("Rows >=", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsLessThan(Short value) {
            addCriterion("Rows <", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsLessThanOrEqualTo(Short value) {
            addCriterion("Rows <=", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsIn(List<Short> values) {
            addCriterion("Rows in", values, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotIn(List<Short> values) {
            addCriterion("Rows not in", values, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsBetween(Short value1, Short value2) {
            addCriterion("Rows between", value1, value2, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotBetween(Short value1, Short value2) {
            addCriterion("Rows not between", value1, value2, "rows");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetIsNull() {
            addCriterion("headeroffset is null");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetIsNotNull() {
            addCriterion("headeroffset is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetEqualTo(Integer value) {
            addCriterion("headeroffset =", value, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetNotEqualTo(Integer value) {
            addCriterion("headeroffset <>", value, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetGreaterThan(Integer value) {
            addCriterion("headeroffset >", value, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetGreaterThanOrEqualTo(Integer value) {
            addCriterion("headeroffset >=", value, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetLessThan(Integer value) {
            addCriterion("headeroffset <", value, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetLessThanOrEqualTo(Integer value) {
            addCriterion("headeroffset <=", value, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetIn(List<Integer> values) {
            addCriterion("headeroffset in", values, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetNotIn(List<Integer> values) {
            addCriterion("headeroffset not in", values, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetBetween(Integer value1, Integer value2) {
            addCriterion("headeroffset between", value1, value2, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andHeaderoffsetNotBetween(Integer value1, Integer value2) {
            addCriterion("headeroffset not between", value1, value2, "headeroffset");
            return (Criteria) this;
        }

        public Criteria andSamplesIsNull() {
            addCriterion("Samples is null");
            return (Criteria) this;
        }

        public Criteria andSamplesIsNotNull() {
            addCriterion("Samples is not null");
            return (Criteria) this;
        }

        public Criteria andSamplesEqualTo(Short value) {
            addCriterion("Samples =", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotEqualTo(Short value) {
            addCriterion("Samples <>", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesGreaterThan(Short value) {
            addCriterion("Samples >", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesGreaterThanOrEqualTo(Short value) {
            addCriterion("Samples >=", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesLessThan(Short value) {
            addCriterion("Samples <", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesLessThanOrEqualTo(Short value) {
            addCriterion("Samples <=", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesIn(List<Short> values) {
            addCriterion("Samples in", values, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotIn(List<Short> values) {
            addCriterion("Samples not in", values, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesBetween(Short value1, Short value2) {
            addCriterion("Samples between", value1, value2, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotBetween(Short value1, Short value2) {
            addCriterion("Samples not between", value1, value2, "samples");
            return (Criteria) this;
        }

        public Criteria andFiletypeIsNull() {
            addCriterion("filetype is null");
            return (Criteria) this;
        }

        public Criteria andFiletypeIsNotNull() {
            addCriterion("filetype is not null");
            return (Criteria) this;
        }

        public Criteria andFiletypeEqualTo(String value) {
            addCriterion("filetype =", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotEqualTo(String value) {
            addCriterion("filetype <>", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeGreaterThan(String value) {
            addCriterion("filetype >", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeGreaterThanOrEqualTo(String value) {
            addCriterion("filetype >=", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLessThan(String value) {
            addCriterion("filetype <", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLessThanOrEqualTo(String value) {
            addCriterion("filetype <=", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLike(String value) {
            addCriterion("filetype like", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotLike(String value) {
            addCriterion("filetype not like", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeIn(List<String> values) {
            addCriterion("filetype in", values, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotIn(List<String> values) {
            addCriterion("filetype not in", values, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeBetween(String value1, String value2) {
            addCriterion("filetype between", value1, value2, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotBetween(String value1, String value2) {
            addCriterion("filetype not between", value1, value2, "filetype");
            return (Criteria) this;
        }

        public Criteria andDatatypeIsNull() {
            addCriterion("DataType is null");
            return (Criteria) this;
        }

        public Criteria andDatatypeIsNotNull() {
            addCriterion("DataType is not null");
            return (Criteria) this;
        }

        public Criteria andDatatypeEqualTo(Byte value) {
            addCriterion("DataType =", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeNotEqualTo(Byte value) {
            addCriterion("DataType <>", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeGreaterThan(Byte value) {
            addCriterion("DataType >", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("DataType >=", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeLessThan(Byte value) {
            addCriterion("DataType <", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeLessThanOrEqualTo(Byte value) {
            addCriterion("DataType <=", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeIn(List<Byte> values) {
            addCriterion("DataType in", values, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeNotIn(List<Byte> values) {
            addCriterion("DataType not in", values, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeBetween(Byte value1, Byte value2) {
            addCriterion("DataType between", value1, value2, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeNotBetween(Byte value1, Byte value2) {
            addCriterion("DataType not between", value1, value2, "datatype");
            return (Criteria) this;
        }

        public Criteria andBandsIsNull() {
            addCriterion("Bands is null");
            return (Criteria) this;
        }

        public Criteria andBandsIsNotNull() {
            addCriterion("Bands is not null");
            return (Criteria) this;
        }

        public Criteria andBandsEqualTo(Short value) {
            addCriterion("Bands =", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotEqualTo(Short value) {
            addCriterion("Bands <>", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsGreaterThan(Short value) {
            addCriterion("Bands >", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsGreaterThanOrEqualTo(Short value) {
            addCriterion("Bands >=", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsLessThan(Short value) {
            addCriterion("Bands <", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsLessThanOrEqualTo(Short value) {
            addCriterion("Bands <=", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsIn(List<Short> values) {
            addCriterion("Bands in", values, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotIn(List<Short> values) {
            addCriterion("Bands not in", values, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsBetween(Short value1, Short value2) {
            addCriterion("Bands between", value1, value2, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotBetween(Short value1, Short value2) {
            addCriterion("Bands not between", value1, value2, "bands");
            return (Criteria) this;
        }

        public Criteria andByteorderIsNull() {
            addCriterion("ByteOrder is null");
            return (Criteria) this;
        }

        public Criteria andByteorderIsNotNull() {
            addCriterion("ByteOrder is not null");
            return (Criteria) this;
        }

        public Criteria andByteorderEqualTo(Byte value) {
            addCriterion("ByteOrder =", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotEqualTo(Byte value) {
            addCriterion("ByteOrder <>", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderGreaterThan(Byte value) {
            addCriterion("ByteOrder >", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderGreaterThanOrEqualTo(Byte value) {
            addCriterion("ByteOrder >=", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderLessThan(Byte value) {
            addCriterion("ByteOrder <", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderLessThanOrEqualTo(Byte value) {
            addCriterion("ByteOrder <=", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderIn(List<Byte> values) {
            addCriterion("ByteOrder in", values, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotIn(List<Byte> values) {
            addCriterion("ByteOrder not in", values, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderBetween(Byte value1, Byte value2) {
            addCriterion("ByteOrder between", value1, value2, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotBetween(Byte value1, Byte value2) {
            addCriterion("ByteOrder not between", value1, value2, "byteorder");
            return (Criteria) this;
        }

        public Criteria andWavebeginIsNull() {
            addCriterion("WaveBegin is null");
            return (Criteria) this;
        }

        public Criteria andWavebeginIsNotNull() {
            addCriterion("WaveBegin is not null");
            return (Criteria) this;
        }

        public Criteria andWavebeginEqualTo(Double value) {
            addCriterion("WaveBegin =", value, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginNotEqualTo(Double value) {
            addCriterion("WaveBegin <>", value, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginGreaterThan(Double value) {
            addCriterion("WaveBegin >", value, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginGreaterThanOrEqualTo(Double value) {
            addCriterion("WaveBegin >=", value, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginLessThan(Double value) {
            addCriterion("WaveBegin <", value, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginLessThanOrEqualTo(Double value) {
            addCriterion("WaveBegin <=", value, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginIn(List<Double> values) {
            addCriterion("WaveBegin in", values, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginNotIn(List<Double> values) {
            addCriterion("WaveBegin not in", values, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginBetween(Double value1, Double value2) {
            addCriterion("WaveBegin between", value1, value2, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWavebeginNotBetween(Double value1, Double value2) {
            addCriterion("WaveBegin not between", value1, value2, "wavebegin");
            return (Criteria) this;
        }

        public Criteria andWaveendIsNull() {
            addCriterion("WaveEnd is null");
            return (Criteria) this;
        }

        public Criteria andWaveendIsNotNull() {
            addCriterion("WaveEnd is not null");
            return (Criteria) this;
        }

        public Criteria andWaveendEqualTo(Double value) {
            addCriterion("WaveEnd =", value, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendNotEqualTo(Double value) {
            addCriterion("WaveEnd <>", value, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendGreaterThan(Double value) {
            addCriterion("WaveEnd >", value, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendGreaterThanOrEqualTo(Double value) {
            addCriterion("WaveEnd >=", value, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendLessThan(Double value) {
            addCriterion("WaveEnd <", value, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendLessThanOrEqualTo(Double value) {
            addCriterion("WaveEnd <=", value, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendIn(List<Double> values) {
            addCriterion("WaveEnd in", values, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendNotIn(List<Double> values) {
            addCriterion("WaveEnd not in", values, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendBetween(Double value1, Double value2) {
            addCriterion("WaveEnd between", value1, value2, "waveend");
            return (Criteria) this;
        }

        public Criteria andWaveendNotBetween(Double value1, Double value2) {
            addCriterion("WaveEnd not between", value1, value2, "waveend");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlIsNull() {
            addCriterion("ThumbnailURL is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlIsNotNull() {
            addCriterion("ThumbnailURL is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlEqualTo(String value) {
            addCriterion("ThumbnailURL =", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotEqualTo(String value) {
            addCriterion("ThumbnailURL <>", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlGreaterThan(String value) {
            addCriterion("ThumbnailURL >", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlGreaterThanOrEqualTo(String value) {
            addCriterion("ThumbnailURL >=", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlLessThan(String value) {
            addCriterion("ThumbnailURL <", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlLessThanOrEqualTo(String value) {
            addCriterion("ThumbnailURL <=", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlLike(String value) {
            addCriterion("ThumbnailURL like", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotLike(String value) {
            addCriterion("ThumbnailURL not like", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlIn(List<String> values) {
            addCriterion("ThumbnailURL in", values, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotIn(List<String> values) {
            addCriterion("ThumbnailURL not in", values, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlBetween(String value1, String value2) {
            addCriterion("ThumbnailURL between", value1, value2, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotBetween(String value1, String value2) {
            addCriterion("ThumbnailURL not between", value1, value2, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andBandnamesIsNull() {
            addCriterion("bandnames is null");
            return (Criteria) this;
        }

        public Criteria andBandnamesIsNotNull() {
            addCriterion("bandnames is not null");
            return (Criteria) this;
        }

        public Criteria andBandnamesEqualTo(String value) {
            addCriterion("bandnames =", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesNotEqualTo(String value) {
            addCriterion("bandnames <>", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesGreaterThan(String value) {
            addCriterion("bandnames >", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesGreaterThanOrEqualTo(String value) {
            addCriterion("bandnames >=", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesLessThan(String value) {
            addCriterion("bandnames <", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesLessThanOrEqualTo(String value) {
            addCriterion("bandnames <=", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesLike(String value) {
            addCriterion("bandnames like", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesNotLike(String value) {
            addCriterion("bandnames not like", value, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesIn(List<String> values) {
            addCriterion("bandnames in", values, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesNotIn(List<String> values) {
            addCriterion("bandnames not in", values, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesBetween(String value1, String value2) {
            addCriterion("bandnames between", value1, value2, "bandnames");
            return (Criteria) this;
        }

        public Criteria andBandnamesNotBetween(String value1, String value2) {
            addCriterion("bandnames not between", value1, value2, "bandnames");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Double value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Double value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Double value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Double value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Double value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Double value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Double> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Double> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Double value1, Double value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Double value1, Double value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}