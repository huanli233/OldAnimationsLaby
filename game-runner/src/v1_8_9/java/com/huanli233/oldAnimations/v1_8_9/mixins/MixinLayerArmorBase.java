package com.huanli233.oldAnimations.v1_8_9.mixins;

import com.huanli233.oldAnimations.core.OldAnimationsAddon;
import com.huanli233.oldAnimations.core.OldAnimationsConfiguration;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LayerArmorBase.class, priority = 2000)
public class MixinLayerArmorBase {

  @Inject(method = "shouldCombineTextures", at = @At("HEAD"), cancellable = true)
  public void oldArmorDamage(CallbackInfoReturnable<Boolean> cir) {

    OldAnimationsConfiguration configuration = OldAnimationsAddon.get().configuration();

    if (configuration.enabled().getOrDefault())
      cir.setReturnValue(configuration.armorDamage.getOrDefault());
  }

}
