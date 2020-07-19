/*
 * Copyright (c) 2020 C4
 *
 * This file is part of Magical Psi, a mod made for Minecraft.
 *
 * Magical Psi is free software: you can redistribute it and/or modify it under the terms of the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International Public License.
 *
 * You should have received a copy of the license along with Magical Psi.
 * If not, see <https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.txt>.
 */

package top.theillusivec4.magipsi.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import javax.annotation.Nonnull;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.psi.common.item.armor.ItemPsimetalExosuitHelmet;

// todo: Make a PR to Psi to avoid this workaround for sensor overlays
public class SensorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends
    BipedArmorLayer<T, M, A> {

  public SensorLayer(IEntityRenderer<T, M> p_i50936_1_, A p_i50936_2_, A p_i50936_3_) {
    super(p_i50936_1_, p_i50936_2_, p_i50936_3_);
  }

  @Override
  public void render(@Nonnull MatrixStack matrixStackIn, @Nonnull IRenderTypeBuffer bufferIn,
      int packedLightIn, @Nonnull T entitylivingbaseIn, float limbSwing, float limbSwingAmount,
      float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ItemStack stack = entitylivingbaseIn.getItemStackFromSlot(EquipmentSlotType.HEAD);

    if (stack.getItem() instanceof ItemPsimetalExosuitHelmet) {
      ItemPsimetalExosuitHelmet helmet = (ItemPsimetalExosuitHelmet) stack.getItem();
      A a = this.getModelFromSlot(EquipmentSlotType.HEAD);
      a = getArmorModelHook(entitylivingbaseIn, stack, EquipmentSlotType.HEAD, a);
      (this.getEntityModel()).setModelAttributes(a);
      a.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
      this.setModelSlotVisible(a, EquipmentSlotType.HEAD);
      a.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
          headPitch);
      int i = helmet.getColor(stack);
      float red = (float) (i >> 16 & 255) / 255.0F;
      float green = (float) (i >> 8 & 255) / 255.0F;
      float blue = (float) (i & 255) / 255.0F;
      ResourceLocation resourceLocation = this
          .getArmorResource(entitylivingbaseIn, stack, EquipmentSlotType.HEAD, "overlay");
      IVertexBuilder ivertexbuilder = ItemRenderer
          .getBuffer(bufferIn, RenderType.getEntityCutoutNoCull(resourceLocation), false,
              stack.hasEffect());
      a.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, red, green,
          blue, 1.0F);
    }
  }
}
