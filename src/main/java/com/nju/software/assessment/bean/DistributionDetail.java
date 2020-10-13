package com.nju.software.assessment.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
 * @Date 2020-08-20 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="distribution_detail" )
public class DistributionDetail  implements Serializable {
	private static final long serialVersionUID =  5189812924910496458L;

	@Id
   	@Column(name = "distribution_detail_id" )
	private String distributionDetailId;

   	@Column(name = "distribution_id" )
	private String distributionId;

   	@Column(name = "object_id" )
	private Long objectId;

   	@Column(name = "object_num" )
	private Integer objectNum;

   	@Column(name = "product_id" )
	private Long productId;

	public String getDistributionDetailId() {
		return this.distributionDetailId;
	}

	public void setDistributionDetailId(String distributionDetailId) {
		this.distributionDetailId = distributionDetailId;
	}

	public String getDistributionId() {
		return this.distributionId;
	}

	public void setDistributionId(String distributionId) {
		this.distributionId = distributionId;
	}

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectNum() {
		return this.objectNum;
	}

	public void setObjectNum(Integer objectNum) {
		this.objectNum = objectNum;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
