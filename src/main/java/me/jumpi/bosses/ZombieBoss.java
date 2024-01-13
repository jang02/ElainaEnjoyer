package me.jumpi.bosses;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class ZombieBoss extends BossBase {

    public ZombieBoss(String name, double health) {
        super(name, health, EntityType.ZOMBIE);

        this.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 15);
        this.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED, 5);
        this.registerAttribute(Attribute.GENERIC_MAX_HEALTH, health);

        this.registerDrop(new ItemStack(Material.DIAMOND, 1), 50);
        this.registerDrop(new ItemStack(Material.PAPER, 1), 30);
        this.registerDrop(new ItemStack(Material.ANVIL, 1), 10);
        this.registerDrop(new ItemStack(Material.BOW, 1), 1);
    }

    @Override
    public void spawn(Location location){
        super.spawn(location, (livingEntity -> {
            Zombie zombie = (Zombie) livingEntity;
            zombie.setShouldBurnInDay(false);
        }));
    }

    @Override
    public BossType getBossType() {
        return BossType.ZOMBIE_BOSS;
    }


}
