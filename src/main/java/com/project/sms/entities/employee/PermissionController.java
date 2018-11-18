package com.project.sms.entities.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	private IPermissionService permissionService;

	@RequestMapping(value = "/{permissionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Permission> findPermissionById(@PathVariable("permissionId") int permissionId) {
		return permissionService.findPermissionById(permissionId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Permission> getPermissions() {
		return permissionService.findAllPermissions();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertPermission(@RequestBody Permission permission) {
		permissionService.insertPermission(permission);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePermission(@RequestBody Permission permission) {
		permissionService.updatePermission(permission);
	}

	@RequestMapping(value = "/delete/{permissionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletePermission(@PathVariable("permissionId") int permissionId) {
		permissionService.deletePermissionById(permissionId);
	}
}