package top.theillusivec4.magipsi.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.Tags;
import top.theillusivec4.magipsi.MagicalPsi;
import top.theillusivec4.magipsi.common.MagipsiBlocks;
import vazkii.psi.common.block.base.ModBlocks;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.ModTags;

public class MagipsiRecipeProvider extends RecipeProvider {

  public MagipsiRecipeProvider(DataGenerator generator) {
    super(generator);
  }

  @Override
  protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
    ICriterionInstance hasIron = hasItem(Tags.Items.INGOTS_IRON);
    ICriterionInstance hasGold = hasItem(Tags.Items.INGOTS_GOLD);
    ICriterionInstance hasPsimetal = hasItem(ModTags.INGOT_PSIMETAL);
    ICriterionInstance hasPsigem = hasItem(ModTags.GEM_PSIGEM);
    ICriterionInstance hasEbonySubstance = hasItem(ModTags.EBONY_SUBSTANCE);
    ICriterionInstance hasIvorySubstance = hasItem(ModTags.IVORY_SUBSTANCE);
    ICriterionInstance hasEbonyPsimetal = hasItem(ModTags.INGOT_EBONY_PSIMETAL);
    ICriterionInstance hasIvoryPsimetal = hasItem(ModTags.INGOT_IVORY_PSIMETAL);
    ICriterionInstance hasPsidust = hasItem(ModTags.PSIDUST);

    ShapedRecipeBuilder.shapedRecipe(MagipsiBlocks.CONSTRUCTOR).key('G', Tags.Items.INGOTS_GOLD)
        .key('W', ItemTags.LOGS).key('P', Blocks.PISTON).patternLine("GWG").patternLine("WPW")
        .patternLine(" G ").addCriterion("has_gold", hasGold)
        .build(consumer, MagicalPsi.location("constructor"));

