package me.jumpi.listeners;

import me.jumpi.bosses.BossType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

import static me.jumpi.bosses.BossType.bossKey;

public class BossListener implements Listener {

    private final static Random random = new Random();

    @EventHandler
    public void bossDeathEvent(EntityDeathEvent entityDeathEvent){

        if (!entityDeathEvent.getEntity().getPersistentDataContainer().getKeys().contains(bossKey))
            return;

        String internalBossName = entityDeathEvent.getEntity().getPersistentDataContainer().get(bossKey, PersistentDataType.STRING);

        entityDeathEvent.getDrops().clear();

        Random random = new Random();
        int drops = random.nextInt(1, 50);

        for (int i = 0; i < drops; i++) {
            ItemStack item = BossType.getBoss(internalBossName).getBoss().getRandomDrop();
            entityDeathEvent.getDrops().add(item);
        }

    }


}
