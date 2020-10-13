package com.nju.software.assessment.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;
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
 * @Date 2020-07-30 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="authority" )
public class Authority  implements Serializable {
	private static final long serialVersionUID =  3725615324775629526L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
   	@Column(name = "qxbh" )
	private Long qxbh;

   	@Column(name = "qxmc" )
	private String qxmc;

   	@Column(name = "qxsm" )
	private String qxsm;

   	@Column(name = "qxcjsj" )
	private Date qxcjsj;

	public Long getQxbh() {
		return this.qxbh;
	}

	public void setQxbh(Long qxbh) {
		this.qxbh = qxbh;
	}

	public String getQxmc() {
		return this.qxmc;
	}

	public void setQxmc(String qxmc) {
		this.qxmc = qxmc;
	}

	public String getQxsm() {
		return this.qxsm;
	}

	public void setQxsm(String qxsm) {
		this.qxsm = qxsm;
	}

	public Date getQxcjsj() {
		return this.qxcjsj;
	}

	public void setQxcjsj(Date qxcjsj) {
		this.qxcjsj = qxcjsj;
	}

}
