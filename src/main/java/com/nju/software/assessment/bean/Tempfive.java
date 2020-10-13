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
 * @Date 2020-09-14 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="tempfive" )
public class Tempfive  implements Serializable {
	private static final long serialVersionUID =  8921981819029031045L;

	@Id
   	@Column(name = "index" )
	private Double index;

   	@Column(name = "departName" )
	private String departName;

   	@Column(name = "roomNum" )
	private String roomNum;

   	@Column(name = "name" )
	private String name;

   	@Column(name = "ip" )
	private String ip;

   	@Column(name = "productType" )
	private String productType;

   	@Column(name = "tip" )
	private String tip;

   	@Column(name = "productTaiNum" )
	private String productTaiNum;

   	@Column(name = "productBiNum" )
	private Double productBiNum;

	public Double getIndex() {
		return this.index;
	}

	public void setIndex(Double index) {
		this.index = index;
	}

	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getProductTaiNum() {
		return this.productTaiNum;
	}

	public void setProductTaiNum(String productTaiNum) {
		this.productTaiNum = productTaiNum;
	}

	public Double getProductBiNum() {
		return this.productBiNum;
	}

	public void setProductBiNum(Double productBiNum) {
		this.productBiNum = productBiNum;
	}

}
