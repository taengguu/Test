package cn.material.base.materialcategory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.material.common.base.BaseEntity;
import cn.material.common.constants.Constants;

/**
 * 具体分类实体
 * @author xjl
 *
 */
@Table(name="base_material_category")
@Entity
public class MaterialCategory extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5140809911739144954L;
	/**
	 * 具体分类编号
	 */
	@Column
	private String serialNo;
	/**
	 * 用途分类 ,关联数据字典
	 */
	@Column
	private String useCategory;
	/**
	 * 具体分类
	 */
	@Column
	private String specificCategory;
	/**
	 * 具体分类描述
	 */
	@Column
	private String decription;
	/**
	 * 删除标记
	 */
	@Column
	private int delFlag = Constants.DIC_NO;
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	

}
