package com.renatiux.dinosexpansion.common.tribes;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.*;

public class Tribe {

    public static final String TRIBE_SAVEDATA_PLAYER = Dinosexpansion.MODID + ".tribe";

    /**
     * should only be called when the nbt provided here is gain from {@link Tribe#save}
     *
     * @param nbt
     * @return a new Tribe created from the nbt data
     */
    public static Tribe fromNBT(CompoundNBT nbt) {
        Map<UUID, TribeData> players = new HashMap();
        for (int i = 0; nbt.contains("player" + i); i++) {
            UUID player = nbt.getUniqueId("player" + i);
            players.put(player, TribeData.load(nbt.getCompound("data" + i)));
        }
        TribeRole required = TribeRole.valueOf(nbt.getString("neededToAdd"));
        Tribe t = new Tribe(nbt.getString("name"), players);
        t.setRequiredRoleToAddOtherPlayers(required);
        return t;
    }

    private final String name;
    private Map<UUID, TribeData> players;
    private boolean shouldExist = true;
    private TribeRole neededToAdd = TribeRole.MEMBER;

    private Tribe(String name, Map<UUID, TribeData> players) {
        this.name = name;
        this.players = players;
        checkLeader();
    }

    public Tribe(String name, PlayerEntity leader) {
        this.name = name;
        this.players = new HashMap<>();
        players.put(leader.getUniqueID(), new TribeData(TribeRole.LEADER));
        leader.getPersistentData().putString(TRIBE_SAVEDATA_PLAYER, this.name);
    }

    /**
     * the normal way is so everyone in ur tribe can add others, u can change it here
     *
     * @param role - the role that is at least required to add other people
     */
    public void setRequiredRoleToAddOtherPlayers(TribeRole role) {
        this.neededToAdd = role;
    }

    public boolean hasMember(PlayerEntity player) {
        return players.containsKey(player.getUniqueID());
    }

    @Nullable
    public TribeRole getRole(PlayerEntity player) {
        TribeData data = players.get(player.getUniqueID());
        if (data != null) {
            return data.getRole();
        }
        return null;
    }

    public boolean addPlayer(PlayerEntity player, PlayerEntity adder) {
        return addPlayer(player, adder, TribeRole.MEMBER);
    }

    public boolean addPlayer(PlayerEntity player, PlayerEntity adder, TribeRole role) {
        TribeRole adderRole = getRole(adder);
        if (adderRole != null && adderRole.hasMoreOrEqualRights(this.neededToAdd) && (this.getSize() < DEModConfig.TRIBE_CONFIG.playerPerTribe.get() || DEModConfig.TRIBE_CONFIG.playerPerTribe.get() == 0)) {
            player.getPersistentData().putString(TRIBE_SAVEDATA_PLAYER, this.name);
            this.players.put(player.getUniqueID(), new TribeData(role));
            return true;
        }
        return false;
    }

    public int getSize() {
        return players.size();
    }

    public boolean removePlayer(PlayerEntity remover, PlayerEntity toRemove) {
        if (remover == toRemove) {
            return remove(toRemove);
        }
        TribeRole removerRole = getRole(remover);
        if (hasMember(toRemove)) {
            if (removerRole != null && removerRole.hasMoreRights(getRole(toRemove))) {
                return remove(toRemove);
            }
        }
        return false;
    }

    private boolean remove(PlayerEntity toRemove) {
        return players.remove(toRemove.getUniqueID()) != null;
    }

    public CompoundNBT save() {
        return save(new CompoundNBT());
    }

    public CompoundNBT save(CompoundNBT nbt) {
        int i = 0;
        for (UUID player : players.keySet()) {
            CompoundNBT playerData = new CompoundNBT();
            playerData.putUniqueId("player" + i, player);
            nbt.put("data" + i, players.get(player).save(playerData));
        }
        nbt.putString("neededToAdd", this.neededToAdd.name());
        nbt.putString("name", this.name);
        return nbt;
    }

    public String getName() {
        return name;
    }

    private void checkLeader() {
        TribeRole toCkeck = TribeRole.LEADER;
        if (players.size() == 0)
            throw new IllegalStateException("tribe mustn have zero Players");
        for (UUID player : players.keySet()) {
            if (players.get(player).getRole() == toCkeck)
                return;
        }
        while (true) {
            for (UUID player : players.keySet()) {
                if (players.get(player).getRole() == toCkeck) {
                    players.get(player).setRole(TribeRole.LEADER);
                    return;
                }
            }
            toCkeck = toCkeck.demote();
        }


    }

    private static class TribeData {
        public static TribeData load(CompoundNBT nbt) {
            TribeRole role = TribeRole.valueOf(nbt.getString("role"));
            return new TribeData(role);
        }


        private TribeRole role;

        private TribeData(TribeRole role) {
            this.role = role;
        }

        public TribeRole getRole() {
            return role;
        }

        public void setRole(TribeRole role) {
            this.role = role;
        }

        public CompoundNBT save(CompoundNBT nbt) {
            nbt.putString("role", role.name());
            return nbt;
        }
    }

    public enum TribeRole {
        MEMBER("member", 0),
        OLDEST("oldest", 1),
        CO_LEADER("co_leader", 2),
        LEADER("leader", 3);

        private final String name;
        private final int prio;

        TribeRole(String shownName, int prio) {
            this.name = shownName;
            this.prio = prio;
        }

        public TribeRole promote() {
            switch (this) {
                case LEADER:
                case CO_LEADER:
                    return LEADER;
                case OLDEST:
                    return CO_LEADER;
                case MEMBER:
                    return OLDEST;
            }
            return MEMBER;
        }

        public TribeRole demote() {
            switch (this) {
                case LEADER:
                    return CO_LEADER;
                case CO_LEADER:
                    return OLDEST;
                case OLDEST:
                case MEMBER:
                    return MEMBER;
            }
            return MEMBER;
        }

        public boolean hasMoreRights(TribeRole role) {
            return this.prio < role.prio;
        }

        public boolean hasMoreOrEqualRights(TribeRole role) {
            return this.prio <= role.prio;
        }

        public TranslationTextComponent getTextComponent() {
            return new TranslationTextComponent("tribe_role." + Dinosexpansion.MODID + "." + this.name);
        }
    }
}
