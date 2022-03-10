package com.renatiux.dinosexpansion.world;

import com.renatiux.dinosexpansion.util.interfaces.SaveDataProvider;
import net.minecraft.nbt.CompoundNBT;

public class ExampleData implements SaveDataProvider<ExampleData> {
    private final String name;

    public ExampleData(String name){
        this.name = name;
    }
    public ExampleData(){
        name = null;
    }

    @Override
    public CompoundNBT save() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("name", this.name);
        return nbt;
    }

    @Override
    public ExampleData load(CompoundNBT nbt) {
        System.out.println("Example Data loaded");
        return new ExampleData(nbt.getString("name"));
    }

    public String getName() {
        return name;
    }
}
