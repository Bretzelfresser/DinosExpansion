package com.renatiux.dinosexpansion.world;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tribes.Tribe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TribeSaveData extends WorldSavedData {
    public static final String NAME = Dinosexpansion.MODID + ".tribes";

    private final Map<String, Tribe> tribes = new HashMap<>();

    public static TribeSaveData fromBuffer(PacketBuffer buffer){
        TribeSaveData data = new TribeSaveData();
        data.read(buffer.readCompoundTag());
        return data;
    }

    public TribeSaveData() {
        super(NAME);
    }

    @Nullable
    public Tribe getTribeWithName(String name){
        return tribes.get(name);
    }

    public boolean hasTribe(Tribe t){
        return hasTribe(t.getName());
    }

    public boolean hasTribe(String name){
        return this.tribes.containsKey(name);
    }

    public int getSize(){
        return tribes.size();
    }

    public void remove(Tribe t){
        this.tribes.remove(t.getName());
    }

    public Collection<Tribe> getTribes(){
        return this.tribes.values();
    }

    @Override
    public void read(CompoundNBT nbt) {
        for (int i = 0; nbt.contains("tribe" + i); i++) {
            Tribe t = Tribe.fromNBT(nbt.getCompound("tribe" + i));
            tribes.put(t.getName(), t);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        int i = 0;
        for (Tribe t : tribes.values()) {
            compound.put("tribe" + i, t.save());
            i++;
        }
        return compound;
    }


    public void write(PacketBuffer buffer){
       buffer.writeCompoundTag(write(new CompoundNBT()));
    }

    public static void addTribe(Tribe toAdd, ServerWorld world){
        TribeSaveData data = world.getSavedData().getOrCreate(TribeSaveData::new, NAME);
        data.tribes.put(toAdd.getName(), toAdd);
        data.markDirty();
    }
    public static TribeSaveData getData(ServerWorld world){
        return world.getSavedData().getOrCreate(TribeSaveData::new, NAME);
    }
}
