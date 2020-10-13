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
 * @Date 2020-08-21 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="product" )
public class Product  implements Serializable {
	private static final long serialVersionUID =  6012988395348335702L;

	@Id
   	@Column(name = "product_id" )
	private Long productId;

   	@Column(name = "product_name" )
	private String productName;

   	@Column(name = "object_id" )
	private Long objectId;

   	@Column(name = "product_parameter" )
	private String productParameter;

   	@Column(name = "product_address_id" )
	private Long productAddressId;

   	@Column(name = "product_contains" )
	private String productContains;

   	@Column(name = "product_add_time" )
	private Date productAddTime;

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getProductParameter() {
		return this.productParameter;
	}

	public void setProductParameter(String productParameter) {
		this.productParameter = productParameter;
	}

	public Long getProductAddressId() {
		return this.productAddressId;
	}

	public void setProductAddressId(Long productAddressId) {
		this.productAddressId = productAddressId;
	}

	public String getProductContains() {
		return this.productContains;
	}

	public void setProductContains(String productContains) {
		this.productContains = productContains;
	}

	public Date getProductAddTime() {
		return this.productAddTime;
	}

	public void setProductAddTime(Date productAddTime) {
		this.productAddTime = productAddTime;
	}

}
