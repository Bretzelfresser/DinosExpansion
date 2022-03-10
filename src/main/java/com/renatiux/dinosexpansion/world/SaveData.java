package com.renatiux.dinosexpansion.world;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.util.interfaces.SaveDataProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SaveData extends WorldSavedData {
    public static final String NAME = Dinosexpansion.MODID + ".save_data";

    private final List<SaveDataProvider<?>> data = new ArrayList<>();

    public SaveData(String p_i2141_1_) {
        super(p_i2141_1_);
    }

    public SaveData() {
        super(NAME);
    }

    public List<SaveDataProvider<?>> getData() {
        return data;
    }

    @Override
    public void read(CompoundNBT nbt) {
        CompoundNBT data = nbt.getCompound("saveData");
        for (int i = 0; data.contains("data" + i, 10); i++) {
            CompoundNBT indexNbt = data.getCompound("data" + i);
            try {
                Class clazz = loadClass(indexNbt);
                Constructor constr = clazz.getConstructor();
                SaveDataProvider<?> provider = (SaveDataProvider<?>) constr.newInstance();
                this.data.add(provider.load(indexNbt));
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private CompoundNBT writeClass(SaveDataProvider<?> data, CompoundNBT nbt) {
        nbt.putString("class", data.getClass().getName());
        return nbt;
    }

    private Class<?> loadClass(CompoundNBT nbt) throws ClassNotFoundException {
        if (!nbt.contains("class")) {
            throw new IllegalStateException("it should always save the class with it");
        }
        return Class.forName(nbt.getString("class"));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        CompoundNBT savedData = new CompoundNBT();
        for (int i = 0; i < data.size(); i++) {
            savedData.put("data" + i, writeClass(data.get(i), data.get(i).save()));
        }

        nbt.put("saveData", savedData);
        return nbt;
    }

    /**
     * keep im mind that all save Data added here has to have an empty constructor, it doesnt matter what values this constructor fills in the object, its just so the load method can be executed
     *
     * @param data  - the data that will be stored
     * @param world
     * @param <T>
     */
    public static <T extends SaveDataProvider<T>> void putData(SaveDataProvider<T> data, ServerWorld world) {
        SaveData savedData = world.getSavedData().getOrCreate(SaveData::new, NAME);
        savedData.data.add(data);
        savedData.markDirty();
    }

    public static SaveData getData(ServerWorld world){
        return world.getSavedData().getOrCreate(SaveData::new, NAME);
    }
}
