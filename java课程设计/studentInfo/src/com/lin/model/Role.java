package com.lin.model;

public class Role {
	// ÿ��userid ��Ӧһ��roleid roleId(0ѧ�� 1��ʦ 2����Ա)
	private int roleId;
	private String roleName;
	private int userId;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
