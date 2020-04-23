package cn.smt666.jt.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 前台为了可以支持Feign，对于Date类型的日期数据而言，还是的先将数据转换成String字符串类型才可以传递。
 * 因为Feign在将Date互相转换String类型时会发生问题。Feign传递的数据就必须是String类型的字符串。
 * @author 27140
 *
 */
public class BasePojo implements Serializable {

	private static final long serialVersionUID = -7842672633056875979L;
	
	private Date created;
	private Date updated;
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
