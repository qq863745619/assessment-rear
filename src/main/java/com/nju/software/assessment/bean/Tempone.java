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
 * @Date 2020-09-11 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="tempone" )
public class Tempone  implements Serializable {
	private static final long serialVersionUID =  3510514199793234499L;

	@Id
   	@Column(name = "index" )
	private Long index;

   	@Column(name = "departName" )
	private String departName;

   	@Column(name = "responser" )
	private String responser;

   	@Column(name = "roomNum" )
	private Long roomNum;

   	@Column(name = "diskIndex" )
	private String diskIndex;

   	@Column(name = "unique" )
	private Long unique;

   	@Column(name = "productType" )
	private String productType;

	public Long getIndex() {
		return this.index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getResponser() {
		return this.responser;
	}

	public void setResponser(String responser) {
		this.responser = responser;
	}

	public Long getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(Long roomNum) {
		this.roomNum = roomNum;
	}

	public String getDiskIndex() {
		return this.diskIndex;
	}

	public void setDiskIndex(String diskIndex) {
		this.diskIndex = diskIndex;
	}

	public Long getUnique() {
		return this.unique;
	}

	public void setUnique(Long unique) {
		this.unique = unique;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

}
