package me.jumpi.bosses;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static me.jumpi.bosses.BossType.bossKey;

public abstract class BossBase {

    public String name;
    @Getter @Setter
    public double health;
    public EntityType entity;
    public Map<Attribute, Double> attributes;
    public Map<ItemStack, Double> dropList;


    public BossBase(String name, double health, EntityType entity) {
        this.name = name;
        this.health = health;
        this.entity = entity;

        this.attributes = new HashMap<>();
        this.dropList = new HashMap<>();
    }
    public void registerAttribute(Attribute attribute, double value){
        this.attributes.put(attribute, value);
    }
    public void registerDrop(ItemStack itemStack, double weight){
        this.dropList.put(itemStack, weight);
    }

    public void spawn(Location location, Consumer<LivingEntity> consumer){
        location.getWorld().spawnEntity(location, this.entity, CreatureSpawnEvent.SpawnReason.COMMAND, (spawnableEntity) -> {
            spawnableEntity.customName(Component.text(this.name));
            spawnableEntity.setCustomNameVisible(true);

            if (spawnableEntity instanceof LivingEntity livingEntity){

                this.attributes.forEach((attribute, value) ->{
                    livingEntity.getAttribute(attribute).setBaseValue(value);
                });

                livingEntity.getPersistentDataContainer().set(bossKey, PersistentDataType.STRING, this.getBossType().getInternalName());

                livingEntity.setHealth(this.health);

                consumer.accept(livingEntity);
            }
        });
    }

    public void spawn(Location location){
        this.spawn(location, (livingEntity) -> {});
    }

    public abstract BossType getBossType();

    public ItemStack getRandomDrop(){
        double maxWeight = 0.0;

        for (Map.Entry<ItemStack, Double> drop : this.dropList.entrySet()) {
            maxWeight += drop.getValue();
        }

        double randomValue = Math.random() * maxWeight;

        for (Map.Entry<ItemStack, Double> bossDrop : this.dropList.entrySet()) {
            randomValue -= bossDrop.getValue();
            if (randomValue <= 0.0){
                return bossDrop.getKey();
            }
        }

        return null;
    }

}
