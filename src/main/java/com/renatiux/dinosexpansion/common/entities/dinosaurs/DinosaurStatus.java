package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import java.util.HashMap;
import java.util.Map;

public enum DinosaurStatus {
	
	IDLE(0),
	SITTING(1),
	HOSTILE(2),
	PROTECTION(3),
	SLEEPING(4),
	WANDER(5),
	FOLLOW(6),
	BREED(7);
	
	private static Map<Integer, DinosaurStatus> values;
	
	private static Map<Integer, DinosaurStatus> getValues(){
		if(values == null) {
			values = new HashMap<>();
			return values;
		}else
			return values;
	}
	
	public static DinosaurStatus getStatus(int id) {
		if(!values.containsKey(id))
			throw new IllegalArgumentException("invlid id for DinsaurStatus " + id);
		return values.get(id);
	}
	
	private int id;
	DinosaurStatus(int id) {
		this.id = id;
		add(id);
	}
	
	private void add(int id) {
		getValues().put(id, this);
	}
	
	public int getID() {
		return id;
	}

}
