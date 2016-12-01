package com.hszs.stb.model.auth;

/**
 * 权限菜单表
 * @author du
 *
 */
public class Authority {

	private Integer id;
	private Integer authorityid;
	private String name;
	private boolean enable;
	private String url;
	private String matchUrl;
	private String itemIcon;
	private String levelCode;
	private int position;
	private String theValue;
	private Integer parentId;
	
	public Integer getAuthorityid() {
		return authorityid;
	}
	public void setAuthorityid(Integer authorityid) {
		this.authorityid = authorityid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMatchUrl() {
		return matchUrl;
	}
	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}
	public String getItemIcon() {
		return itemIcon;
	}
	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getTheValue() {
		return theValue;
	}
	public void setTheValue(String theValue) {
		this.theValue = theValue;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
