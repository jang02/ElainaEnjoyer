package me.jumpi.bosses;

import lombok.Getter;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public enum BossType {
    ZOMBIE_BOSS( "ZOMBIE", new ZombieBoss("tyfus zombie", 200));

    @Getter
    private final BossBase boss;
    @Getter
    private final String internalName;

    private static Map<String, BossType> bossMap = new HashMap<>();
    public static NamespacedKey bossKey = new NamespacedKey("elainaenjoyer", "boss");

    BossType(String internalName, BossBase boss) {
        this.internalName = internalName;
        this.boss = boss;
    }

    public static BossType getBoss(String name){
        return bossMap.get(name.toUpperCase());
    }

    static {
        for (BossType boss : BossType.values()) {
            bossMap.put(boss.internalName, boss);
        }
    }
}
