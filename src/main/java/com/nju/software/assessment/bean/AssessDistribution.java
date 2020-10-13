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
 * @Date 2020-08-11 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="assess_distribution" )
public class AssessDistribution  implements Serializable {
	private static final long serialVersionUID =  4402845359436282523L;

	@Id
	@Column(name = "distribution_id" )
	private String distributionId;

   	@Column(name = "yhbh" )
	private Integer yhbh;

   	@Column(name = "distribution_state" )
	private Integer distributionState;

   	@Column(name = "distribution_time" )
	private Date distributionTime;

	public String getDistributionId() {
		return this.distributionId;
	}

	public void setDistributionId(String distributionId) {
		this.distributionId = distributionId;
	}

	public Integer getYhbh() {
		return this.yhbh;
	}

	public void setYhbh(Integer yhbh) {
		this.yhbh = yhbh;
	}

	public Integer getDistributionState() {
		return this.distributionState;
	}

	public void setDistributionState(Integer distributionState) {
		this.distributionState = distributionState;
	}

	public Date getDistributionTime() {
		return this.distributionTime;
	}

	public void setDistributionTime(Date distributionTime) {
		this.distributionTime = distributionTime;
	}

}
