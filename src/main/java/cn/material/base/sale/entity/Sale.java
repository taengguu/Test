package cn.material.base.sale.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.material.base.material.entity.Material;
import cn.material.base.member.entity.Member;
import cn.material.common.base.BaseEntity;
import cn.material.common.constants.Constants;


/**
 * 销售订单实体
 * @author xjl
 *
 */
@Table(name="sale_order")
@Entity
public class Sale extends BaseEntity{

	private static final long serialVersionUID = -2096208738267653446L;

	/**
	 * 销售订单编号
	 */
	@Column
	private String serialNo;
	/**
	 * 订单材料
	 */
	@OneToOne
	@JoinColumn(name="material_id")
	private Material material;
	/**
	 * 会员
	 */
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;
	/**
	 * 购买数量
	 */
	@Column
	private int amount;
	/**
	 * 总价
	 */
	@Column
	private float totalPrice;
	/**
	 * 下单时间
	 */
	@Column
	private Date orderTime;
	/**
	 * 订单状态
	 */
	@Column
	private String saleStatus;
	/**
	 * 支付方式
	 */
	@Column
	private String payType;
	/**
	 * 支付时间
	 */
	@Column
	private Date payTime;
	/**
	 * 备注
	 */
	@Column
	private String remark;
	/**
	 * 伪删标记
	 */
	@Column
	private int delFlag=Constants.DIC_NO;
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	
	

}
