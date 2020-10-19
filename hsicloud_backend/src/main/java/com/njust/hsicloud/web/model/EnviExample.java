package com.njust.hsicloud.web.model;

import java.util.ArrayList;
import java.util.List;

public class EnviExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnviExample() {
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

        public Criteria andSamplesEqualTo(Integer value) {
            addCriterion("samples =", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotEqualTo(Integer value) {
            addCriterion("samples <>", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesGreaterThan(Integer value) {
            addCriterion("samples >", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesGreaterThanOrEqualTo(Integer value) {
            addCriterion("samples >=", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesLessThan(Integer value) {
            addCriterion("samples <", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesLessThanOrEqualTo(Integer value) {
            addCriterion("samples <=", value, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesIn(List<Integer> values) {
            addCriterion("samples in", values, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotIn(List<Integer> values) {
            addCriterion("samples not in", values, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesBetween(Integer value1, Integer value2) {
            addCriterion("samples between", value1, value2, "samples");
            return (Criteria) this;
        }

        public Criteria andSamplesNotBetween(Integer value1, Integer value2) {
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

        public Criteria andRowsEqualTo(Integer value) {
            addCriterion("rows =", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotEqualTo(Integer value) {
            addCriterion("rows <>", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsGreaterThan(Integer value) {
            addCriterion("rows >", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsGreaterThanOrEqualTo(Integer value) {
            addCriterion("rows >=", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsLessThan(Integer value) {
            addCriterion("rows <", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsLessThanOrEqualTo(Integer value) {
            addCriterion("rows <=", value, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsIn(List<Integer> values) {
            addCriterion("rows in", values, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotIn(List<Integer> values) {
            addCriterion("rows not in", values, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsBetween(Integer value1, Integer value2) {
            addCriterion("rows between", value1, value2, "rows");
            return (Criteria) this;
        }

        public Criteria andRowsNotBetween(Integer value1, Integer value2) {
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

        public Criteria andBandsEqualTo(Integer value) {
            addCriterion("bands =", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotEqualTo(Integer value) {
            addCriterion("bands <>", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsGreaterThan(Integer value) {
            addCriterion("bands >", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsGreaterThanOrEqualTo(Integer value) {
            addCriterion("bands >=", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsLessThan(Integer value) {
            addCriterion("bands <", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsLessThanOrEqualTo(Integer value) {
            addCriterion("bands <=", value, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsIn(List<Integer> values) {
            addCriterion("bands in", values, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotIn(List<Integer> values) {
            addCriterion("bands not in", values, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsBetween(Integer value1, Integer value2) {
            addCriterion("bands between", value1, value2, "bands");
            return (Criteria) this;
        }

        public Criteria andBandsNotBetween(Integer value1, Integer value2) {
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
            addCriterion("datatype is null");
            return (Criteria) this;
        }

        public Criteria andDatatypeIsNotNull() {
            addCriterion("datatype is not null");
            return (Criteria) this;
        }

        public Criteria andDatatypeEqualTo(Integer value) {
            addCriterion("datatype =", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeNotEqualTo(Integer value) {
            addCriterion("datatype <>", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeGreaterThan(Integer value) {
            addCriterion("datatype >", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("datatype >=", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeLessThan(Integer value) {
            addCriterion("datatype <", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeLessThanOrEqualTo(Integer value) {
            addCriterion("datatype <=", value, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeIn(List<Integer> values) {
            addCriterion("datatype in", values, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeNotIn(List<Integer> values) {
            addCriterion("datatype not in", values, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeBetween(Integer value1, Integer value2) {
            addCriterion("datatype between", value1, value2, "datatype");
            return (Criteria) this;
        }

        public Criteria andDatatypeNotBetween(Integer value1, Integer value2) {
            addCriterion("datatype not between", value1, value2, "datatype");
            return (Criteria) this;
        }

        public Criteria andInterleaveIsNull() {
            addCriterion("interleave is null");
            return (Criteria) this;
        }

        public Criteria andInterleaveIsNotNull() {
            addCriterion("interleave is not null");
            return (Criteria) this;
        }

        public Criteria andInterleaveEqualTo(String value) {
            addCriterion("interleave =", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotEqualTo(String value) {
            addCriterion("interleave <>", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveGreaterThan(String value) {
            addCriterion("interleave >", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveGreaterThanOrEqualTo(String value) {
            addCriterion("interleave >=", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLessThan(String value) {
            addCriterion("interleave <", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLessThanOrEqualTo(String value) {
            addCriterion("interleave <=", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveLike(String value) {
            addCriterion("interleave like", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotLike(String value) {
            addCriterion("interleave not like", value, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveIn(List<String> values) {
            addCriterion("interleave in", values, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotIn(List<String> values) {
            addCriterion("interleave not in", values, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveBetween(String value1, String value2) {
            addCriterion("interleave between", value1, value2, "interleave");
            return (Criteria) this;
        }

        public Criteria andInterleaveNotBetween(String value1, String value2) {
            addCriterion("interleave not between", value1, value2, "interleave");
            return (Criteria) this;
        }

        public Criteria andByteorderIsNull() {
            addCriterion("byteorder is null");
            return (Criteria) this;
        }

        public Criteria andByteorderIsNotNull() {
            addCriterion("byteorder is not null");
            return (Criteria) this;
        }

        public Criteria andByteorderEqualTo(Integer value) {
            addCriterion("byteorder =", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotEqualTo(Integer value) {
            addCriterion("byteorder <>", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderGreaterThan(Integer value) {
            addCriterion("byteorder >", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("byteorder >=", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderLessThan(Integer value) {
            addCriterion("byteorder <", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderLessThanOrEqualTo(Integer value) {
            addCriterion("byteorder <=", value, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderIn(List<Integer> values) {
            addCriterion("byteorder in", values, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotIn(List<Integer> values) {
            addCriterion("byteorder not in", values, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderBetween(Integer value1, Integer value2) {
            addCriterion("byteorder between", value1, value2, "byteorder");
            return (Criteria) this;
        }

        public Criteria andByteorderNotBetween(Integer value1, Integer value2) {
            addCriterion("byteorder not between", value1, value2, "byteorder");
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

        public Criteria andWavelengthIsNull() {
            addCriterion("wavelength is null");
            return (Criteria) this;
        }

        public Criteria andWavelengthIsNotNull() {
            addCriterion("wavelength is not null");
            return (Criteria) this;
        }

        public Criteria andWavelengthEqualTo(String value) {
            addCriterion("wavelength =", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthNotEqualTo(String value) {
            addCriterion("wavelength <>", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthGreaterThan(String value) {
            addCriterion("wavelength >", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthGreaterThanOrEqualTo(String value) {
            addCriterion("wavelength >=", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthLessThan(String value) {
            addCriterion("wavelength <", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthLessThanOrEqualTo(String value) {
            addCriterion("wavelength <=", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthLike(String value) {
            addCriterion("wavelength like", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthNotLike(String value) {
            addCriterion("wavelength not like", value, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthIn(List<String> values) {
            addCriterion("wavelength in", values, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthNotIn(List<String> values) {
            addCriterion("wavelength not in", values, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthBetween(String value1, String value2) {
            addCriterion("wavelength between", value1, value2, "wavelength");
            return (Criteria) this;
        }

        public Criteria andWavelengthNotBetween(String value1, String value2) {
            addCriterion("wavelength not between", value1, value2, "wavelength");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureIsNull() {
            addCriterion("Timeofexposure is null");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureIsNotNull() {
            addCriterion("Timeofexposure is not null");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureEqualTo(String value) {
            addCriterion("Timeofexposure =", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotEqualTo(String value) {
            addCriterion("Timeofexposure <>", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureGreaterThan(String value) {
            addCriterion("Timeofexposure >", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureGreaterThanOrEqualTo(String value) {
            addCriterion("Timeofexposure >=", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureLessThan(String value) {
            addCriterion("Timeofexposure <", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureLessThanOrEqualTo(String value) {
            addCriterion("Timeofexposure <=", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureLike(String value) {
            addCriterion("Timeofexposure like", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotLike(String value) {
            addCriterion("Timeofexposure not like", value, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureIn(List<String> values) {
            addCriterion("Timeofexposure in", values, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotIn(List<String> values) {
            addCriterion("Timeofexposure not in", values, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureBetween(String value1, String value2) {
            addCriterion("Timeofexposure between", value1, value2, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andTimeofexposureNotBetween(String value1, String value2) {
            addCriterion("Timeofexposure not between", value1, value2, "timeofexposure");
            return (Criteria) this;
        }

        public Criteria andImagebinIsNull() {
            addCriterion("ImageBin is null");
            return (Criteria) this;
        }

        public Criteria andImagebinIsNotNull() {
            addCriterion("ImageBin is not null");
            return (Criteria) this;
        }

        public Criteria andImagebinEqualTo(Integer value) {
            addCriterion("ImageBin =", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinNotEqualTo(Integer value) {
            addCriterion("ImageBin <>", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinGreaterThan(Integer value) {
            addCriterion("ImageBin >", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinGreaterThanOrEqualTo(Integer value) {
            addCriterion("ImageBin >=", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinLessThan(Integer value) {
            addCriterion("ImageBin <", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinLessThanOrEqualTo(Integer value) {
            addCriterion("ImageBin <=", value, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinIn(List<Integer> values) {
            addCriterion("ImageBin in", values, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinNotIn(List<Integer> values) {
            addCriterion("ImageBin not in", values, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinBetween(Integer value1, Integer value2) {
            addCriterion("ImageBin between", value1, value2, "imagebin");
            return (Criteria) this;
        }

        public Criteria andImagebinNotBetween(Integer value1, Integer value2) {
            addCriterion("ImageBin not between", value1, value2, "imagebin");
            return (Criteria) this;
        }

        public Criteria andAutoexproixIsNull() {
            addCriterion("AutoExpROIX is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproixIsNotNull() {
            addCriterion("AutoExpROIX is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproixEqualTo(Integer value) {
            addCriterion("AutoExpROIX =", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixNotEqualTo(Integer value) {
            addCriterion("AutoExpROIX <>", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixGreaterThan(Integer value) {
            addCriterion("AutoExpROIX >", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixGreaterThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIX >=", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixLessThan(Integer value) {
            addCriterion("AutoExpROIX <", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixLessThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIX <=", value, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixIn(List<Integer> values) {
            addCriterion("AutoExpROIX in", values, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixNotIn(List<Integer> values) {
            addCriterion("AutoExpROIX not in", values, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIX between", value1, value2, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproixNotBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIX not between", value1, value2, "autoexproix");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyIsNull() {
            addCriterion("AutoExpROIY is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyIsNotNull() {
            addCriterion("AutoExpROIY is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyEqualTo(Integer value) {
            addCriterion("AutoExpROIY =", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyNotEqualTo(Integer value) {
            addCriterion("AutoExpROIY <>", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyGreaterThan(Integer value) {
            addCriterion("AutoExpROIY >", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyGreaterThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIY >=", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyLessThan(Integer value) {
            addCriterion("AutoExpROIY <", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyLessThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIY <=", value, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyIn(List<Integer> values) {
            addCriterion("AutoExpROIY in", values, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyNotIn(List<Integer> values) {
            addCriterion("AutoExpROIY not in", values, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIY between", value1, value2, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiyNotBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIY not between", value1, value2, "autoexproiy");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthIsNull() {
            addCriterion("AutoExpROIWidth is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthIsNotNull() {
            addCriterion("AutoExpROIWidth is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthEqualTo(Integer value) {
            addCriterion("AutoExpROIWidth =", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthNotEqualTo(Integer value) {
            addCriterion("AutoExpROIWidth <>", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthGreaterThan(Integer value) {
            addCriterion("AutoExpROIWidth >", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIWidth >=", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthLessThan(Integer value) {
            addCriterion("AutoExpROIWidth <", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthLessThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIWidth <=", value, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthIn(List<Integer> values) {
            addCriterion("AutoExpROIWidth in", values, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthNotIn(List<Integer> values) {
            addCriterion("AutoExpROIWidth not in", values, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIWidth between", value1, value2, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiwidthNotBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIWidth not between", value1, value2, "autoexproiwidth");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightIsNull() {
            addCriterion("AutoExpROIHeight is null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightIsNotNull() {
            addCriterion("AutoExpROIHeight is not null");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightEqualTo(Integer value) {
            addCriterion("AutoExpROIHeight =", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightNotEqualTo(Integer value) {
            addCriterion("AutoExpROIHeight <>", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightGreaterThan(Integer value) {
            addCriterion("AutoExpROIHeight >", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightGreaterThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIHeight >=", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightLessThan(Integer value) {
            addCriterion("AutoExpROIHeight <", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightLessThanOrEqualTo(Integer value) {
            addCriterion("AutoExpROIHeight <=", value, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightIn(List<Integer> values) {
            addCriterion("AutoExpROIHeight in", values, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightNotIn(List<Integer> values) {
            addCriterion("AutoExpROIHeight not in", values, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIHeight between", value1, value2, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andAutoexproiheightNotBetween(Integer value1, Integer value2) {
            addCriterion("AutoExpROIHeight not between", value1, value2, "autoexproiheight");
            return (Criteria) this;
        }

        public Criteria andGainIsNull() {
            addCriterion("Gain is null");
            return (Criteria) this;
        }

        public Criteria andGainIsNotNull() {
            addCriterion("Gain is not null");
            return (Criteria) this;
        }

        public Criteria andGainEqualTo(Float value) {
            addCriterion("Gain =", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainNotEqualTo(Float value) {
            addCriterion("Gain <>", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainGreaterThan(Float value) {
            addCriterion("Gain >", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainGreaterThanOrEqualTo(Float value) {
            addCriterion("Gain >=", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainLessThan(Float value) {
            addCriterion("Gain <", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainLessThanOrEqualTo(Float value) {
            addCriterion("Gain <=", value, "gain");
            return (Criteria) this;
        }

        public Criteria andGainIn(List<Float> values) {
            addCriterion("Gain in", values, "gain");
            return (Criteria) this;
        }

        public Criteria andGainNotIn(List<Float> values) {
            addCriterion("Gain not in", values, "gain");
            return (Criteria) this;
        }

        public Criteria andGainBetween(Float value1, Float value2) {
            addCriterion("Gain between", value1, value2, "gain");
            return (Criteria) this;
        }

        public Criteria andGainNotBetween(Float value1, Float value2) {
            addCriterion("Gain not between", value1, value2, "gain");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientIsNull() {
            addCriterion("Exposurecoefficient is null");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientIsNotNull() {
            addCriterion("Exposurecoefficient is not null");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientEqualTo(Float value) {
            addCriterion("Exposurecoefficient =", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientNotEqualTo(Float value) {
            addCriterion("Exposurecoefficient <>", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientGreaterThan(Float value) {
            addCriterion("Exposurecoefficient >", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientGreaterThanOrEqualTo(Float value) {
            addCriterion("Exposurecoefficient >=", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientLessThan(Float value) {
            addCriterion("Exposurecoefficient <", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientLessThanOrEqualTo(Float value) {
            addCriterion("Exposurecoefficient <=", value, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientIn(List<Float> values) {
            addCriterion("Exposurecoefficient in", values, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientNotIn(List<Float> values) {
            addCriterion("Exposurecoefficient not in", values, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientBetween(Float value1, Float value2) {
            addCriterion("Exposurecoefficient between", value1, value2, "exposurecoefficient");
            return (Criteria) this;
        }

        public Criteria andExposurecoefficientNotBetween(Float value1, Float value2) {
            addCriterion("Exposurecoefficient not between", value1, value2, "exposurecoefficient");
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

        public Criteria andLctftemperatureEqualTo(Float value) {
            addCriterion("LCTFTemperature =", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureNotEqualTo(Float value) {
            addCriterion("LCTFTemperature <>", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureGreaterThan(Float value) {
            addCriterion("LCTFTemperature >", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureGreaterThanOrEqualTo(Float value) {
            addCriterion("LCTFTemperature >=", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureLessThan(Float value) {
            addCriterion("LCTFTemperature <", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureLessThanOrEqualTo(Float value) {
            addCriterion("LCTFTemperature <=", value, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureIn(List<Float> values) {
            addCriterion("LCTFTemperature in", values, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureNotIn(List<Float> values) {
            addCriterion("LCTFTemperature not in", values, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureBetween(Float value1, Float value2) {
            addCriterion("LCTFTemperature between", value1, value2, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andLctftemperatureNotBetween(Float value1, Float value2) {
            addCriterion("LCTFTemperature not between", value1, value2, "lctftemperature");
            return (Criteria) this;
        }

        public Criteria andHdfsIsNull() {
            addCriterion("hdfs is null");
            return (Criteria) this;
        }

        public Criteria andHdfsIsNotNull() {
            addCriterion("hdfs is not null");
            return (Criteria) this;
        }

        public Criteria andHdfsEqualTo(String value) {
            addCriterion("hdfs =", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsNotEqualTo(String value) {
            addCriterion("hdfs <>", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsGreaterThan(String value) {
            addCriterion("hdfs >", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsGreaterThanOrEqualTo(String value) {
            addCriterion("hdfs >=", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsLessThan(String value) {
            addCriterion("hdfs <", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsLessThanOrEqualTo(String value) {
            addCriterion("hdfs <=", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsLike(String value) {
            addCriterion("hdfs like", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsNotLike(String value) {
            addCriterion("hdfs not like", value, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsIn(List<String> values) {
            addCriterion("hdfs in", values, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsNotIn(List<String> values) {
            addCriterion("hdfs not in", values, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsBetween(String value1, String value2) {
            addCriterion("hdfs between", value1, value2, "hdfs");
            return (Criteria) this;
        }

        public Criteria andHdfsNotBetween(String value1, String value2) {
            addCriterion("hdfs not between", value1, value2, "hdfs");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlIsNull() {
            addCriterion("thumbnailurl is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlIsNotNull() {
            addCriterion("thumbnailurl is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlEqualTo(String value) {
            addCriterion("thumbnailurl =", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotEqualTo(String value) {
            addCriterion("thumbnailurl <>", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlGreaterThan(String value) {
            addCriterion("thumbnailurl >", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlGreaterThanOrEqualTo(String value) {
            addCriterion("thumbnailurl >=", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlLessThan(String value) {
            addCriterion("thumbnailurl <", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlLessThanOrEqualTo(String value) {
            addCriterion("thumbnailurl <=", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlLike(String value) {
            addCriterion("thumbnailurl like", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotLike(String value) {
            addCriterion("thumbnailurl not like", value, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlIn(List<String> values) {
            addCriterion("thumbnailurl in", values, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotIn(List<String> values) {
            addCriterion("thumbnailurl not in", values, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlBetween(String value1, String value2) {
            addCriterion("thumbnailurl between", value1, value2, "thumbnailurl");
            return (Criteria) this;
        }

        public Criteria andThumbnailurlNotBetween(String value1, String value2) {
            addCriterion("thumbnailurl not between", value1, value2, "thumbnailurl");
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