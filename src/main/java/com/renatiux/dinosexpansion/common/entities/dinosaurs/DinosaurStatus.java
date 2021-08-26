package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import java.util.HashMap;
import java.util.Map;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public enum DinosaurStatus {
	
	IDLE(0, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".idle")),
	SITTING(1, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".sit")),
	HOSTILE(2, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".hostile")),
	PROTECTION(3, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".protecting")),
	SLEEPING(4, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".sleep")),
	WANDER(5, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".wander")),
	FOLLOW(6, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".follow")),
	BREED(7, new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".breed"));
	
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
	private ITextComponent text;
	DinosaurStatus(int id, ITextComponent text) {
		this.id = id;
		add(id);
		this.text = text;
	}
	
	private void add(int id) {
		getValues().put(id, this);
	}
	
	public ITextComponent getTextComponent() {
		return text;
	}
	
	public int getID() {
		return id;
	}

}
