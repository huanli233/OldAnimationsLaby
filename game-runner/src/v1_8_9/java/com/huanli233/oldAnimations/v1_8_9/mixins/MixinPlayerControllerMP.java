package com.huanli233.oldAnimations.v1_8_9.mixins;

import com.huanli233.oldAnimations.core.OldAnimationsAddon;
import com.huanli233.oldAnimations.core.OldAnimationsConfiguration;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerControllerMP.class, priority = 2000)
public class MixinPlayerControllerMP {

  @Inject(method = "getIsHittingBlock", at = @At("HEAD"), cancellable = true)
  private void cancelHit(CallbackInfoReturnable<Boolean> cir) {

    OldAnimationsConfiguration configuration = OldAnimationsAddon.get().configuration();

    if(configuration.enabled().getOrDefault(true) && configuration.pushing.getOrDefault(false) && configuration.blockhit.getOrDefault(false)) {
      cir.setReturnValue(false);
    }
  }

}
