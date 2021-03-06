package com.fbee.modules.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenantsBankCardsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public TenantsBankCardsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
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

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(Integer value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(Integer value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(Integer value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(Integer value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(Integer value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<Integer> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<Integer> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(Integer value1, Integer value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("CARD_NO not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_Type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_Type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(String value) {
            addCriterion("account_Type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(String value) {
            addCriterion("account_Type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(String value) {
            addCriterion("account_Type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("account_Type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(String value) {
            addCriterion("account_Type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(String value) {
            addCriterion("account_Type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLike(String value) {
            addCriterion("account_Type like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotLike(String value) {
            addCriterion("account_Type not like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<String> values) {
            addCriterion("account_Type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<String> values) {
            addCriterion("account_Type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(String value1, String value2) {
            addCriterion("account_Type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(String value1, String value2) {
            addCriterion("account_Type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNull() {
            addCriterion("BANK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNotNull() {
            addCriterion("BANK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBankCodeEqualTo(String value) {
            addCriterion("BANK_CODE =", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotEqualTo(String value) {
            addCriterion("BANK_CODE <>", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThan(String value) {
            addCriterion("BANK_CODE >", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CODE >=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThan(String value) {
            addCriterion("BANK_CODE <", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThanOrEqualTo(String value) {
            addCriterion("BANK_CODE <=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLike(String value) {
            addCriterion("BANK_CODE like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotLike(String value) {
            addCriterion("BANK_CODE not like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeIn(List<String> values) {
            addCriterion("BANK_CODE in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotIn(List<String> values) {
            addCriterion("BANK_CODE not in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeBetween(String value1, String value2) {
            addCriterion("BANK_CODE between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotBetween(String value1, String value2) {
            addCriterion("BANK_CODE not between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankSecNameIsNull() {
            addCriterion("BANK_SEC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankSecNameIsNotNull() {
            addCriterion("BANK_SEC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankSecNameEqualTo(String value) {
            addCriterion("BANK_SEC_NAME =", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameNotEqualTo(String value) {
            addCriterion("BANK_SEC_NAME <>", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameGreaterThan(String value) {
            addCriterion("BANK_SEC_NAME >", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_SEC_NAME >=", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameLessThan(String value) {
            addCriterion("BANK_SEC_NAME <", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_SEC_NAME <=", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameLike(String value) {
            addCriterion("BANK_SEC_NAME like", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameNotLike(String value) {
            addCriterion("BANK_SEC_NAME not like", value, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameIn(List<String> values) {
            addCriterion("BANK_SEC_NAME in", values, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameNotIn(List<String> values) {
            addCriterion("BANK_SEC_NAME not in", values, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameBetween(String value1, String value2) {
            addCriterion("BANK_SEC_NAME between", value1, value2, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andBankSecNameNotBetween(String value1, String value2) {
            addCriterion("BANK_SEC_NAME not between", value1, value2, "bankSecName");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andIsUsableIsNull() {
            addCriterion("is_usable is null");
            return (Criteria) this;
        }

        public Criteria andIsUsableIsNotNull() {
            addCriterion("is_usable is not null");
            return (Criteria) this;
        }

        public Criteria andIsUsableEqualTo(String value) {
            addCriterion("is_usable =", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableNotEqualTo(String value) {
            addCriterion("is_usable <>", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableGreaterThan(String value) {
            addCriterion("is_usable >", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableGreaterThanOrEqualTo(String value) {
            addCriterion("is_usable >=", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableLessThan(String value) {
            addCriterion("is_usable <", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableLessThanOrEqualTo(String value) {
            addCriterion("is_usable <=", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableLike(String value) {
            addCriterion("is_usable like", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableNotLike(String value) {
            addCriterion("is_usable not like", value, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableIn(List<String> values) {
            addCriterion("is_usable in", values, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableNotIn(List<String> values) {
            addCriterion("is_usable not in", values, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableBetween(String value1, String value2) {
            addCriterion("is_usable between", value1, value2, "isUsable");
            return (Criteria) this;
        }

        public Criteria andIsUsableNotBetween(String value1, String value2) {
            addCriterion("is_usable not between", value1, value2, "isUsable");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddAccountIsNull() {
            addCriterion("add_account is null");
            return (Criteria) this;
        }

        public Criteria andAddAccountIsNotNull() {
            addCriterion("add_account is not null");
            return (Criteria) this;
        }

        public Criteria andAddAccountEqualTo(String value) {
            addCriterion("add_account =", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountNotEqualTo(String value) {
            addCriterion("add_account <>", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountGreaterThan(String value) {
            addCriterion("add_account >", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountGreaterThanOrEqualTo(String value) {
            addCriterion("add_account >=", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountLessThan(String value) {
            addCriterion("add_account <", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountLessThanOrEqualTo(String value) {
            addCriterion("add_account <=", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountLike(String value) {
            addCriterion("add_account like", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountNotLike(String value) {
            addCriterion("add_account not like", value, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountIn(List<String> values) {
            addCriterion("add_account in", values, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountNotIn(List<String> values) {
            addCriterion("add_account not in", values, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountBetween(String value1, String value2) {
            addCriterion("add_account between", value1, value2, "addAccount");
            return (Criteria) this;
        }

        public Criteria andAddAccountNotBetween(String value1, String value2) {
            addCriterion("add_account not between", value1, value2, "addAccount");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyAccountIsNull() {
            addCriterion("modify_account is null");
            return (Criteria) this;
        }

        public Criteria andModifyAccountIsNotNull() {
            addCriterion("modify_account is not null");
            return (Criteria) this;
        }

        public Criteria andModifyAccountEqualTo(String value) {
            addCriterion("modify_account =", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountNotEqualTo(String value) {
            addCriterion("modify_account <>", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountGreaterThan(String value) {
            addCriterion("modify_account >", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountGreaterThanOrEqualTo(String value) {
            addCriterion("modify_account >=", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountLessThan(String value) {
            addCriterion("modify_account <", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountLessThanOrEqualTo(String value) {
            addCriterion("modify_account <=", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountLike(String value) {
            addCriterion("modify_account like", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountNotLike(String value) {
            addCriterion("modify_account not like", value, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountIn(List<String> values) {
            addCriterion("modify_account in", values, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountNotIn(List<String> values) {
            addCriterion("modify_account not in", values, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountBetween(String value1, String value2) {
            addCriterion("modify_account between", value1, value2, "modifyAccount");
            return (Criteria) this;
        }

        public Criteria andModifyAccountNotBetween(String value1, String value2) {
            addCriterion("modify_account not between", value1, value2, "modifyAccount");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated do_not_delete_during_merge Wed Apr 19 09:30:13 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tenants_bank_cards
     *
     * @mbggenerated Wed Apr 19 09:30:13 CST 2017
     */
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