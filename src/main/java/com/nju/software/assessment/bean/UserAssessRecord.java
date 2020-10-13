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
@Table ( name ="user_assess_record" )
public class UserAssessRecord  implements Serializable {
	private static final long serialVersionUID =  8170692590383954856L;

	@Id
   	@Column(name = "user_assess_record_id" )
	private Long userAssessRecordId;

   	@Column(name = "yhbh" )
	private Long yhbh;

   	@Column(name = "object_id" )
	private Long objectId;

   	@Column(name = "object_name" )
	private String objectName;

   	@Column(name = "object_num" )
	private Long objectNum;

	public Long getUserAssessRecordId() {
		return this.userAssessRecordId;
	}

	public void setUserAssessRecordId(Long userAssessRecordId) {
		this.userAssessRecordId = userAssessRecordId;
	}

	public Long getYhbh() {
		return this.yhbh;
	}

	public void setYhbh(Long yhbh) {
		this.yhbh = yhbh;
	}

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getObjectNum() {
		return this.objectNum;
	}

	public void setObjectNum(Long objectNum) {
		this.objectNum = objectNum;
	}

}
