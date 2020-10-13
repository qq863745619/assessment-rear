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
@Table ( name ="tempfour" )
public class Tempfour  implements Serializable {
	private static final long serialVersionUID =  7010422807868113959L;

	@Id
   	@Column(name = "index" )
	private Long index;

   	@Column(name = "departName" )
	private String departName;

   	@Column(name = "roomNum" )
	private String roomNum;

   	@Column(name = "userName" )
	private String userName;

   	@Column(name = "ip" )
	private String ip;

   	@Column(name = "productType" )
	private String productType;

   	@Column(name = "tip" )
	private String tip;

   	@Column(name = "sum" )
	private Long sum;

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

	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Long getSum() {
		return this.sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}

}
