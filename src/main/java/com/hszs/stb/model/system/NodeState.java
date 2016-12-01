package com.hszs.stb.model.system;

/**
 * 树节点的状态
 * @author Administrator
 *
 */
public class NodeState {

	private Boolean checked;
	private Boolean disabled;
	private Boolean expanded;
	private Boolean selected;
	
	public NodeState(Boolean checked,Boolean disabled,Boolean expanded,Boolean selected){
		this.checked = checked;
		this.disabled = disabled;
		this.expanded = expanded;
		this.selected = selected;
	}
	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	
}
