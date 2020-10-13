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
@Table ( name ="department_assess_record" )
public class DepartmentAssessRecord  implements Serializable {
	private static final long serialVersionUID =  7595601259619552072L;

	@Id
   	@Column(name = "department_assess_record_id" )
	private Long departmentAssessRecordId;

   	@Column(name = "dmbh" )
	private String dmbh;

   	@Column(name = "object_id" )
	private Long objectId;

   	@Column(name = "object_name" )
	private String objectName;

	public Long getObjectNum() {
		return objectNum;
	}

	public void setObjectNum(Long objectNum) {
		this.objectNum = objectNum;
	}

	@Column(name = "object_num" )
	private Long objectNum;

	public Long getDepartmentAssessRecordId() {
		return this.departmentAssessRecordId;
	}

	public void setDepartmentAssessRecordId(Long departmentAssessRecordId) {
		this.departmentAssessRecordId = departmentAssessRecordId;
	}

	public String getDmbh() {
		return this.dmbh;
	}

	public void setDmbh(String dmbh) {
		this.dmbh = dmbh;
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

}
