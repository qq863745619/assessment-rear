package com.nju.software.assessment.bean;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class MdId implements Serializable {
    private Integer mbid;
    private Integer yhbh;

    public void setMbid(Integer mbid) {
        this.mbid = mbid;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public MdId() {
    }

    public MdId(Integer mbid, Integer yhbh) {
        this.mbid = mbid;
        this.yhbh = yhbh;
    }

    @Override
    public String toString() {
        return "MdId{" +
                "mbid=" + mbid +
                ", yhbh=" + yhbh +
                '}';
    }
}
