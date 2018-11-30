package com.project.sms.entities.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentService implements IComponentService{
	
	@Autowired
	private IComponentRepository componentRepository;

	public Optional<Component> findComponentById(int componentId) {
		return componentRepository.findById(componentId);
	}

	public List<Component> findAllComponents() {
		return (List<Component>) componentRepository.findAll();
	}

	public void insertComponent(Component component) {
		componentRepository.save(component);
	}

	public void updateComponent(Component component) {
		componentRepository.save(component);
	}

	public void deleteComponentById(int componentId) {
		componentRepository.deleteById(componentId);
	}
}
