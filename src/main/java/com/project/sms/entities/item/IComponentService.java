package com.project.sms.entities.item;

import java.util.List;
import java.util.Optional;

public interface IComponentService {
	
	public Optional<Component> findComponentById(int componentId);

	public List<Component> findAllComponents();

	public void insertComponent(Component component);

	public void updateComponent(Component component);

	public void deleteComponentById(int componentId);
}
