package com.njust.hsicloud.web.model;

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNull() {
            addCriterion("filename is null");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNotNull() {
            addCriterion("filename is not null");
            return (Criteria) this;
        }

        public Criteria andFilenameEqualTo(String value) {
            addCriterion("filename =", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotEqualTo(String value) {
            addCriterion("filename <>", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThan(String value) {
            addCriterion("filename >", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("filename >=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThan(String value) {
            addCriterion("filename <", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThanOrEqualTo(String value) {
            addCriterion("filename <=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLike(String value) {
            addCriterion("filename like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotLike(String value) {
            addCriterion("filename not like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameIn(List<String> values) {
            addCriterion("filename in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotIn(List<String> values) {
            addCriterion("filename not in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameBetween(String value1, String value2) {
            addCriterion("filename between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotBetween(String value1, String value2) {
            addCriterion("filename not between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andSamplesIsNull() {
            addCriterion("samples is null");
            return (Criteria) this;
        }

        public Criteria andSamplesIsNotNull() {
            addCriterion("samples is not null");
            return (Criteria) this;
        }

        public Criteria andSamplesEqualTo(Short value) {
            addCriterion("samples =", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotEqualTo(Short value) {
            addCriterion("samples <>", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesGreaterThan(Short value) {
            addCriterion("samples >", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesGreaterThanOrEqualTo(Short value) {
            addCriterion("samples >=", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesLessThan(Short value) {
            addCriterion("samples <", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesLessThanOrEqualTo(Short value) {
            addCriterion("samples <=", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesIn(List<Short> values) {
            addCriterion("samples in", values, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotIn(List<Short> values) {
            addCriterion("samples not in", values, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesBetween(Short value1, Short value2) {
            addCriterion("samples between", value1, value2, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotBetween(Short value1, Short value2) {
            addCriterion("samples not between", value1, value2, "samples");
            return (Criteria) this;
        }

        public Criteria andRowsIsNull() {
            addCriterion("rows is null");
            return (Criteria) this;
        }

        public Criteria andRowsIsNotNull() {
            addCriterion("rows is not null");
            return (Criteria) this;
        }

        public Criteria andRowsEqualTo(Short value) {
            addCriterion("rows =", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotEqualTo(Short value) {
            addCriterion("rows <>", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsGreaterThan(Short value) {
            addCriterion("rows >", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsGreaterThanOrEqualTo(Short value) {
            addCriterion("rows >=", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsLessThan(Short value) {
            addCriterion("rows <", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsLessThanOrEqualTo(Short value) {
            addCriterion("rows <=", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsIn(List<Short> values) {
            addCriterion("rows in", values, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotIn(List<Short> values) {
            addCriterion("rows not in", values, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsBetween(Short value1, Short value2) {
            addCriterion("rows between", value1, value2, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotBetween(Short value1, Short value2) {
            addCriterion("rows not between", value1, value2, "rows");
            return (Criteria) this;
        }

        public Criteria andBandsIsNull() {
            addCriterion("bands is null");
            return (Criteria) this;
        }

        public Criteria andBandsIsNotNull() {
            addCriterion("bands is not null");
            return (Criteria) this;
        }

        public Criteria andBandsEqualTo(Short value) {
            addCriterion("bands =", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotEqualTo(Short value) {
            addCriterion("bands <>", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsGreaterThan(Short value) {
            addCriterion("bands >", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsGreaterThanOrEqualTo(Short value) {
            addCriterion("bands >=", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsLessThan(Short value) {
            addCriterion("bands <", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsLessThanOrEqualTo(Short value) {
            addCriterion("bands <=", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsIn(List<Short> values) {
            addCriterion("bands in", values, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotIn(List<Short> values) {
            addCriterion("bands not in", values, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsBetween(Short value1, Short value2) {
            addCriterion("bands between", value1, value2, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotBetween(Short value1, Short value2) {
            addCriterion("bands not between", value1, value2, "bands");
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

        public Criteria andInterleaveIsNull() {
            addCriterion("Interleave is null");
            return (Criteria) this;
        }

        public Criteria andInterleaveIsNotNull() {
            addCriterion("Interleave is not null");
            return (Criteria) this;
        }

        public Criteria andInterleaveEqualTo(String value) {
            addCriterion("Interleave =", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotEqualTo(String value) {
            addCriterion("Interleave <>", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveGreaterThan(String value) {
            addCriterion("Interleave >", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveGreaterThanOrEqualTo(String value) {
            addCriterion("Interleave >=", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLessThan(String value) {
            addCriterion("Interleave <", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLessThanOrEqualTo(String value) {
            addCriterion("Interleave <=", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLike(String value) {
            addCriterion("Interleave like", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotLike(String value) {
            addCriterion("Interleave not like", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveIn(List<String> values) {
            addCriterion("Interleave in", values, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotIn(List<String> values) {
            addCriterion("Interleave not in", values, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveBetween(String value1, String value2) {
            addCriterion("Interleave between", value1, value2, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotBetween(String value1, String value2) {
            addCriterion("Interleave not between", value1, value2, "interleave");
            return (Criteria) this;
        }

        public Criteria andByteorderIsNull() {
            addCriterion("byteOrder is null");
            return (Criteria) this;
        }

        public Criteria andByteorderIsNotNull() {
            addCriterion("byteOrder is not null");
            return (Criteria) this;
        }

        public Criteria andByteorderEqualTo(Byte value) {
            addCriterion("byteOrder =", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotEqualTo(Byte value) {
            addCriterion("byteOrder <>", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderGreaterThan(Byte value) {
            addCriterion("byteOrder >", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderGreaterThanOrEqualTo(Byte value) {
            addCriterion("byteOrder >=", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderLessThan(Byte value) {
            addCriterion("byteOrder <", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderLessThanOrEqualTo(Byte value) {
            addCriterion("byteOrder <=", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderIn(List<Byte> values) {
            addCriterion("byteOrder in", values, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotIn(List<Byte> values) {
            addCriterion("byteOrder not in", values, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderBetween(Byte value1, Byte value2) {
            addCriterion("byteOrder between", value1, value2, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotBetween(Byte value1, Byte value2) {
            addCriterion("byteOrder not between", value1, value2, "byteorder");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureIsNull() {
            addCriterion("timeofexposure is null");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureIsNotNull() {
            addCriterion("timeofexposure is not null");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureEqualTo(String value) {
            addCriterion("timeofexposure =", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotEqualTo(String value) {
            addCriterion("timeofexposure <>", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureGreaterThan(String value) {
            addCriterion("timeofexposure >", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureGreaterThanOrEqualTo(String value) {
            addCriterion("timeofexposure >=", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureLessThan(String value) {
            addCriterion("timeofexposure <", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureLessThanOrEqualTo(String value) {
            addCriterion("timeofexposure <=", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureLike(String value) {
            addCriterion("timeofexposure like", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotLike(String value) {
            addCriterion("timeofexposure not like", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureIn(List<String> values) {
            addCriterion("timeofexposure in", values, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotIn(List<String> values) {
            addCriterion("timeofexposure not in", values, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureBetween(String value1, String value2) {
            addCriterion("timeofexposure between", value1, value2, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotBetween(String value1, String value2) {
            addCriterion("timeofexposure not between", value1, value2, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andImagebinIsNull() {
            addCriterion("imageBin is null");
            return (Criteria) this;
        }

        public Criteria andImagebinIsNotNull() {
            addCriterion("imageBin is not null");
            return (Criteria) this;
        }

        public Criteria andImagebinEqualTo(Integer value) {
            addCriterion("imageBin =", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinNotEqualTo(Integer value) {
            addCriterion("imageBin <>", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinGreaterThan(Integer value) {
            addCriterion("imageBin >", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinGreaterThanOrEqualTo(Integer value) {
            addCriterion("imageBin >=", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinLessThan(Integer value) {
            addCriterion("imageBin <", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinLessThanOrEqualTo(Integer value) {
            addCriterion("imageBin <=", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinIn(List<Integer> values) {
            addCriterion("imageBin in", values, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinNotIn(List<Integer> values) {
            addCriterion("imageBin not in", values, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinBetween(Integer value1, Integer value2) {
            addCriterion("imageBin between", value1, value2, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinNotBetween(Integer value1, Integer value2) {
            addCriterion("imageBin not between", value1, value2, "imagebin");
            return (Criteria) this;
        }

        public Criteria andAutoexproixIsNull() {
            addCriterion("autoexproix is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproixIsNotNull() {
            addCriterion("autoexproix is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproixEqualTo(Integer value) {
            addCriterion("autoexproix =", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixNotEqualTo(Integer value) {
            addCriterion("autoexproix <>", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixGreaterThan(Integer value) {
            addCriterion("autoexproix >", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixGreaterThanOrEqualTo(Integer value) {
            addCriterion("autoexproix >=", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixLessThan(Integer value) {
            addCriterion("autoexproix <", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixLessThanOrEqualTo(Integer value) {
            addCriterion("autoexproix <=", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixIn(List<Integer> values) {
            addCriterion("autoexproix in", values, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixNotIn(List<Integer> values) {
            addCriterion("autoexproix not in", values, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixBetween(Integer value1, Integer value2) {
            addCriterion("autoexproix between", value1, value2, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixNotBetween(Integer value1, Integer value2) {
            addCriterion("autoexproix not between", value1, value2, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyIsNull() {
            addCriterion("autoexproiy is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyIsNotNull() {
            addCriterion("autoexproiy is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyEqualTo(Integer value) {
            addCriterion("autoexproiy =", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyNotEqualTo(Integer value) {
            addCriterion("autoexproiy <>", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyGreaterThan(Integer value) {
            addCriterion("autoexproiy >", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyGreaterThanOrEqualTo(Integer value) {
            addCriterion("autoexproiy >=", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyLessThan(Integer value) {
            addCriterion("autoexproiy <", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyLessThanOrEqualTo(Integer value) {
            addCriterion("autoexproiy <=", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyIn(List<Integer> values) {
            addCriterion("autoexproiy in", values, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyNotIn(List<Integer> values) {
            addCriterion("autoexproiy not in", values, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyBetween(Integer value1, Integer value2) {
            addCriterion("autoexproiy between", value1, value2, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyNotBetween(Integer value1, Integer value2) {
            addCriterion("autoexproiy not between", value1, value2, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthIsNull() {
            addCriterion("autoexproiwidth is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthIsNotNull() {
            addCriterion("autoexproiwidth is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthEqualTo(Integer value) {
            addCriterion("autoexproiwidth =", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthNotEqualTo(Integer value) {
            addCriterion("autoexproiwidth <>", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthGreaterThan(Integer value) {
            addCriterion("autoexproiwidth >", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("autoexproiwidth >=", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthLessThan(Integer value) {
            addCriterion("autoexproiwidth <", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthLessThanOrEqualTo(Integer value) {
            addCriterion("autoexproiwidth <=", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthIn(List<Integer> values) {
            addCriterion("autoexproiwidth in", values, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthNotIn(List<Integer> values) {
            addCriterion("autoexproiwidth not in", values, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthBetween(Integer value1, Integer value2) {
            addCriterion("autoexproiwidth between", value1, value2, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthNotBetween(Integer value1, Integer value2) {
            addCriterion("autoexproiwidth not between", value1, value2, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightIsNull() {
            addCriterion("autoexproiheight is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightIsNotNull() {
            addCriterion("autoexproiheight is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightEqualTo(Integer value) {
            addCriterion("autoexproiheight =", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightNotEqualTo(Integer value) {
            addCriterion("autoexproiheight <>", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightGreaterThan(Integer value) {
            addCriterion("autoexproiheight >", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightGreaterThanOrEqualTo(Integer value) {
            addCriterion("autoexproiheight >=", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightLessThan(Integer value) {
            addCriterion("autoexproiheight <", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightLessThanOrEqualTo(Integer value) {
            addCriterion("autoexproiheight <=", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightIn(List<Integer> values) {
            addCriterion("autoexproiheight in", values, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightNotIn(List<Integer> values) {
            addCriterion("autoexproiheight not in", values, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightBetween(Integer value1, Integer value2) {
            addCriterion("autoexproiheight between", value1, value2, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightNotBetween(Integer value1, Integer value2) {
            addCriterion("autoexproiheight not between", value1, value2, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andGainIsNull() {
            addCriterion("gain is null");
            return (Criteria) this;
        }

        public Criteria andGainIsNotNull() {
            addCriterion("gain is not null");
            return (Criteria) this;
        }

        public Criteria andGainEqualTo(Double value) {
            addCriterion("gain =", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainNotEqualTo(Double value) {
            addCriterion("gain <>", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainGreaterThan(Double value) {
            addCriterion("gain >", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainGreaterThanOrEqualTo(Double value) {
            addCriterion("gain >=", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainLessThan(Double value) {
            addCriterion("gain <", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainLessThanOrEqualTo(Double value) {
            addCriterion("gain <=", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainIn(List<Double> values) {
            addCriterion("gain in", values, "gain");
            return (Criteria) this;
        }

        public Criteria andGainNotIn(List<Double> values) {
            addCriterion("gain not in", values, "gain");
            return (Criteria) this;
        }

        public Criteria andGainBetween(Double value1, Double value2) {
            addCriterion("gain between", value1, value2, "gain");
            return (Criteria) this;
        }

        public Criteria andGainNotBetween(Double value1, Double value2) {
            addCriterion("gain not between", value1, value2, "gain");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientIsNull() {
            addCriterion("exposurecoefficient is null");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientIsNotNull() {
            addCriterion("exposurecoefficient is not null");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientEqualTo(Double value) {
            addCriterion("exposurecoefficient =", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientNotEqualTo(Double value) {
            addCriterion("exposurecoefficient <>", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientGreaterThan(Double value) {
            addCriterion("exposurecoefficient >", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientGreaterThanOrEqualTo(Double value) {
            addCriterion("exposurecoefficient >=", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientLessThan(Double value) {
            addCriterion("exposurecoefficient <", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientLessThanOrEqualTo(Double value) {
            addCriterion("exposurecoefficient <=", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientIn(List<Double> values) {
            addCriterion("exposurecoefficient in", values, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientNotIn(List<Double> values) {
            addCriterion("exposurecoefficient not in", values, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientBetween(Double value1, Double value2) {
            addCriterion("exposurecoefficient between", value1, value2, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientNotBetween(Double value1, Double value2) {
            addCriterion("exposurecoefficient not between", value1, value2, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureIsNull() {
            addCriterion("LCTFTemperature is null");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureIsNotNull() {
            addCriterion("LCTFTemperature is not null");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureEqualTo(String value) {
            addCriterion("LCTFTemperature =", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureNotEqualTo(String value) {
            addCriterion("LCTFTemperature <>", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureGreaterThan(String value) {
            addCriterion("LCTFTemperature >", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("LCTFTemperature >=", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureLessThan(String value) {
            addCriterion("LCTFTemperature <", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureLessThanOrEqualTo(String value) {
            addCriterion("LCTFTemperature <=", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureLike(String value) {
            addCriterion("LCTFTemperature like", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureNotLike(String value) {
            addCriterion("LCTFTemperature not like", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureIn(List<String> values) {
            addCriterion("LCTFTemperature in", values, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureNotIn(List<String> values) {
            addCriterion("LCTFTemperature not in", values, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureBetween(String value1, String value2) {
            addCriterion("LCTFTemperature between", value1, value2, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureNotBetween(String value1, String value2) {
            addCriterion("LCTFTemperature not between", value1, value2, "lctftemperature");
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

        public Criteria andHdfsurlIsNull() {
            addCriterion("HDFSURL is null");
            return (Criteria) this;
        }

        public Criteria andHdfsurlIsNotNull() {
            addCriterion("HDFSURL is not null");
            return (Criteria) this;
        }

        public Criteria andHdfsurlEqualTo(String value) {
            addCriterion("HDFSURL =", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlNotEqualTo(String value) {
            addCriterion("HDFSURL <>", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlGreaterThan(String value) {
            addCriterion("HDFSURL >", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlGreaterThanOrEqualTo(String value) {
            addCriterion("HDFSURL >=", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlLessThan(String value) {
            addCriterion("HDFSURL <", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlLessThanOrEqualTo(String value) {
            addCriterion("HDFSURL <=", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlLike(String value) {
            addCriterion("HDFSURL like", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlNotLike(String value) {
            addCriterion("HDFSURL not like", value, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlIn(List<String> values) {
            addCriterion("HDFSURL in", values, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlNotIn(List<String> values) {
            addCriterion("HDFSURL not in", values, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlBetween(String value1, String value2) {
            addCriterion("HDFSURL between", value1, value2, "hdfsurl");
            return (Criteria) this;
        }

        public Criteria andHdfsurlNotBetween(String value1, String value2) {
            addCriterion("HDFSURL not between", value1, value2, "hdfsurl");
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

        public Criteria andBlocksizeIsNull() {
            addCriterion("blocksize is null");
            return (Criteria) this;
        }

        public Criteria andBlocksizeIsNotNull() {
            addCriterion("blocksize is not null");
            return (Criteria) this;
        }

        public Criteria andBlocksizeEqualTo(Double value) {
            addCriterion("blocksize =", value, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeNotEqualTo(Double value) {
            addCriterion("blocksize <>", value, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeGreaterThan(Double value) {
            addCriterion("blocksize >", value, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeGreaterThanOrEqualTo(Double value) {
            addCriterion("blocksize >=", value, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeLessThan(Double value) {
            addCriterion("blocksize <", value, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeLessThanOrEqualTo(Double value) {
            addCriterion("blocksize <=", value, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeIn(List<Double> values) {
            addCriterion("blocksize in", values, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeNotIn(List<Double> values) {
            addCriterion("blocksize not in", values, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeBetween(Double value1, Double value2) {
            addCriterion("blocksize between", value1, value2, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocksizeNotBetween(Double value1, Double value2) {
            addCriterion("blocksize not between", value1, value2, "blocksize");
            return (Criteria) this;
        }

        public Criteria andBlocknumberIsNull() {
            addCriterion("blocknumber is null");
            return (Criteria) this;
        }

        public Criteria andBlocknumberIsNotNull() {
            addCriterion("blocknumber is not null");
            return (Criteria) this;
        }

        public Criteria andBlocknumberEqualTo(Integer value) {
            addCriterion("blocknumber =", value, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberNotEqualTo(Integer value) {
            addCriterion("blocknumber <>", value, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberGreaterThan(Integer value) {
            addCriterion("blocknumber >", value, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("blocknumber >=", value, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberLessThan(Integer value) {
            addCriterion("blocknumber <", value, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberLessThanOrEqualTo(Integer value) {
            addCriterion("blocknumber <=", value, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberIn(List<Integer> values) {
            addCriterion("blocknumber in", values, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberNotIn(List<Integer> values) {
            addCriterion("blocknumber not in", values, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberBetween(Integer value1, Integer value2) {
            addCriterion("blocknumber between", value1, value2, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andBlocknumberNotBetween(Integer value1, Integer value2) {
            addCriterion("blocknumber not between", value1, value2, "blocknumber");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Long value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Long value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Long value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Long value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Long value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Long> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Long> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Long value1, Long value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Long value1, Long value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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