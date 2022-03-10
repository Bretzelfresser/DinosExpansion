package com.renatiux.dinosexpansion.util.interfaces;

import net.minecraft.nbt.CompoundNBT;

public interface SaveDataProvider<T extends SaveDataProvider<T>> {
    /**
     * saves the data to the worldSaveData
     * @return the compound data
     */
    CompoundNBT save();

    /**
     *
     * @param nbt the nbt provided in save
     * @return - a freshly baked SaveDataProvider that then can be stored again
     */
     T load(CompoundNBT nbt);
}
