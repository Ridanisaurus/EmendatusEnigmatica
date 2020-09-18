package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum Ores {

    COAL("coal", "Coal", OreCoal::new, ItemHandler.CHUNK_COAL, drop(() -> Items.COAL), 17, 20, 0, 128),
    IRON("iron", "Iron", OreIron::new, ItemHandler.CHUNK_IRON, 9, 20, 0, 64),
    GOLD("gold", "Gold", OreGold::new, ItemHandler.CHUNK_GOLD, 9, 2, 0, 32),
    DIAMOND("diamond", "Diamond", OreDiamond::new, ItemHandler.CHUNK_DIAMOND, drop(() -> Items.DIAMOND), 8, 1, 0, 16),
    EMERALD("emerald", "Emerald", OreEmerald::new, ItemHandler.CHUNK_EMERALD, drop(() -> Items.EMERALD), 4, 1, 64, 128),
    LAPIS("lapis", "Lapis Lazuli", OreLapis::new, ItemHandler.CHUNK_LAPIS, drop(() -> Items.LAPIS_LAZULI, 4, 9), 7, 2, 0, 16),
    REDSTONE("redstone", "Redstone", OreRedstone::new, ItemHandler.CHUNK_REDSTONE, drop(() -> Items.REDSTONE, 4, 5), 8, 8, 0, 16),

    COPPER("copper", "Copper", OreCopper::new, ItemHandler.CHUNK_COPPER, 7, 20, 45, 60),
    ALUMINUM("aluminum", "Aluminum", OreAluminum::new, ItemHandler.CHUNK_ALUMINUM, 3, 8, 50, 70), // rid pls, it's ALUMINIUM! >:(
    SILVER("silver", "Silver", OreSilver::new, ItemHandler.CHUNK_SILVER, 4, 8, 30, 38),
    LEAD("lead", "Lead", OreLead::new, ItemHandler.CHUNK_LEAD, 3, 8, 32, 40),
    NICKEL("nickel", "Nickel", OreNickel::new, ItemHandler.CHUNK_NICKEL, 3, 8, 25, 40),
    URANIUM("uranium", "Uranium", OreUranium::new, ItemHandler.CHUNK_URANIUM, 3, 4, 5, 20),
    OSMIUM("osmium", "Osmium", OreOsmium::new, ItemHandler.CHUNK_OSMIUM, 6, 20, 20, 45),
    TIN("tin", "Tin", OreTin::new, ItemHandler.CHUNK_TIN, 6, 20, 40, 55),
    ZINC("zinc", "Zinc", OreZinc::new, ItemHandler.CHUNK_ZINC, 4, 8, 35, 50),

    CERTUS_QUARTZ("certus_quartz", "Certus Quartz", OreCertusQuartz::new, ItemHandler.CHUNK_CERTUS_QUARTZ, drop(ItemHandler.GEM_CERTUS_QUARTZ), 8, 16, 0, 64),
    CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", OreChargedCertusQuartz::new, ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ, drop(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ), 4, 8, 0, 32),
    FLUORITE("fluorite", "Fluorite", OreFluorite::new, ItemHandler.CHUNK_FLUORITE, drop(ItemHandler.GEM_FLUORITE, 2, 4), 12, 6, 0, 32),
    ;

    public final String id;
    public final Supplier<OreBlock> block;
    public final String localisedName;

    public final RegistryObject<Item> chunk;
    public final OreDropInfo drop;

    public final int defaultSize;
    public final int defaultCount;
    public final int defaultMinY;
    public final int defaultMaxY;

    Ores(String id, String localisedName, Supplier<OreBlock> block, RegistryObject<Item> silkDrop, @Nullable OreDropInfo nonSilkDrop, int s, int c, int minY, int maxY) {
        this.id = id;
        this.block = block;

        this.chunk = silkDrop;
        this.drop = nonSilkDrop;

        this.defaultSize = s;
        this.defaultCount = c;
        this.defaultMinY = minY;
        this.defaultMaxY = maxY;
        this.localisedName = localisedName;
    }

    Ores(String id, String localisedName, Supplier<OreBlock> block, RegistryObject<Item> silkDrop, int s, int c, int minY, int maxY) {
        this(id, localisedName, block, silkDrop, null, s, c, minY, maxY);
    }

    static OreDropInfo drop(Supplier<Item> item) {
        return drop(item, 1, 1);
    }

    static OreDropInfo drop(Supplier<Item> item, float min, float max) {
        return new OreDropInfo(item, min, max);
    }

    public static class OreDropInfo {
        public final Supplier<Item> item;
        public final float min;
        public final float max;

        public OreDropInfo(Supplier<Item> item, float min, float max) {
            this.item = item;
            this.min = min;
            this.max = max;
        }
    }
}
