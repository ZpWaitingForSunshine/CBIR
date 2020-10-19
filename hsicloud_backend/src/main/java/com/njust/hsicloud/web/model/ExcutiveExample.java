package com.njust.hsicloud.web.model;

import java.util.ArrayList;
import java.util.List;

public class ExcutiveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExcutiveExample() {
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

        public Criteria andUerNameIsNull() {
            addCriterion("uer_name is null");
            return (Criteria) this;
        }

        public Criteria andUerNameIsNotNull() {
            addCriterion("uer_name is not null");
            return (Criteria) this;
        }

        public Criteria andUerNameEqualTo(String value) {
            addCriterion("uer_name =", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameNotEqualTo(String value) {
            addCriterion("uer_name <>", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameGreaterThan(String value) {
            addCriterion("uer_name >", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameGreaterThanOrEqualTo(String value) {
            addCriterion("uer_name >=", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameLessThan(String value) {
            addCriterion("uer_name <", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameLessThanOrEqualTo(String value) {
            addCriterion("uer_name <=", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameLike(String value) {
            addCriterion("uer_name like", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameNotLike(String value) {
            addCriterion("uer_name not like", value, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameIn(List<String> values) {
            addCriterion("uer_name in", values, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameNotIn(List<String> values) {
            addCriterion("uer_name not in", values, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameBetween(String value1, String value2) {
            addCriterion("uer_name between", value1, value2, "uerName");
            return (Criteria) this;
        }

        public Criteria andUerNameNotBetween(String value1, String value2) {
            addCriterion("uer_name not between", value1, value2, "uerName");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersIsNull() {
            addCriterion("nodes_numbers is null");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersIsNotNull() {
            addCriterion("nodes_numbers is not null");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersEqualTo(Integer value) {
            addCriterion("nodes_numbers =", value, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersNotEqualTo(Integer value) {
            addCriterion("nodes_numbers <>", value, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersGreaterThan(Integer value) {
            addCriterion("nodes_numbers >", value, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("nodes_numbers >=", value, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersLessThan(Integer value) {
            addCriterion("nodes_numbers <", value, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersLessThanOrEqualTo(Integer value) {
            addCriterion("nodes_numbers <=", value, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersIn(List<Integer> values) {
            addCriterion("nodes_numbers in", values, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersNotIn(List<Integer> values) {
            addCriterion("nodes_numbers not in", values, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersBetween(Integer value1, Integer value2) {
            addCriterion("nodes_numbers between", value1, value2, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNumbersNotBetween(Integer value1, Integer value2) {
            addCriterion("nodes_numbers not between", value1, value2, "nodesNumbers");
            return (Criteria) this;
        }

        public Criteria andNodesNameIsNull() {
            addCriterion("nodes_name is null");
            return (Criteria) this;
        }

        public Criteria andNodesNameIsNotNull() {
            addCriterion("nodes_name is not null");
            return (Criteria) this;
        }

        public Criteria andNodesNameEqualTo(String value) {
            addCriterion("nodes_name =", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameNotEqualTo(String value) {
            addCriterion("nodes_name <>", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameGreaterThan(String value) {
            addCriterion("nodes_name >", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameGreaterThanOrEqualTo(String value) {
            addCriterion("nodes_name >=", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameLessThan(String value) {
            addCriterion("nodes_name <", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameLessThanOrEqualTo(String value) {
            addCriterion("nodes_name <=", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameLike(String value) {
            addCriterion("nodes_name like", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameNotLike(String value) {
            addCriterion("nodes_name not like", value, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameIn(List<String> values) {
            addCriterion("nodes_name in", values, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameNotIn(List<String> values) {
            addCriterion("nodes_name not in", values, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameBetween(String value1, String value2) {
            addCriterion("nodes_name between", value1, value2, "nodesName");
            return (Criteria) this;
        }

        public Criteria andNodesNameNotBetween(String value1, String value2) {
            addCriterion("nodes_name not between", value1, value2, "nodesName");
            return (Criteria) this;
        }

        public Criteria andParamtersIsNull() {
            addCriterion("paramters is null");
            return (Criteria) this;
        }

        public Criteria andParamtersIsNotNull() {
            addCriterion("paramters is not null");
            return (Criteria) this;
        }

        public Criteria andParamtersEqualTo(String value) {
            addCriterion("paramters =", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersNotEqualTo(String value) {
            addCriterion("paramters <>", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersGreaterThan(String value) {
            addCriterion("paramters >", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersGreaterThanOrEqualTo(String value) {
            addCriterion("paramters >=", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersLessThan(String value) {
            addCriterion("paramters <", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersLessThanOrEqualTo(String value) {
            addCriterion("paramters <=", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersLike(String value) {
            addCriterion("paramters like", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersNotLike(String value) {
            addCriterion("paramters not like", value, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersIn(List<String> values) {
            addCriterion("paramters in", values, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersNotIn(List<String> values) {
            addCriterion("paramters not in", values, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersBetween(String value1, String value2) {
            addCriterion("paramters between", value1, value2, "paramters");
            return (Criteria) this;
        }

        public Criteria andParamtersNotBetween(String value1, String value2) {
            addCriterion("paramters not between", value1, value2, "paramters");
            return (Criteria) this;
        }

        public Criteria andDagLinkIsNull() {
            addCriterion("dag_link is null");
            return (Criteria) this;
        }

        public Criteria andDagLinkIsNotNull() {
            addCriterion("dag_link is not null");
            return (Criteria) this;
        }

        public Criteria andDagLinkEqualTo(String value) {
            addCriterion("dag_link =", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkNotEqualTo(String value) {
            addCriterion("dag_link <>", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkGreaterThan(String value) {
            addCriterion("dag_link >", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkGreaterThanOrEqualTo(String value) {
            addCriterion("dag_link >=", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkLessThan(String value) {
            addCriterion("dag_link <", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkLessThanOrEqualTo(String value) {
            addCriterion("dag_link <=", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkLike(String value) {
            addCriterion("dag_link like", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkNotLike(String value) {
            addCriterion("dag_link not like", value, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkIn(List<String> values) {
            addCriterion("dag_link in", values, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkNotIn(List<String> values) {
            addCriterion("dag_link not in", values, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkBetween(String value1, String value2) {
            addCriterion("dag_link between", value1, value2, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDagLinkNotBetween(String value1, String value2) {
            addCriterion("dag_link not between", value1, value2, "dagLink");
            return (Criteria) this;
        }

        public Criteria andDispatchResultIsNull() {
            addCriterion("dispatch_result is null");
            return (Criteria) this;
        }

        public Criteria andDispatchResultIsNotNull() {
            addCriterion("dispatch_result is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchResultEqualTo(String value) {
            addCriterion("dispatch_result =", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultNotEqualTo(String value) {
            addCriterion("dispatch_result <>", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultGreaterThan(String value) {
            addCriterion("dispatch_result >", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultGreaterThanOrEqualTo(String value) {
            addCriterion("dispatch_result >=", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultLessThan(String value) {
            addCriterion("dispatch_result <", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultLessThanOrEqualTo(String value) {
            addCriterion("dispatch_result <=", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultLike(String value) {
            addCriterion("dispatch_result like", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultNotLike(String value) {
            addCriterion("dispatch_result not like", value, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultIn(List<String> values) {
            addCriterion("dispatch_result in", values, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultNotIn(List<String> values) {
            addCriterion("dispatch_result not in", values, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultBetween(String value1, String value2) {
            addCriterion("dispatch_result between", value1, value2, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchResultNotBetween(String value1, String value2) {
            addCriterion("dispatch_result not between", value1, value2, "dispatchResult");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkIsNull() {
            addCriterion("dispatch_local_link is null");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkIsNotNull() {
            addCriterion("dispatch_local_link is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkEqualTo(String value) {
            addCriterion("dispatch_local_link =", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkNotEqualTo(String value) {
            addCriterion("dispatch_local_link <>", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkGreaterThan(String value) {
            addCriterion("dispatch_local_link >", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkGreaterThanOrEqualTo(String value) {
            addCriterion("dispatch_local_link >=", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkLessThan(String value) {
            addCriterion("dispatch_local_link <", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkLessThanOrEqualTo(String value) {
            addCriterion("dispatch_local_link <=", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkLike(String value) {
            addCriterion("dispatch_local_link like", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkNotLike(String value) {
            addCriterion("dispatch_local_link not like", value, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkIn(List<String> values) {
            addCriterion("dispatch_local_link in", values, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkNotIn(List<String> values) {
            addCriterion("dispatch_local_link not in", values, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkBetween(String value1, String value2) {
            addCriterion("dispatch_local_link between", value1, value2, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andDispatchLocalLinkNotBetween(String value1, String value2) {
            addCriterion("dispatch_local_link not between", value1, value2, "dispatchLocalLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkIsNull() {
            addCriterion("gant_link is null");
            return (Criteria) this;
        }

        public Criteria andGantLinkIsNotNull() {
            addCriterion("gant_link is not null");
            return (Criteria) this;
        }

        public Criteria andGantLinkEqualTo(String value) {
            addCriterion("gant_link =", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkNotEqualTo(String value) {
            addCriterion("gant_link <>", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkGreaterThan(String value) {
            addCriterion("gant_link >", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkGreaterThanOrEqualTo(String value) {
            addCriterion("gant_link >=", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkLessThan(String value) {
            addCriterion("gant_link <", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkLessThanOrEqualTo(String value) {
            addCriterion("gant_link <=", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkLike(String value) {
            addCriterion("gant_link like", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkNotLike(String value) {
            addCriterion("gant_link not like", value, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkIn(List<String> values) {
            addCriterion("gant_link in", values, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkNotIn(List<String> values) {
            addCriterion("gant_link not in", values, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkBetween(String value1, String value2) {
            addCriterion("gant_link between", value1, value2, "gantLink");
            return (Criteria) this;
        }

        public Criteria andGantLinkNotBetween(String value1, String value2) {
            addCriterion("gant_link not between", value1, value2, "gantLink");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("result like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("result not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdIsNull() {
            addCriterion("submission_id is null");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdIsNotNull() {
            addCriterion("submission_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdEqualTo(String value) {
            addCriterion("submission_id =", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotEqualTo(String value) {
            addCriterion("submission_id <>", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdGreaterThan(String value) {
            addCriterion("submission_id >", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdGreaterThanOrEqualTo(String value) {
            addCriterion("submission_id >=", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdLessThan(String value) {
            addCriterion("submission_id <", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdLessThanOrEqualTo(String value) {
            addCriterion("submission_id <=", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdLike(String value) {
            addCriterion("submission_id like", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotLike(String value) {
            addCriterion("submission_id not like", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdIn(List<String> values) {
            addCriterion("submission_id in", values, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotIn(List<String> values) {
            addCriterion("submission_id not in", values, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdBetween(String value1, String value2) {
            addCriterion("submission_id between", value1, value2, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotBetween(String value1, String value2) {
            addCriterion("submission_id not between", value1, value2, "submissionId");
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