package com.huanli233.oldAnimations.v1_8_9.mixins;

import com.huanli233.oldAnimations.core.OldAnimationsAddon;
import com.huanli233.oldAnimations.core.OldAnimationsConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

  @Shadow
  @Final
  private Minecraft mc;

  @ModifyConstant(method = "renderItemInFirstPerson", constant = @Constant(floatValue = 0.0f))
  public float modifyTransformItem(float original, float partialTicks) {

    AbstractClientPlayer abstractClientPlayer = mc.thePlayer;
    OldAnimationsConfiguration configuration = OldAnimationsAddon.get().configuration();
    return configuration.enabled().getOrDefault(true) && configuration.blockhit.getOrDefault(false) ? abstractClientPlayer.getSwingProgress(partialTicks) : original;
  }

}
