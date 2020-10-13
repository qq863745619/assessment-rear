package com.nju.software.assessment.bean;

import javax.persistence.*;
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
 * @Author  dyd
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
@Table ( name ="user_authority_record" )
public class UserAuthorityRecord  implements Serializable {
	private static final long serialVersionUID =  6703262208115314414L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)//设置自增  插入数据时，此属性可空
	@Id
   	@Column(name = "yhqxbh" )
	private Long yhqxbh;

   	@Column(name = "yhbh" )
	private Long yhbh;

   	@Column(name = "qxbh" )
	private Long qxbh;

   	@Column(name="bmbh")
	private String bmbh;

   	public String  getBmbh() { return this.bmbh; }

   	public void setBmbh(String bmbh) { this.bmbh = bmbh;}

	public Long getYhqxbh() {
		return this.yhqxbh;
	}

	public void setYhqxbh(Long yhqxbh) {
		this.yhqxbh = yhqxbh;
	}

	public Long getYhbh() {
		return this.yhbh;
	}

	public void setYhbh(Long yhbh) {
		this.yhbh = yhbh;
	}

	public Long getQxbh() {
		return this.qxbh;
	}

	public void setQxbh(Long qxbh) {
		this.qxbh = qxbh;
	}

}
