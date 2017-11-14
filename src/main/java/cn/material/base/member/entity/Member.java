package cn.material.base.member.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.material.common.base.BaseEntity;
import cn.material.common.constants.Constants;


@Table(name="base_member")
@Entity
public class Member extends BaseEntity{
	

	/**
	 * 会员编码，必添属性
	 */
	@Column
	private String serialNo;

	/**
	 * 会员用户名，必添属性
	 */
	@Column
	private String name;

	/**
	 * 会员性别，必添属性
	 * 1.男  0.女
	 */
	@Column
	private int sex;

	/**
	 * 会员密码，必添属性
	 */
	@Column
	private String password;

	/**
	 * 会员手机号码，必添属性
	 */
	@Column(name = "mobile_phone")
	private String mobilePhone;

	/**
	 * 会员地址
	 */
	@Column
	private String address;

	/**
	 * 会员邮箱号码
	 */
	@Column(name = "E_mail")
	private String eMail;

	/**
	 * 会员积分
	 **/
	@Column
	private int integrl = Constants.DEFULT_VALUE_INTEGRL ;
	/**
	 * 会员最后一次购买时间
	 */
	@Column(name = "last_purchase_time")
	private Date lastPurchaseTime;



	/**
	 * 删除标识，可选{Constants.DIC_NO：0, Constans.DIC_YES：1}，
	 * 默认为Constants.DIC_NO
	 *
	 */
	@Column(name = "del_flag")
	private int delFlag = Constants.DIC_NO;



	public String getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getSex() {
		return sex;
	}



	public void setSex(int sex) {
		this.sex = sex;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getMobilePhone() {
		return mobilePhone;
	}



	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String geteMail() {
		return eMail;
	}



	public void seteMail(String eMail) {
		this.eMail = eMail;
	}



	public int getIntegrl() {
		return integrl;
	}



	public void setIntegrl(int integrl) {
		this.integrl = integrl;
	}






	public Date getLastPurchaseTime() {
		return lastPurchaseTime;
	}



	public void setLastPurchaseTime(Date lastPurchaseTime) {
		this.lastPurchaseTime = lastPurchaseTime;
	}



	public int getDelFlag() {
		return delFlag;
	}



	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	
	
	

}
