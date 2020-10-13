package com.nju.software.assessment.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  baodan_Fan 
 * @Date 2020-09-04 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="user_assess_detail" )
public class UserAssessDetail  implements Serializable {
	private static final long serialVersionUID =  8884146132442458911L;

	@Id
   	@Column(name = "user_assess_detail_id" )
	private Long userAssessDetailId;

   	@Column(name = "user_assess_record_id" )
	private Long userAssessRecordId;

   	@Column(name = "product_id" )
	private Long productId;

   	@Column(name = "address_id" )
	private String addressId;

   	@Column(name = "uniqueIdentify" )
	private String uniqueIdentify;

   	@Column(name = "response_yhbh" )
	private Integer responseYhbh;

   	@Column(name = "is_out" )
	private Integer isOut;

   	@Column(name = "is_secret" )
	private Integer isSecret;

   	@Column(name = "decide_secret_date" )
	private Date decideSecretDate;

   	@Column(name = "secret_level" )
	private String secretLevel;

   	@Column(name = "disk_index" )
	private String diskIndex;

   	@Column(name = "oprate_system_time" )
	private String oprateSystemTime;

   	@Column(name = "network_condition" )
	private String networkCondition;

   	@Column(name = "Mac_IP" )
	private String macIp;

   	@Column(name = "use_condition" )
	private String useCondition;

   	@Column(name = "secret_is_out" )
	private Integer secretIsOut;

   	@Column(name = "device_index" )
	private String deviceIndex;

   	@Column(name = "uniform_numbers" )
	private String uniformNumbers;

   	@Column(name = "tip" )
	private String tip;

   	@Column(name = "user_name" )
	private String userName;


   	//用途
	@Column(name = "use_t")
	private String useT;

	@Column(name = "is_depart_assess")
	private Integer isDepartAssess;


	public Date getDistributeTime() {
		return distributeTime;
	}

	public void setDistributeTime(Date distributeTime) {
		this.distributeTime = distributeTime;
	}

	@Column(name = "distribute_time")
	private Date distributeTime;

	public UserAssessDetail(UserAssessDetail userAssessDetail) {
		this.userAssessDetailId = userAssessDetail.userAssessDetailId;
		this.userAssessRecordId = userAssessDetail.userAssessRecordId;
		this.productId = userAssessDetail.productId;
		this.addressId = userAssessDetail.addressId;
		this.uniqueIdentify = userAssessDetail.uniqueIdentify;
		this.responseYhbh = userAssessDetail.responseYhbh;
		this.isOut = userAssessDetail.isOut;
		this.isSecret = userAssessDetail.isSecret;
		this.decideSecretDate = userAssessDetail.decideSecretDate;
		this.secretLevel = userAssessDetail.secretLevel;
		this.diskIndex = userAssessDetail.diskIndex;
		this.oprateSystemTime = userAssessDetail.oprateSystemTime;
		this.networkCondition = userAssessDetail.networkCondition;
		this.macIp = userAssessDetail.macIp;
		this.useCondition = userAssessDetail.useCondition;
		this.secretIsOut = userAssessDetail.secretIsOut;
		this.deviceIndex = userAssessDetail.deviceIndex;
		this.uniformNumbers = userAssessDetail.uniformNumbers;
		this.tip = userAssessDetail.tip;
		this.userName = userAssessDetail.userName;
		this.useT = userAssessDetail.useT;
		this.distributeTime = userAssessDetail.distributeTime;

	}

	public Integer getIsDepartAssess() {
		return isDepartAssess;
	}

	public void setIsDepartAssess(Integer isDepartAssess) {
		this.isDepartAssess = isDepartAssess;
	}

	public String getUse() {
		return useT;
	}

	public void setUse(String use) {
		this.useT = use;
	}

	public Long getUserAssessDetailId() {
		return this.userAssessDetailId;
	}

	public void setUserAssessDetailId(Long userAssessDetailId) {
		this.userAssessDetailId = userAssessDetailId;
	}

	public Long getUserAssessRecordId() {
		return this.userAssessRecordId;
	}

	public void setUserAssessRecordId(Long userAssessRecordId) {
		this.userAssessRecordId = userAssessRecordId;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getUniqueIdentify() {
		return this.uniqueIdentify;
	}

	public void setUniqueIdentify(String uniqueIdentify) {
		this.uniqueIdentify = uniqueIdentify;
	}

	public Integer getResponseYhbh() {
		return this.responseYhbh;
	}

	public void setResponseYhbh(Integer responseYhbh) {
		this.responseYhbh = responseYhbh;
	}

	public Integer getIsOut() {
		return this.isOut;
	}

	public void setIsOut(Integer isOut) {
		this.isOut = isOut;
	}

	public Integer getIsSecret() {
		return this.isSecret;
	}

	public void setIsSecret(Integer isSecret) {
		this.isSecret = isSecret;
	}

	public Date getDecideSecretDate() {
		return this.decideSecretDate;
	}

	public void setDecideSecretDate(Date decideSecretDate) {
		this.decideSecretDate = decideSecretDate;
	}

	public String getSecretLevel() {
		return this.secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public String getDiskIndex() {
		return this.diskIndex;
	}

	public void setDiskIndex(String diskIndex) {
		this.diskIndex = diskIndex;
	}

	public String getOprateSystemTime() {
		return this.oprateSystemTime;
	}

	public void setOprateSystemTime(String oprateSystemTime) {
		this.oprateSystemTime = oprateSystemTime;
	}

	public String getNetworkCondition() {
		return this.networkCondition;
	}

	public void setNetworkCondition(String networkCondition) {
		this.networkCondition = networkCondition;
	}

	public String getMacIp() {
		return this.macIp;
	}

	public void setMacIp(String macIp) {
		this.macIp = macIp;
	}

	public String getUseCondition() {
		return this.useCondition;
	}

	public void setUseCondition(String useCondition) {
		this.useCondition = useCondition;
	}

	public Integer getSecretIsOut() {
		return this.secretIsOut;
	}

	public void setSecretIsOut(Integer secretIsOut) {
		this.secretIsOut = secretIsOut;
	}

	public String getDeviceIndex() {
		return this.deviceIndex;
	}

	public void setDeviceIndex(String deviceIndex) {
		this.deviceIndex = deviceIndex;
	}

	public String getUniformNumbers() {
		return this.uniformNumbers;
	}

	public void setUniformNumbers(String uniformNumbers) {
		this.uniformNumbers = uniformNumbers;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
