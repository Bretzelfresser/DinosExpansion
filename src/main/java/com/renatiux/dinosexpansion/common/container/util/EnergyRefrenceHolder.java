package com.renatiux.dinosexpansion.common.container.util;

import net.minecraft.util.IntReferenceHolder;

public abstract class EnergyRefrenceHolder extends IntReferenceHolder{

	protected int lastKnownValue = -1;
	
	@Override
	public boolean isDirty() {
		  int i = this.get();
	      boolean flag = i != this.lastKnownValue;
	      this.lastKnownValue = i;
	      return flag;
	}

}
