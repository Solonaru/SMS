package com.project.msd.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService{

	@Autowired
	private IPermissionRepository permissionRepository;

	public Optional<Permission> findPermissionById(int permissionId) {
		return permissionRepository.findById(permissionId);
	}

	public List<Permission> findAllPermissions() {
		return (List<Permission>) permissionRepository.findAll();
	}

	public void insertPermission(Permission permission) {
		permissionRepository.save(permission);
	}

	public void updatePermission(Permission permission) {
		permissionRepository.save(permission);
	}

	public void deletePermissionById(int permissionId) {
		permissionRepository.deleteById(permissionId);
	}
}
