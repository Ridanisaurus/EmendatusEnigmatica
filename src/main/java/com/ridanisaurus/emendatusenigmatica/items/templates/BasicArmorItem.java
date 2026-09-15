/*
 * MIT License
 *
 * Copyright (c) 2020-2026. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.items.templates;

import com.ridanisaurus.emendatusenigmatica.datagen.gen.LangGen;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.ArmorModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.ArmorPieceModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.EffectModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.IColorable;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.RomanNumberHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.jarjar.nio.util.Lazy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BasicArmorItem extends ArmorItem implements IColorable {
    /**
     * Indexes:
     * <ul>
     * <li>0 -> Helmet</li>
     * <li>1 -> Chestplate</li>
     * <li>2 -> Leggings</li>
     * <li>3 -> Boots</li>
     * </ul>
     */
    private final Lazy<ItemStack[]> lazySetPieces;
    private final ArmorModel properties;
    private final boolean hasColor;
    private final int highlight1;
    private final int highlight2;
    private final int highlight3;
    private final int shadow1;
    private final int shadow2;
    private final int base;

    public BasicArmorItem(@NotNull MaterialModel material, Type type, @NotNull ArmorPieceModel armor) {
        super(EERegistrar.armorMaterialsMap.get(material.getId()), type, new Properties().durability(armor.getDurability()));
        String id = material.getId();
        this.lazySetPieces = Lazy.of(() -> new ItemStack[] {
            new ItemStack(EERegistrar.helmetMap.getValue(id)),
            new ItemStack(EERegistrar.chestplateMap.getValue(id)),
            new ItemStack(EERegistrar.leggingsMap.getValue(id)),
            new ItemStack(EERegistrar.bootsMap.getValue(id))
        });
        this.properties = material.getArmor();
        this.hasColor = material.getColors().hasMaterialColor();
        this.highlight1 = material.getColors().getHighlightColor(1);
        this.highlight2 = material.getColors().getHighlightColor(2);
        this.highlight3 = material.getColors().getHighlightColor(3);
        this.shadow1 = material.getColors().getShadowColor(1);
        this.shadow2 = material.getColors().getShadowColor(2);
        this.base = material.getColors().getMaterialColor();
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (!properties.isSetArmor() || !(slotId >= Inventory.INVENTORY_SIZE && slotId < Inventory.INVENTORY_SIZE + 4 && entity instanceof Player player) || level.getGameTime() % 20 != 0) return;
        if (isSetActive(player)) {
            for (EffectModel effect : properties.getEffects()) {
                player.addEffect(new MobEffectInstance(effect.getEffect(), 600, effect.getLevel(), true, effect.isShowParticles(), effect.isShowIcon()));
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, components, tooltipFlag);
        Player player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null && properties.isSetArmor()) {
            ItemStack[] setPieces = lazySetPieces.get();
            //TODO: Add possibility to translate this.
            components.add(Component
                .literal(this.properties.getSetName()).append(" (" + getPiecesEquipped(player) + "/" + setPieces.length + ") ")
                .withStyle(ChatFormatting.AQUA)
            );

            if (!tooltipFlag.hasShiftDown()) {
                components.add(LangGen.get("tooltip", "press_shift").withStyle(ChatFormatting.DARK_GRAY));
                return;
            }

            components.add(getPieceComponent(setPieces[0], player, EquipmentSlot.HEAD));
            components.add(getPieceComponent(setPieces[1], player, EquipmentSlot.CHEST));
            components.add(getPieceComponent(setPieces[2], player, EquipmentSlot.LEGS));
            components.add(getPieceComponent(setPieces[3], player, EquipmentSlot.FEET));
            components.add(Component.literal(" "));
            //TODO: Add possibility to translate this.
            components.add(Component.literal(this.properties.getSetDesc()).withStyle(isSetActive(player) ? ChatFormatting.GOLD : ChatFormatting.DARK_GRAY));
            components.add(Component.literal(" "));
            components.add(LangGen.get("tooltip", "armor_effect").withStyle(ChatFormatting.GRAY));
            for (EffectModel effect : properties.getEffects()) {
                components.add(Component.literal(
                    "- " +
                    effect.getEffect().value().getDisplayName().getString() +
                    (effect.getLevel() + 1 > 1? " " + RomanNumberHelper.toRoman(effect.getLevel() + 1): "")
                ).withStyle(isSetActive(player) ? ChatFormatting.BLUE : ChatFormatting.DARK_GRAY));
            }
        }
    }

    public boolean isSetActive(@NotNull Player player) {
        ItemStack[] setPieces = lazySetPieces.get();
        return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == setPieces[0].getItem() &&
            player.getItemBySlot(EquipmentSlot.CHEST).getItem() == setPieces[1].getItem() &&
            player.getItemBySlot(EquipmentSlot.LEGS).getItem() == setPieces[2].getItem() &&
            player.getItemBySlot(EquipmentSlot.FEET).getItem() == setPieces[3].getItem();
    }

    public boolean hasSetPiece(Player player, @NotNull EquipmentSlot slot) {
        ItemStack[] setPieces = lazySetPieces.get();
        return switch (slot) {
            case HEAD -> player.getItemBySlot(slot).getItem() == setPieces[0].getItem();
            case CHEST -> player.getItemBySlot(slot).getItem() == setPieces[1].getItem();
            case LEGS -> player.getItemBySlot(slot).getItem() == setPieces[2].getItem();
            case FEET -> player.getItemBySlot(slot).getItem() == setPieces[3].getItem();
            default -> false;
        };
    }

    private Component getPieceComponent(ItemStack stack, Player player, EquipmentSlot slot) {
        return stack.getHoverName().plainCopy().withStyle(hasSetPiece(player, slot)? ChatFormatting.GOLD: ChatFormatting.DARK_GRAY);
    }

    private int getPiecesEquipped(Player player) {
        int armorPieces = 0;
        EquipmentSlot[] values = EquipmentSlot.values();
        for (EquipmentSlot slot : values)
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && hasSetPiece(player, slot)) armorPieces++;

        return armorPieces;
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(@NotNull ItemStack stack, @NotNull Entity entity, @NotNull EquipmentSlot slot, ArmorMaterial.@NotNull Layer layer, boolean innerModel) {
        if (this.base > -1) {
            return super.getArmorTexture(stack, entity, slot, layer, innerModel);
        } else {
            return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/armor/empty.png");
        }
    }

    public boolean hasColor() {
        return this.hasColor;
    }

    public int getColorForIndex(int index){
        return switch (index) {
            case 0 -> highlight2;
            case 1 -> highlight1;
            case 3 -> shadow1;
            case 4 -> shadow2;
            default -> base;
        };
    }

    @Override
    public int getHighlight2() {
        // Intentional. Should return getHighlightColor(3).
        return highlight3;
    }

    @Override
    public int getHighlight1() {
        return highlight1;
    }

    @Override
    public int getBase() {
        return base;
    }

    @Override
    public int getShadow1() {
        return shadow1;
    }

    @Override
    public int getShadow2() {
        return shadow2;
    }
}