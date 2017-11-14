package cn.material.base.purchase.vo;

import java.util.Date;




public class PurchaseOrderVo {
	
	private String id;
	
	private String serialNo;
	
	private String materialId;
	private String materialName;
	
	private String categoryId;
	private String useCategory;
	private String specificCategory;
	
	private String materialModel;
	private String materialUnit;
	private double materialWholesalePrice;
	
	private Integer orderAmount;
	private String orderStatus;
	private Date applicationDate;
	private Date auditDate;
	private String applicant;
	private String auditor;
	private double orderTotalPrice;
	private String minOrderTotalPrice;
	private String maxOrderTotalPrice;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public double getMaterialWholesalePrice() {
		return materialWholesalePrice;
	}
	public void setMaterialWholesalePrice(double materialWholesalePrice) {
		this.materialWholesalePrice = materialWholesalePrice;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getUseCategory() {
		return useCategory;
	}
	public void setUseCategory(String useCategory) {
		this.useCategory = useCategory;
	}
	public String getSpecificCategory() {
		return specificCategory;
	}
	public void setSpecificCategory(String specificCategory) {
		this.specificCategory = specificCategory;
	}
	public String getMinOrderTotalPrice() {
		return minOrderTotalPrice;
	}
	public void setMinOrderTotalPrice(String minOrderTotalPrice) {
		this.minOrderTotalPrice = minOrderTotalPrice;
	}
	public String getMaxOrderTotalPrice() {
		return maxOrderTotalPrice;
	}
	public void setMaxOrderTotalPrice(String maxOrderTotalPrice) {
		this.maxOrderTotalPrice = maxOrderTotalPrice;
	}
	

	

}
