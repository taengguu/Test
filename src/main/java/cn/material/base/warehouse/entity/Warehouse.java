package cn.material.base.warehouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.material.common.constants.Constants;
import cn.material.common.base.BaseEntity;


/**
 * 仓库实体
 * @author xjl
 *
 */

@Table(name="base_warehouse")
@Entity
public class Warehouse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7188108546444889911L;
	/**
	 * 仓库编号
	 */
	@Column
	private String serialNo;
	/**
	 * 仓库序号
	 */
	@Column
	private int warehouseNo;
	/**
	 * 仓库名称
	 */
	@Column
	private String warehouseName;
	
	/**
	 * 仓库描述
	 */
	@Column
	private String description;
	
	/** 删除标识，可选{Constants.DIC_NO：0, Constans.DIC_YES：1}，默认为Constants.DIC_NO **/
	@Column
	private int delFlag = Constants.DIC_NO;
	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public int getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(int warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	

}
