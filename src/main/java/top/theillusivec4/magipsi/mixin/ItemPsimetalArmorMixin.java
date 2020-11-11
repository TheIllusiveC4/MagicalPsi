package top.theillusivec4.magipsi.mixin;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.magipsi.client.FocusingPlateModel;
import vazkii.psi.common.item.armor.ItemPsimetalArmor;

@Mixin(ItemPsimetalArmor.class)
public class ItemPsimetalArmorMixin {

  @Inject(at = @At("HEAD"), method = "provideArmorModelForSlot", cancellable = true, remap = false)
  public void _magipsi_provideArmorModelForSlot(EquipmentSlotType slotType,
                                                CallbackInfoReturnable<BipedModel<?>> ci) {
    ci.setReturnValue(new FocusingPlateModel(slotType));
  }
}
