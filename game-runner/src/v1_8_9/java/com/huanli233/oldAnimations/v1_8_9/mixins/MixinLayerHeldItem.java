package com.huanli233.oldAnimations.v1_8_9.mixins;

import com.huanli233.oldAnimations.core.OldAnimationsAddon;
import com.huanli233.oldAnimations.core.OldAnimationsConfiguration;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LayerHeldItem.class, priority = 2000)
public class MixinLayerHeldItem {

  @Inject(method = "doRenderLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
  private void injectDoRenderLayer(EntityLivingBase lvt_1_1_, float lvt_2_1_, float lvt_3_1_,
      float lvt_4_1_, float lvt_5_1_, float lvt_6_1_, float lvt_7_1_, float lvt_8_1_,
      CallbackInfo ci) {
    OldAnimationsConfiguration configuration = OldAnimationsAddon.get().configuration();
    if (configuration.enabled().getOrDefault(true) && configuration.blockhit.getOrDefault(false)) {
      AbstractClientPlayer player = null;
      if (lvt_1_1_ instanceof AbstractClientPlayer) {
        player = (AbstractClientPlayer) lvt_1_1_;
      }
      EnumAction enumAction;
      if (player!= null && player.getItemInUseCount() > 0) {
        enumAction = lvt_1_1_.getHeldItem().getItemUseAction();
        if (enumAction == EnumAction.BLOCK) {
          GlStateManager.translate(0.05F, 0.0F, -0.1F);
          GlStateManager.rotate(-50.0F, 0.0F, 1.0F, 0.0F);
          GlStateManager.rotate(-10.0F, 1.0F, 0.0F, 0.0F);
          GlStateManager.rotate(-60.0F, 0.0F, 0.0F, 1.0F);
        }
      }
    }
  }

}
