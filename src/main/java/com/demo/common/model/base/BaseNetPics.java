package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseNetPics<M extends BaseNetPics<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setStarname(java.lang.String starname) {
		set("starname", starname);
	}
	
	public java.lang.String getStarname() {
		return getStr("starname");
	}

	public void setPicurl(java.lang.String picurl) {
		set("picurl", picurl);
	}
	
	public java.lang.String getPicurl() {
		return getStr("picurl");
	}

	public void setBase64(java.lang.String base64) {
		set("base64", base64);
	}
	
	public java.lang.String getBase64() {
		return getStr("base64");
	}

	/**
	 * 性别,0-女，1-男
	 */
	public void setGender(java.lang.Integer gender) {
		set("gender", gender);
	}
	
	/**
	 * 性别,0-女，1-男
	 */
	public java.lang.Integer getGender() {
		return getInt("gender");
	}

	public void setBeautifulpoint(java.lang.Integer beautifulpoint) {
		set("beautifulpoint", beautifulpoint);
	}
	
	public java.lang.Integer getBeautifulpoint() {
		return getInt("beautifulpoint");
	}

	public void setBeauty(java.lang.String beauty) {
		set("beauty", beauty);
	}
	
	public java.lang.String getBeauty() {
		return getStr("beauty");
	}

	public void setState(java.lang.Boolean state) {
		set("state", state);
	}
	
	public java.lang.Boolean getState() {
		return get("state");
	}

	public void setCreatetime(java.util.Date createtime) {
		set("createtime", createtime);
	}
	
	public java.util.Date getCreatetime() {
		return get("createtime");
	}

}
