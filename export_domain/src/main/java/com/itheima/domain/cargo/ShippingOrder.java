package com.itheima.domain.cargo;

import java.io.Serializable;
import java.util.Date;

public class ShippingOrder implements Serializable {

    private String shippingOrderId;

    private String orderType;

    private String shipper;

    private String consignee;

    private String notifyParty;

    private String lcNo;

    private String portOfLoading;

    private String portOfTrans;

    private String portOfDischarge;

    private Date loadingDate;

    private Date limitDate;

    private String isBatch;

    private String isTrans;

    private String copyNum;

    private String remark;

    private String specialCondition;

    private String freight;

    private String checkBy;

    private Integer state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String companyId;

    private String companyName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.shipping_order_id
     *
     * @return the value of co_shipping_order.shipping_order_id
     *
     * @mbg.generated
     */
    public String getShippingOrderId() {
        return shippingOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.shipping_order_id
     *
     * @param shippingOrderId the value for co_shipping_order.shipping_order_id
     *
     * @mbg.generated
     */
    public void setShippingOrderId(String shippingOrderId) {
        this.shippingOrderId = shippingOrderId == null ? null : shippingOrderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.order_type
     *
     * @return the value of co_shipping_order.order_type
     *
     * @mbg.generated
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.order_type
     *
     * @param orderType the value for co_shipping_order.order_type
     *
     * @mbg.generated
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.shipper
     *
     * @return the value of co_shipping_order.shipper
     *
     * @mbg.generated
     */
    public String getShipper() {
        return shipper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.shipper
     *
     * @param shipper the value for co_shipping_order.shipper
     *
     * @mbg.generated
     */
    public void setShipper(String shipper) {
        this.shipper = shipper == null ? null : shipper.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.consignee
     *
     * @return the value of co_shipping_order.consignee
     *
     * @mbg.generated
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.consignee
     *
     * @param consignee the value for co_shipping_order.consignee
     *
     * @mbg.generated
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.notify_party
     *
     * @return the value of co_shipping_order.notify_party
     *
     * @mbg.generated
     */
    public String getNotifyParty() {
        return notifyParty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.notify_party
     *
     * @param notifyParty the value for co_shipping_order.notify_party
     *
     * @mbg.generated
     */
    public void setNotifyParty(String notifyParty) {
        this.notifyParty = notifyParty == null ? null : notifyParty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.lc_no
     *
     * @return the value of co_shipping_order.lc_no
     *
     * @mbg.generated
     */
    public String getLcNo() {
        return lcNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.lc_no
     *
     * @param lcNo the value for co_shipping_order.lc_no
     *
     * @mbg.generated
     */
    public void setLcNo(String lcNo) {
        this.lcNo = lcNo == null ? null : lcNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.port_of_loading
     *
     * @return the value of co_shipping_order.port_of_loading
     *
     * @mbg.generated
     */
    public String getPortOfLoading() {
        return portOfLoading;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.port_of_loading
     *
     * @param portOfLoading the value for co_shipping_order.port_of_loading
     *
     * @mbg.generated
     */
    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading == null ? null : portOfLoading.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.port_of_trans
     *
     * @return the value of co_shipping_order.port_of_trans
     *
     * @mbg.generated
     */
    public String getPortOfTrans() {
        return portOfTrans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.port_of_trans
     *
     * @param portOfTrans the value for co_shipping_order.port_of_trans
     *
     * @mbg.generated
     */
    public void setPortOfTrans(String portOfTrans) {
        this.portOfTrans = portOfTrans == null ? null : portOfTrans.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.port_of_discharge
     *
     * @return the value of co_shipping_order.port_of_discharge
     *
     * @mbg.generated
     */
    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.port_of_discharge
     *
     * @param portOfDischarge the value for co_shipping_order.port_of_discharge
     *
     * @mbg.generated
     */
    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge == null ? null : portOfDischarge.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.loading_date
     *
     * @return the value of co_shipping_order.loading_date
     *
     * @mbg.generated
     */
    public Date getLoadingDate() {
        return loadingDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.loading_date
     *
     * @param loadingDate the value for co_shipping_order.loading_date
     *
     * @mbg.generated
     */
    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.limit_date
     *
     * @return the value of co_shipping_order.limit_date
     *
     * @mbg.generated
     */
    public Date getLimitDate() {
        return limitDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.limit_date
     *
     * @param limitDate the value for co_shipping_order.limit_date
     *
     * @mbg.generated
     */
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.is_batch
     *
     * @return the value of co_shipping_order.is_batch
     *
     * @mbg.generated
     */
    public String getIsBatch() {
        return isBatch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.is_batch
     *
     * @param isBatch the value for co_shipping_order.is_batch
     *
     * @mbg.generated
     */
    public void setIsBatch(String isBatch) {
        this.isBatch = isBatch == null ? null : isBatch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.is_trans
     *
     * @return the value of co_shipping_order.is_trans
     *
     * @mbg.generated
     */
    public String getIsTrans() {
        return isTrans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.is_trans
     *
     * @param isTrans the value for co_shipping_order.is_trans
     *
     * @mbg.generated
     */
    public void setIsTrans(String isTrans) {
        this.isTrans = isTrans == null ? null : isTrans.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.copy_num
     *
     * @return the value of co_shipping_order.copy_num
     *
     * @mbg.generated
     */
    public String getCopyNum() {
        return copyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.copy_num
     *
     * @param copyNum the value for co_shipping_order.copy_num
     *
     * @mbg.generated
     */
    public void setCopyNum(String copyNum) {
        this.copyNum = copyNum == null ? null : copyNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.remark
     *
     * @return the value of co_shipping_order.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.remark
     *
     * @param remark the value for co_shipping_order.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.special_condition
     *
     * @return the value of co_shipping_order.special_condition
     *
     * @mbg.generated
     */
    public String getSpecialCondition() {
        return specialCondition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.special_condition
     *
     * @param specialCondition the value for co_shipping_order.special_condition
     *
     * @mbg.generated
     */
    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition == null ? null : specialCondition.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.freight
     *
     * @return the value of co_shipping_order.freight
     *
     * @mbg.generated
     */
    public String getFreight() {
        return freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.freight
     *
     * @param freight the value for co_shipping_order.freight
     *
     * @mbg.generated
     */
    public void setFreight(String freight) {
        this.freight = freight == null ? null : freight.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.check_by
     *
     * @return the value of co_shipping_order.check_by
     *
     * @mbg.generated
     */
    public String getCheckBy() {
        return checkBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.check_by
     *
     * @param checkBy the value for co_shipping_order.check_by
     *
     * @mbg.generated
     */
    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy == null ? null : checkBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.state
     *
     * @return the value of co_shipping_order.state
     *
     * @mbg.generated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.state
     *
     * @param state the value for co_shipping_order.state
     *
     * @mbg.generated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.create_by
     *
     * @return the value of co_shipping_order.create_by
     *
     * @mbg.generated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.create_by
     *
     * @param createBy the value for co_shipping_order.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.create_dept
     *
     * @return the value of co_shipping_order.create_dept
     *
     * @mbg.generated
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.create_dept
     *
     * @param createDept the value for co_shipping_order.create_dept
     *
     * @mbg.generated
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.create_time
     *
     * @return the value of co_shipping_order.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.create_time
     *
     * @param createTime the value for co_shipping_order.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.company_id
     *
     * @return the value of co_shipping_order.company_id
     *
     * @mbg.generated
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.company_id
     *
     * @param companyId the value for co_shipping_order.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.company_name
     *
     * @return the value of co_shipping_order.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.company_name
     *
     * @param companyName the value for co_shipping_order.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}