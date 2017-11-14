package cn.material.base.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.base.supplier.entity.Supplier;
import cn.material.base.warehouse.entity.Warehouse;
import cn.material.common.base.BaseEntity;
import cn.material.common.constants.Constants;

@Entity
@Table(name="base_material")
public class Material extends BaseEntity{
	
	/**
	 * serialNo:材料编号
	 */
	@Column
	private String serialNo;
	
	/**
	 * name:材料名字
	 */
	@Column
	private String materialName;
	
	/**
	 * 材料所属分类 
	 */
	@ManyToOne
	@JoinColumn(name="category_id")
	private MaterialCategory materialCategory;
	
	/**
	 * 材料规格
	 */
	@Column
	private String materialModel;
	
	/**
	 * 材料规格单位
	 */
	@Column
	private String materialUnit;
	
	/**
	 * 库存量
	 */
	@Column
	private int materialStock;
	/**
	 * 进货价
	 */
	@Column
	private double wholesalePrice;
	/**
	 * 零售价
	 */
	@Column
	private double retailPrice;
	/**
	 * 备注
	 */
	@Column
	private String remark;
	
	/**
	 * 伪删除标记
	 */
	@Column
	private int delFlag=Constants.DIC_NO;
	
	/**
	 * 材料存放的仓库
	 */
	@JoinColumn(name="warehouse_id")
	@ManyToOne
	private Warehouse warehouse;
	
	/**
	 * 供应商
	 */
	@JoinColumn(name="supplier_id")
	@ManyToOne
	private Supplier supplier;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public MaterialCategory getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
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

	public int getMaterialStock() {
		return materialStock;
	}

	public void setMaterialStock(int materialStock) {
		this.materialStock = materialStock;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	

}
