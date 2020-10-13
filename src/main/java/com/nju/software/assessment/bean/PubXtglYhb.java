package com.nju.software.assessment.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nju.software.assessment.bean2.YHB;
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
@Table ( name ="pub_xtgl_yhb" )
public class PubXtglYhb  implements Serializable {
	private static final long serialVersionUID =  3497754702152966099L;

	@Id
   	@Column(name = "YHBH" )
	private Long yhbh;

   	@Column(name = "PHONE" )
	private String phone;

   	@Column(name = "YHBM" )
	private String yhbm;

   	@Column(name = "YHDM" )
	private String yhdm;

   	@Column(name = "YHKL" )
	private String yhkl;

   	@Column(name = "YHMC" )
	private String yhmc;

   	@Column(name = "YHSF" )
	private String yhsf;

   	@Column(name = "FYBH" )
	private Long fybh;

	public PubXtglYhb(YHB yhb) {
		this.phone = yhb.getPhone();
		this.yhbh = Long.valueOf(yhb.getYhbh());
		this.yhbm = yhb.getYhbm();
		this.yhdm = yhb.getYhdm();
		this.yhkl = yhb.getYhkl();
		this.yhmc = yhb.getYhmc();
		this.yhsf = yhb.getYhsf();


	}

	public Long getYhbh() {
		return this.yhbh;
	}

	public void setYhbh(Long yhbh) {
		this.yhbh = yhbh;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getYhbm() {
		return this.yhbm;
	}

	public void setYhbm(String yhbm) {
		this.yhbm = yhbm;
	}

	public String getYhdm() {
		return this.yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getYhkl() {
		return this.yhkl;
	}

	public void setYhkl(String yhkl) {
		this.yhkl = yhkl;
	}

	public String getYhmc() {
		return this.yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getYhsf() {
		return this.yhsf;
	}

	public void setYhsf(String yhsf) {
		this.yhsf = yhsf;
	}

	public Long getFybh() {
		return this.fybh;
	}

	public void setFybh(Long fybh) {
		this.fybh = fybh;
	}

}
