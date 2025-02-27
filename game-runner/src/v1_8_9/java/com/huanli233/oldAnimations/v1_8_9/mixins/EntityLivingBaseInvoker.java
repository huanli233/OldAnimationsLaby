package com.huanli233.oldAnimations.v1_8_9.mixins;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityLivingBase.class)
public interface EntityLivingBaseInvoker {

  @Invoker("getArmSwingAnimationEnd")
  int invokeGetArmSwingAnimationEnd();

}