    ShapedRecipeBuilder.shapedRecipe(MagipsiBlocks.INSCRIBER).key('G', Tags.Items.INGOTS_GOLD)
        .key('M', Items.MAP).key('W', ItemTags.LOGS).key('D', ModTags.PSIDUST).patternLine("WMW")
        .patternLine("WDW").patternLine("G G").addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("inscriber"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.ebonyPsimetal).key('S', ModTags.EBONY_SUBSTANCE)
        .key('I', ModTags.INGOT_PSIMETAL).patternLine("SSS").patternLine("SIS").patternLine("SSS")
        .addCriterion("has_ebony_substance", hasEbonySubstance)
        .build(consumer, MagicalPsi.location("psimetal_ebony"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.ivoryPsimetal).key('S', ModTags.IVORY_SUBSTANCE)
        .key('I', ModTags.INGOT_PSIMETAL).patternLine("SSS").patternLine("SIS").patternLine("SSS")
        .addCriterion("has_ivory_substance", hasIvorySubstance)
        .build(consumer, MagicalPsi.location("psimetal_ivory"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadAssemblyIron).key('I', Tags.Items.INGOTS_IRON)
        .key('W', ItemTags.LOGS).patternLine("I  ").patternLine("IWI").patternLine("  I")
        .addCriterion("has_iron", hasIron).build(consumer, MagicalPsi.location("rod_iron"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadAssemblyGold).key('I', Tags.Items.INGOTS_GOLD)
        .key('W', ItemTags.LOGS).patternLine("I  ").patternLine("IWI").patternLine("  I")
        .addCriterion("has_gold", hasGold).build(consumer, MagicalPsi.location("rod_gold"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadAssemblyPsimetal).key('I', ModTags.INGOT_PSIMETAL)
        .key('W', ItemTags.LOGS).patternLine("I  ").patternLine("IWI").patternLine("  I")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("rod_psimetal"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadAssemblyEbony)
        .key('I', ModTags.INGOT_EBONY_PSIMETAL).key('W', ItemTags.LOGS).patternLine("I  ")
        .patternLine("IWI").patternLine("  I").addCriterion("has_ebony_psimetal", hasEbonyPsimetal)
        .build(consumer, MagicalPsi.location("rod_ebony"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadAssemblyIvory)
        .key('I', ModTags.INGOT_IVORY_PSIMETAL).key('W', ItemTags.LOGS).patternLine("I  ")
        .patternLine("IWI").patternLine("  I").addCriterion("has_ivory_psimetal", hasIvoryPsimetal)
        .build(consumer, MagicalPsi.location("rod_ivory"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadCoreBasic).key('I', Tags.Items.INGOTS_IRON)
        .key('D', Tags.Items.DUSTS_REDSTONE).key('P', ModTags.PSIDUST).patternLine("I  ")
        .patternLine("DPD").patternLine("  I").addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("crux_basic"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadCoreOverclocked).key('I', Tags.Items.INGOTS_GOLD)
        .key('D', Tags.Items.DUSTS_REDSTONE).key('P', ModTags.INGOT_PSIMETAL).patternLine("I  ")
        .patternLine("DPD").patternLine("  I").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("crux_quick"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadCoreConductive).key('I', Tags.Items.INGOTS_GOLD)
        .key('D', Tags.Items.DUSTS_GLOWSTONE).key('P', ModTags.INGOT_PSIMETAL).patternLine("I  ")
        .patternLine("DPD").patternLine("  I").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("crux_bright"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadCoreHyperClocked).key('I', Tags.Items.INGOTS_GOLD)
        .key('D', Tags.Items.DUSTS_REDSTONE).key('P', ModTags.GEM_PSIGEM).patternLine("I  ")
        .patternLine("DPD").patternLine("  I").addCriterion("has_psigem", hasPsigem)
        .build(consumer, MagicalPsi.location("crux_swift"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadCoreRadiative).key('I', Tags.Items.INGOTS_GOLD)
        .key('D', Tags.Items.DUSTS_GLOWSTONE).key('P', ModTags.GEM_PSIGEM).patternLine("I  ")
        .patternLine("DPD").patternLine("  I").addCriterion("has_psigem", hasPsigem)
        .build(consumer, MagicalPsi.location("crux_radiant"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadSocketBasic).key('W', ItemTags.LOGS)
        .key('D', ModTags.PSIDUST).patternLine("DW").patternLine("W ")
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("notch_basic"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadSocketSignaling).key('W', ItemTags.LOGS)
        .key('D', Tags.Items.DUSTS_REDSTONE).key('I', ModTags.INGOT_PSIMETAL).patternLine("DIW")
        .patternLine("IW ").patternLine("W  ").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("notch_flashing"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadSocketLarge).key('W', ItemTags.LOGS)
        .key('D', Tags.Items.DUSTS_GLOWSTONE).key('I', ModTags.INGOT_PSIMETAL).patternLine("DIW")
        .patternLine("IW ").patternLine("W  ").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("notch_large"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadSocketTransmissive).key('W', ItemTags.LOGS)
        .key('D', Tags.Items.DUSTS_REDSTONE).key('I', ModTags.INGOT_PSIMETAL)
        .key('G', ModTags.GEM_PSIGEM).patternLine("DIW").patternLine("IG ").patternLine("W  ")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("notch_beaming"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadSocketHuge).key('W', ItemTags.LOGS)
        .key('D', Tags.Items.DUSTS_GLOWSTONE).key('I', ModTags.INGOT_PSIMETAL)
        .key('G', ModTags.GEM_PSIGEM).patternLine("DIW").patternLine("IG ").patternLine("W  ")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("notch_major"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadBatteryBasic).key('I', Tags.Items.INGOTS_GOLD)
        .key('D', ModTags.PSIDUST).patternLine("I").patternLine("D").patternLine("I")
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("source_basic"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadBatteryExtended).key('I', Tags.Items.INGOTS_GOLD)
        .key('D', ModTags.INGOT_PSIMETAL).patternLine("I").patternLine("D").patternLine("I")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("source_sparkling"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadBatteryUltradense).key('I', Tags.Items.INGOTS_GOLD)
        .key('D', ModTags.GEM_PSIGEM).patternLine("I").patternLine("D").patternLine("I")
        .addCriterion("has_psigem", hasPsigem)
        .build(consumer, MagicalPsi.location("source_bursting"));

    Map<Item, Tag<Item>> dyes = new HashMap<>();
    dyes.put(ModItems.cadColorizerWhite, Tags.Items.DYES_WHITE);
    dyes.put(ModItems.cadColorizerOrange, Tags.Items.DYES_ORANGE);
    dyes.put(ModItems.cadColorizerMagenta, Tags.Items.DYES_MAGENTA);
    dyes.put(ModItems.cadColorizerLightBlue, Tags.Items.DYES_LIGHT_BLUE);
    dyes.put(ModItems.cadColorizerYellow, Tags.Items.DYES_YELLOW);
    dyes.put(ModItems.cadColorizerLime, Tags.Items.DYES_LIME);
    dyes.put(ModItems.cadColorizerPink, Tags.Items.DYES_PINK);
    dyes.put(ModItems.cadColorizerGray, Tags.Items.DYES_GRAY);
    dyes.put(ModItems.cadColorizerLightGray, Tags.Items.DYES_LIGHT_GRAY);
    dyes.put(ModItems.cadColorizerCyan, Tags.Items.DYES_CYAN);
    dyes.put(ModItems.cadColorizerPurple, Tags.Items.DYES_PURPLE);
    dyes.put(ModItems.cadColorizerBlue, Tags.Items.DYES_BLUE);
    dyes.put(ModItems.cadColorizerBrown, Tags.Items.DYES_BROWN);
    dyes.put(ModItems.cadColorizerGreen, Tags.Items.DYES_GREEN);
    dyes.put(ModItems.cadColorizerRed, Tags.Items.DYES_RED);
    dyes.put(ModItems.cadColorizerBlack, Tags.Items.DYES_BLACK);
    dyes.put(ModItems.cadColorizerRainbow, Tags.Items.GEMS_PRISMARINE);
    dyes.put(ModItems.cadColorizerPsi, ModTags.PSIDUST);

    for (Map.Entry<Item, Tag<Item>> entry : dyes.entrySet()) {
      String path = entry.getValue().getId().getPath();
      String name = path.substring(path.lastIndexOf("/") + 1).toLowerCase();
      ShapedRecipeBuilder.shapedRecipe(entry.getKey()).setGroup("psi:colorizer")
          .key('I', Tags.Items.INGOTS_IRON).key('G', Tags.Items.GLASS).key('C', entry.getValue())
          .key('D', ModTags.PSIDUST).patternLine(" D ").patternLine("GCG").patternLine(" I ")
          .addCriterion("has_psidust", hasPsidust)
          .build(consumer, MagicalPsi.location("varnish_" + name));
    }

    ShapedRecipeBuilder.shapedRecipe(ModItems.cadColorizerEmpty).setGroup("psi:colorizer")
        .key('I', Tags.Items.INGOTS_IRON).key('G', Tags.Items.GLASS).key('D', ModTags.PSIDUST)
        .patternLine(" D ").patternLine("G G").patternLine(" I ")
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("varnish_empty"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.spellBullet).addIngredient(ItemTags.LOGS)
        .addIngredient(Items.PAPER).addIngredient(ModTags.PSIDUST)
        .addCriterion("has_psidust", hasPsidust).build(consumer, MagicalPsi.location("spell_node"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.projectileSpellBullet)
        .addIngredient(ItemTags.LOGS).addIngredient(Items.PAPER).addIngredient(ModTags.PSIDUST)
        .addIngredient(Tags.Items.ARROWS).addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_projectile"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.projectileSpellBullet)
        .addIngredient(ModItems.spellBullet).addIngredient(Tags.Items.ARROWS)
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_projectile_upgrade"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.loopSpellBullet).addIngredient(ItemTags.LOGS)
        .addIngredient(Items.PAPER).addIngredient(ModTags.PSIDUST).addIngredient(Tags.Items.STRING)
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_loop"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.loopSpellBullet)
        .addIngredient(ModItems.spellBullet).addIngredient(Tags.Items.STRING)
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_loop_upgrade"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.circleSpellBullet).addIngredient(ItemTags.LOGS)
        .addIngredient(Items.PAPER).addIngredient(ModTags.PSIDUST).addIngredient(Ingredient
        .fromItemListStream(Stream.of(new Ingredient.TagList(Tags.Items.SLIMEBALLS),
            new Ingredient.SingleItemList(new ItemStack(Items.SNOWBALL)))))
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_circle"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.circleSpellBullet)
        .addIngredient(ModItems.spellBullet).addIngredient(Ingredient.fromItemListStream(Stream
        .of(new Ingredient.TagList(Tags.Items.SLIMEBALLS),
            new Ingredient.SingleItemList(new ItemStack(Items.SNOWBALL)))))
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_circle_upgrade"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.grenadeSpellBullet).addIngredient(ItemTags.LOGS)
        .addIngredient(Items.PAPER).addIngredient(ModTags.PSIDUST)
        .addIngredient(Tags.Items.GUNPOWDER).addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_grenade"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.grenadeSpellBullet)
        .addIngredient(ModItems.spellBullet).addIngredient(Tags.Items.GUNPOWDER)
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_grenade_upgrade"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.chargeSpellBullet).addIngredient(ItemTags.LOGS)
        .addIngredient(Items.PAPER).addIngredient(ModTags.PSIDUST)
        .addIngredient(Tags.Items.DUSTS_REDSTONE).addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_charge"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.chargeSpellBullet)
        .addIngredient(ModItems.spellBullet).addIngredient(Tags.Items.DUSTS_REDSTONE)
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_charge_upgrade"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.mineSpellBullet).addIngredient(ItemTags.LOGS)
        .addIngredient(Items.PAPER).addIngredient(ModTags.PSIDUST).addIngredient(ItemTags.BUTTONS)
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_mine"));

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.mineSpellBullet)
        .addIngredient(ModItems.spellBullet).addIngredient(ItemTags.BUTTONS)
        .addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("spell_node_mine_upgrade"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.spellDrive).key('W', ItemTags.LOGS)
        .key('P', Items.PAPER).key('I', ModTags.INGOT_PSIMETAL).key('R', Tags.Items.DUSTS_REDSTONE)
        .patternLine("WIW").patternLine("PRP").patternLine("PIP")
        .addCriterion("has_psimetal", hasPsimetal).build(consumer, MagicalPsi.location("scroll"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalShovel).key('W', ItemTags.LOGS)
        .key('P', ModTags.INGOT_PSIMETAL).key('G', ModTags.GEM_PSIGEM)
        .key('I', Tags.Items.INGOTS_GOLD).patternLine("GP").patternLine(" W").patternLine(" I")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_shovel"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalPickaxe).key('W', ItemTags.LOGS)
        .key('P', ModTags.INGOT_PSIMETAL).key('G', ModTags.GEM_PSIGEM)
        .key('I', Tags.Items.INGOTS_GOLD).patternLine("PGP").patternLine(" W ").patternLine(" I ")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_pickaxe"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalAxe).key('W', ItemTags.LOGS)
        .key('P', ModTags.INGOT_PSIMETAL).key('G', ModTags.GEM_PSIGEM)
        .key('I', Tags.Items.INGOTS_GOLD).patternLine("GP").patternLine("PW").patternLine(" I")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_axe"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalSword).key('W', ItemTags.LOGS)
        .key('P', ModTags.INGOT_PSIMETAL).key('G', ModTags.GEM_PSIGEM)
        .key('I', Tags.Items.INGOTS_GOLD).patternLine(" P").patternLine("IG").patternLine(" W")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_sword"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalExosuitHelmet)
        .key('P', ModTags.INGOT_PSIMETAL).key('G', ModTags.GEM_PSIGEM).patternLine("GPG")
        .patternLine("P P").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_helmet"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalExosuitChestplate)
        .key('P', ModTags.INGOT_PSIMETAL).key('G', ModTags.GEM_PSIGEM).patternLine("P P")
        .patternLine("GPG").patternLine("PPP").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_chestplate"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalExosuitLeggings)
        .key('P', ModTags.INGOT_PSIMETAL).key('G', ModTags.GEM_PSIGEM).patternLine("GPG")
        .patternLine("P P").patternLine("P P").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_leggings"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.psimetalExosuitBoots).key('P', ModTags.INGOT_PSIMETAL)
        .key('G', ModTags.GEM_PSIGEM).patternLine("G G").patternLine("P P")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_boots"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.detonator).key('P', ModTags.PSIDUST)
        .key('I', Tags.Items.INGOTS_GOLD).key('B', ItemTags.BUTTONS).patternLine(" B ")
        .patternLine("IPI").addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("detonator"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.exosuitController).key('W', ItemTags.LOGS)
        .key('P', Items.PAPER).key('R', Tags.Items.DUSTS_REDSTONE)
        .key('G', Tags.Items.GLASS_COLORLESS).key('I', ModTags.INGOT_PSIMETAL).patternLine("WRW")
        .patternLine("PGP").patternLine("WIW").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_tablet"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.vectorRuler).key('D', ModTags.PSIDUST)
        .key('W', ItemTags.LOGS).key('I', Tags.Items.INGOTS_GOLD).patternLine("D").patternLine("W")
        .patternLine("I").addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("vector_rule"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.exosuitSensorLight).key('I', ModTags.INGOT_PSIMETAL)
        .key('R', Tags.Items.INGOTS_GOLD).key('M', Tags.Items.DUSTS_GLOWSTONE).patternLine(" I ")
        .patternLine("IMR").patternLine(" R ").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_sensor_light"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.exosuitSensorWater).key('I', ModTags.INGOT_PSIMETAL)
        .key('R', Tags.Items.INGOTS_GOLD).key('M', Tags.Items.GEMS_PRISMARINE).patternLine(" I ")
        .patternLine("IMR").patternLine(" R ").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_sensor_water"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.exosuitSensorHeat).key('I', ModTags.INGOT_PSIMETAL)
        .key('R', Tags.Items.INGOTS_GOLD).key('M', Items.FIRE_CHARGE).patternLine(" I ")
        .patternLine("IMR").patternLine(" R ").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_sensor_heat"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.exosuitSensorStress).key('I', ModTags.INGOT_PSIMETAL)
        .key('R', Tags.Items.INGOTS_GOLD).key('M', Items.GLISTERING_MELON_SLICE).patternLine(" I ")
        .patternLine("IMR").patternLine(" R ").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_sensor_stress"));

    ShapedRecipeBuilder.shapedRecipe(ModItems.exosuitSensorTrigger).key('I', ModTags.INGOT_PSIMETAL)
        .key('R', Tags.Items.INGOTS_GOLD).key('M', Tags.Items.GUNPOWDER).patternLine(" I ")
        .patternLine("IMR").patternLine(" R ").addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("focusing_sensor_trigger"));

    ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.psidustBlock)
        .addIngredient(ModItems.psidust, 9).addCriterion("has_psidust", hasPsidust)
        .build(consumer, MagicalPsi.location("psidust_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.psimetalBlock)
        .addIngredient(ModItems.psimetal, 9).addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.psigemBlock).addIngredient(ModItems.psigem, 9)
        .addCriterion("has_psigem", hasPsigem).build(consumer, MagicalPsi.location("psigem_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.psimetalEbony)
        .addIngredient(ModItems.ebonyPsimetal, 9)
        .addCriterion("has_ebony_psimetal", hasEbonyPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_ebony_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.psimetalIvory)
        .addIngredient(ModItems.ivoryPsimetal, 9)
        .addCriterion("has_ivory_psimetal", hasIvoryPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_ivory_block"));

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.psimetalPlateBlack).key('C', ItemTags.COALS)
        .key('I', ModTags.INGOT_PSIMETAL).patternLine(" C ").patternLine("CIC").patternLine(" C ")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_plate_black"));

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.psimetalPlateWhite).key('C', Tags.Items.GEMS_QUARTZ)
        .key('I', ModTags.INGOT_PSIMETAL).patternLine(" C ").patternLine("CIC").patternLine(" C ")
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_plate_white"));

    ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.psimetalPlateBlackLight)
        .addIngredient(ModBlocks.psimetalPlateBlack).addIngredient(Tags.Items.DUSTS_GLOWSTONE)
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_plate_black_light"));

    ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.psimetalPlateWhiteLight)
        .addIngredient(ModBlocks.psimetalPlateWhite).addIngredient(Tags.Items.DUSTS_GLOWSTONE)
        .addCriterion("has_psimetal", hasPsimetal)
        .build(consumer, MagicalPsi.location("psimetal_plate_white_light"));
  }

  @Nonnull
  @Override
  public String getName() {
    return "Magical Psi Crafting Recipes";
  }
}
