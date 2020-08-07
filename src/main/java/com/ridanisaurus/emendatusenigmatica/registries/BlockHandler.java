/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.registries;

import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHandler {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

  //Blocks
  public static final RegistryObject<Block> BLOCK_COPPER = BLOCKS.register("block_copper", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_ALUMINUM = BLOCKS.register("block_aluminum", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_SILVER = BLOCKS.register("block_silver", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_LEAD = BLOCKS.register("block_lead", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_NICKEL = BLOCKS.register("block_nickel", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_URANIUM = BLOCKS.register("block_uranium", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_OSMIUM = BLOCKS.register("block_osmium", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_TIN = BLOCKS.register("block_tin", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_ZINC = BLOCKS.register("block_zinc", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_BRONZE = BLOCKS.register("block_bronze", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_BRASS = BLOCKS.register("block_brass", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_CONSTANTAN = BLOCKS.register("block_constantan", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_ELECTRUM = BLOCKS.register("block_electrum", BlockBase::new);
  public static final RegistryObject<Block> BLOCK_STEEL = BLOCKS.register("block_steel", BlockBase::new);

  //Ores
  public static final RegistryObject<Block> ORE_COAL = BLOCKS.register("ore_coal", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON = BLOCKS.register("ore_iron", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD = BLOCKS.register("ore_gold", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND = BLOCKS.register("ore_diamond", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD = BLOCKS.register("ore_emerald", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS = BLOCKS.register("ore_lapis", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE = BLOCKS.register("ore_redstone", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER = BLOCKS.register("ore_copper", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM = BLOCKS.register("ore_aluminum", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER = BLOCKS.register("ore_silver", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD = BLOCKS.register("ore_lead", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL = BLOCKS.register("ore_nickel", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM = BLOCKS.register("ore_uranium", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM = BLOCKS.register("ore_osmium", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN = BLOCKS.register("ore_tin", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC = BLOCKS.register("ore_zinc", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ = BLOCKS.register("ore_certus_quartz", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ = BLOCKS.register("ore_charged_certus_quartz", OreChargedCertusQuartz::new);

  //Strata Ores - Andesite
  public static final RegistryObject<Block> ORE_COAL_ANDESITE = BLOCKS.register("ore_coal_andesite", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_ANDESITE = BLOCKS.register("ore_iron_andesite", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_ANDESITE = BLOCKS.register("ore_gold_andesite", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_ANDESITE = BLOCKS.register("ore_diamond_andesite", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_ANDESITE = BLOCKS.register("ore_emerald_andesite", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_ANDESITE = BLOCKS.register("ore_lapis_andesite", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_ANDESITE = BLOCKS.register("ore_redstone_andesite", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_ANDESITE = BLOCKS.register("ore_copper_andesite", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_ANDESITE = BLOCKS.register("ore_aluminum_andesite", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_ANDESITE = BLOCKS.register("ore_silver_andesite", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_ANDESITE = BLOCKS.register("ore_lead_andesite", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_ANDESITE = BLOCKS.register("ore_nickel_andesite", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_ANDESITE = BLOCKS.register("ore_uranium_andesite", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_ANDESITE = BLOCKS.register("ore_osmium_andesite", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_ANDESITE = BLOCKS.register("ore_tin_andesite", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_ANDESITE = BLOCKS.register("ore_zinc_andesite", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_ANDESITE = BLOCKS.register("ore_certus_quartz_andesite", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_ANDESITE = BLOCKS.register("ore_charged_certus_quartz_andesite", OreChargedCertusQuartz::new);

  // Strata Ores - Gabbro
  public static final RegistryObject<Block> ORE_COAL_GABBRO = BLOCKS.register("ore_coal_gabbro", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_GABBRO = BLOCKS.register("ore_iron_gabbro", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_GABBRO = BLOCKS.register("ore_gold_gabbro", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_GABBRO = BLOCKS.register("ore_diamond_gabbro", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_GABBRO = BLOCKS.register("ore_emerald_gabbro", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_GABBRO = BLOCKS.register("ore_lapis_gabbro", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_GABBRO = BLOCKS.register("ore_redstone_gabbro", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_GABBRO = BLOCKS.register("ore_copper_gabbro", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_GABBRO = BLOCKS.register("ore_aluminum_gabbro", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_GABBRO = BLOCKS.register("ore_silver_gabbro", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_GABBRO = BLOCKS.register("ore_lead_gabbro", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_GABBRO = BLOCKS.register("ore_nickel_gabbro", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_GABBRO = BLOCKS.register("ore_uranium_gabbro", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_GABBRO = BLOCKS.register("ore_osmium_gabbro", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_GABBRO = BLOCKS.register("ore_tin_gabbro", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_GABBRO = BLOCKS.register("ore_zinc_gabbro", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_GABBRO = BLOCKS.register("ore_certus_quartz_gabbro", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_GABBRO = BLOCKS.register("ore_charged_certus_quartz_gabbro", OreChargedCertusQuartz::new);

  // Strata Ores - Limestone (Create)
  public static final RegistryObject<Block> ORE_COAL_C_LIMESTONE = BLOCKS.register("ore_coal_c_limestone", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_C_LIMESTONE = BLOCKS.register("ore_iron_c_limestone", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_C_LIMESTONE = BLOCKS.register("ore_gold_c_limestone", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_C_LIMESTONE = BLOCKS.register("ore_diamond_c_limestone", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_C_LIMESTONE = BLOCKS.register("ore_emerald_c_limestone", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_C_LIMESTONE = BLOCKS.register("ore_lapis_c_limestone", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_C_LIMESTONE = BLOCKS.register("ore_redstone_c_limestone", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_C_LIMESTONE = BLOCKS.register("ore_copper_c_limestone", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_C_LIMESTONE = BLOCKS.register("ore_aluminum_c_limestone", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_C_LIMESTONE = BLOCKS.register("ore_silver_c_limestone", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_C_LIMESTONE = BLOCKS.register("ore_lead_c_limestone", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_C_LIMESTONE = BLOCKS.register("ore_nickel_c_limestone", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_C_LIMESTONE = BLOCKS.register("ore_uranium_c_limestone", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_C_LIMESTONE = BLOCKS.register("ore_osmium_c_limestone", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_C_LIMESTONE = BLOCKS.register("ore_tin_c_limestone", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_C_LIMESTONE = BLOCKS.register("ore_zinc_c_limestone", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_C_LIMESTONE = BLOCKS.register("ore_certus_quartz_c_limestone", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_C_LIMESTONE = BLOCKS.register("ore_charged_certus_quartz_c_limestone", OreChargedCertusQuartz::new);

  // Strata Ores - Scoria
  public static final RegistryObject<Block> ORE_COAL_SCORIA = BLOCKS.register("ore_coal_scoria", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_SCORIA = BLOCKS.register("ore_iron_scoria", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_SCORIA = BLOCKS.register("ore_gold_scoria", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_SCORIA = BLOCKS.register("ore_diamond_scoria", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_SCORIA = BLOCKS.register("ore_emerald_scoria", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_SCORIA = BLOCKS.register("ore_lapis_scoria", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_SCORIA = BLOCKS.register("ore_redstone_scoria", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_SCORIA = BLOCKS.register("ore_copper_scoria", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_SCORIA = BLOCKS.register("ore_aluminum_scoria", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_SCORIA = BLOCKS.register("ore_silver_scoria", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_SCORIA = BLOCKS.register("ore_lead_scoria", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_SCORIA = BLOCKS.register("ore_nickel_scoria", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_SCORIA = BLOCKS.register("ore_uranium_scoria", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_SCORIA = BLOCKS.register("ore_osmium_scoria", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_SCORIA = BLOCKS.register("ore_tin_scoria", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_SCORIA = BLOCKS.register("ore_zinc_scoria", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_SCORIA = BLOCKS.register("ore_certus_quartz_scoria", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_SCORIA = BLOCKS.register("ore_charged_certus_quartz_scoria", OreChargedCertusQuartz::new);

  // Strata Ores - Weathered Limestone
  public static final RegistryObject<Block> ORE_COAL_WEATHERED_LIMESTONE = BLOCKS.register("ore_coal_weathered_limestone", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_WEATHERED_LIMESTONE = BLOCKS.register("ore_iron_weathered_limestone", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_WEATHERED_LIMESTONE = BLOCKS.register("ore_gold_weathered_limestone", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_WEATHERED_LIMESTONE = BLOCKS.register("ore_diamond_weathered_limestone", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_WEATHERED_LIMESTONE = BLOCKS.register("ore_emerald_weathered_limestone", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_WEATHERED_LIMESTONE = BLOCKS.register("ore_lapis_weathered_limestone", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_WEATHERED_LIMESTONE = BLOCKS.register("ore_redstone_weathered_limestone", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_WEATHERED_LIMESTONE = BLOCKS.register("ore_copper_weathered_limestone", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_WEATHERED_LIMESTONE = BLOCKS.register("ore_aluminum_weathered_limestone", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_WEATHERED_LIMESTONE = BLOCKS.register("ore_silver_weathered_limestone", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_WEATHERED_LIMESTONE = BLOCKS.register("ore_lead_weathered_limestone", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_WEATHERED_LIMESTONE = BLOCKS.register("ore_nickel_weathered_limestone", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_WEATHERED_LIMESTONE = BLOCKS.register("ore_uranium_weathered_limestone", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_WEATHERED_LIMESTONE = BLOCKS.register("ore_osmium_weathered_limestone", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_WEATHERED_LIMESTONE = BLOCKS.register("ore_tin_weathered_limestone", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_WEATHERED_LIMESTONE = BLOCKS.register("ore_zinc_weathered_limestone", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_WEATHERED_LIMESTONE = BLOCKS.register("ore_certus_quartz_weathered_limestone", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_WEATHERED_LIMESTONE = BLOCKS.register("ore_charged_certus_quartz_weathered_limestone", OreChargedCertusQuartz::new);

  // Strata Ores - Jasper
  public static final RegistryObject<Block> ORE_COAL_JASPER = BLOCKS.register("ore_coal_jasper", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_JASPER = BLOCKS.register("ore_iron_jasper", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_JASPER = BLOCKS.register("ore_gold_jasper", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_JASPER = BLOCKS.register("ore_diamond_jasper", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_JASPER = BLOCKS.register("ore_emerald_jasper", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_JASPER = BLOCKS.register("ore_lapis_jasper", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_JASPER = BLOCKS.register("ore_redstone_jasper", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_JASPER = BLOCKS.register("ore_copper_jasper", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_JASPER = BLOCKS.register("ore_aluminum_jasper", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_JASPER = BLOCKS.register("ore_silver_jasper", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_JASPER = BLOCKS.register("ore_lead_jasper", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_JASPER = BLOCKS.register("ore_nickel_jasper", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_JASPER = BLOCKS.register("ore_uranium_jasper", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_JASPER = BLOCKS.register("ore_osmium_jasper", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_JASPER = BLOCKS.register("ore_tin_jasper", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_JASPER = BLOCKS.register("ore_zinc_jasper", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_JASPER = BLOCKS.register("ore_certus_quartz_jasper", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_JASPER = BLOCKS.register("ore_charged_certus_quartz_jasper", OreChargedCertusQuartz::new);

  // Strata Ores - Limestone (Quark)
  public static final RegistryObject<Block> ORE_COAL_Q_LIMESTONE = BLOCKS.register("ore_coal_q_limestone", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_Q_LIMESTONE = BLOCKS.register("ore_iron_q_limestone", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_Q_LIMESTONE = BLOCKS.register("ore_gold_q_limestone", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_Q_LIMESTONE = BLOCKS.register("ore_diamond_q_limestone", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_Q_LIMESTONE = BLOCKS.register("ore_emerald_q_limestone", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_Q_LIMESTONE = BLOCKS.register("ore_lapis_q_limestone", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_Q_LIMESTONE = BLOCKS.register("ore_redstone_q_limestone", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_Q_LIMESTONE = BLOCKS.register("ore_copper_q_limestone", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_Q_LIMESTONE = BLOCKS.register("ore_aluminum_q_limestone", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_Q_LIMESTONE = BLOCKS.register("ore_silver_q_limestone", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_Q_LIMESTONE = BLOCKS.register("ore_lead_q_limestone", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_Q_LIMESTONE = BLOCKS.register("ore_nickel_q_limestone", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_Q_LIMESTONE = BLOCKS.register("ore_uranium_q_limestone", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_Q_LIMESTONE = BLOCKS.register("ore_osmium_q_limestone", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_Q_LIMESTONE = BLOCKS.register("ore_tin_q_limestone", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_Q_LIMESTONE = BLOCKS.register("ore_zinc_q_limestone", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_Q_LIMESTONE = BLOCKS.register("ore_certus_quartz_q_limestone", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_Q_LIMESTONE = BLOCKS.register("ore_charged_certus_quartz_q_limestone", OreChargedCertusQuartz::new);

  // Strata Ores - Marble
  public static final RegistryObject<Block> ORE_COAL_MARBLE = BLOCKS.register("ore_coal_marble", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_MARBLE = BLOCKS.register("ore_iron_marble", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_MARBLE = BLOCKS.register("ore_gold_marble", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_MARBLE = BLOCKS.register("ore_diamond_marble", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_MARBLE = BLOCKS.register("ore_emerald_marble", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_MARBLE = BLOCKS.register("ore_lapis_marble", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_MARBLE = BLOCKS.register("ore_redstone_marble", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_MARBLE = BLOCKS.register("ore_copper_marble", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_MARBLE = BLOCKS.register("ore_aluminum_marble", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_MARBLE = BLOCKS.register("ore_silver_marble", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_MARBLE = BLOCKS.register("ore_lead_marble", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_MARBLE = BLOCKS.register("ore_nickel_marble", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_MARBLE = BLOCKS.register("ore_uranium_marble", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_MARBLE = BLOCKS.register("ore_osmium_marble", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_MARBLE = BLOCKS.register("ore_tin_marble", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_MARBLE = BLOCKS.register("ore_zinc_marble", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_MARBLE = BLOCKS.register("ore_certus_quartz_marble", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_MARBLE = BLOCKS.register("ore_charged_certus_quartz_marble", OreChargedCertusQuartz::new);

  // Strata Ores - Slate
  public static final RegistryObject<Block> ORE_COAL_SLATE = BLOCKS.register("ore_coal_slate", OreCoal::new);
  public static final RegistryObject<Block> ORE_IRON_SLATE = BLOCKS.register("ore_iron_slate", OreIron::new);
  public static final RegistryObject<Block> ORE_GOLD_SLATE = BLOCKS.register("ore_gold_slate", OreGold::new);
  public static final RegistryObject<Block> ORE_DIAMOND_SLATE = BLOCKS.register("ore_diamond_slate", OreDiamond::new);
  public static final RegistryObject<Block> ORE_EMERALD_SLATE = BLOCKS.register("ore_emerald_slate", OreEmerald::new);
  public static final RegistryObject<Block> ORE_LAPIS_SLATE = BLOCKS.register("ore_lapis_slate", OreLapis::new);
  public static final RegistryObject<Block> ORE_REDSTONE_SLATE = BLOCKS.register("ore_redstone_slate", OreRedstone::new);
  public static final RegistryObject<Block> ORE_COPPER_SLATE = BLOCKS.register("ore_copper_slate", OreCopper::new);
  public static final RegistryObject<Block> ORE_ALUMINUM_SLATE = BLOCKS.register("ore_aluminum_slate", OreAluminum::new);
  public static final RegistryObject<Block> ORE_SILVER_SLATE = BLOCKS.register("ore_silver_slate", OreSilver::new);
  public static final RegistryObject<Block> ORE_LEAD_SLATE = BLOCKS.register("ore_lead_slate", OreLead::new);
  public static final RegistryObject<Block> ORE_NICKEL_SLATE = BLOCKS.register("ore_nickel_slate", OreNickel::new);
  public static final RegistryObject<Block> ORE_URANIUM_SLATE = BLOCKS.register("ore_uranium_slate", OreUranium::new);
  public static final RegistryObject<Block> ORE_OSMIUM_SLATE = BLOCKS.register("ore_osmium_slate", OreOsmium::new);
  public static final RegistryObject<Block> ORE_TIN_SLATE = BLOCKS.register("ore_tin_slate", OreTin::new);
  public static final RegistryObject<Block> ORE_ZINC_SLATE = BLOCKS.register("ore_zinc_slate", OreZinc::new);
  public static final RegistryObject<Block> ORE_CERTUS_QUARTZ_SLATE = BLOCKS.register("ore_certus_quartz_slate", OreCertusQuartz::new);
  public static final RegistryObject<Block> ORE_CHARGED_CERTUS_QUARTZ_SLATE = BLOCKS.register("ore_charged_certus_quartz_slate", OreChargedCertusQuartz::new);

  //Machines
  public static final RegistryObject<Block> ENIGMATIC_EXCHANGER = BLOCKS.register("enigmatic_exchanger", EnigmaticExchanger::new);
}
