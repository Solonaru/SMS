package com.project.sms.entities.employee;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

	public Optional<Permission> findPermissionById(int permissionId);

	public List<Permission> findAllPermissions();

	public void insertPermission(Permission permission);

	public void updatePermission(Permission permission);

	public void deletePermissionById(int permissionId);
}
