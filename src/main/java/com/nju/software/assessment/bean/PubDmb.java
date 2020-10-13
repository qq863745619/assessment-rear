package com.nju.software.assessment.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nju.software.assessment.bean2.DMB;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  baodan_Fan 
 * @Date 2020-09-07 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="pub_dmb" )
public class PubDmb  implements Serializable {
	private static final long serialVersionUID =  542693377955384096L;

	@Id
   	@Column(name = "DMBH" )
	private String dmbh;

   	@Column(name = "DMMS" )
	private String dmms;

   	@Column(name = "LBBH" )
	private String lbbh;

	public PubDmb(DMB dmb) {
		this.dmbh = dmb.getDmbh();
		this.dmms = dmb.getDmms();
	}

	public String getDmbh() {
		return this.dmbh;
	}

	public void setDmbh(String dmbh) {
		this.dmbh = dmbh;
	}

	public String getDmms() {
		return this.dmms;
	}

	public void setDmms(String dmms) {
		this.dmms = dmms;
	}

	public String getLbbh() {
		return this.lbbh;
	}

	public void setLbbh(String lbbh) {
		this.lbbh = lbbh;
	}

}
