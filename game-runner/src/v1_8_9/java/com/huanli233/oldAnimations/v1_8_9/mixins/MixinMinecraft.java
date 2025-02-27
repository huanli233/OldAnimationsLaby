package com.huanli233.oldAnimations.v1_8_9.mixins;

import com.huanli233.oldAnimations.core.OldAnimationsAddon;
import com.huanli233.oldAnimations.core.OldAnimationsConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

  @Shadow
  public GameSettings gameSettings;

  @Shadow
  public MovingObjectPosition objectMouseOver;

  @Shadow
  private int leftClickCounter;

  @Shadow
  public WorldClient theWorld;

  @Shadow
  public EntityPlayerSP thePlayer;

  @Shadow
  public EffectRenderer effectRenderer;

  @Shadow
  public PlayerControllerMP playerController;

  @Inject(method = "sendClickBlockToController", at = @At("HEAD"))
  public void preSendClickBlockToController(boolean leftClick, CallbackInfo ci) {

    OldAnimationsConfiguration configuration = OldAnimationsAddon.get().configuration();

    if (configuration.enabled().getOrDefault(true) && configuration.blockhit.getOrDefault(false) && configuration.pushing.getOrDefault(false) && gameSettings.keyBindUseItem.isKeyDown()) {
      if (leftClickCounter <= 0 && leftClick && objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
        if (!theWorld.isAirBlock(objectMouseOver.getBlockPos()) && thePlayer.isAllowEdit()) {

          if (configuration.pushingParticle.getOrDefault(false)) {
            effectRenderer.addBlockHitEffects(objectMouseOver.getBlockPos(), objectMouseOver.sideHit);
          }

          if (!thePlayer.isSwingInProgress || thePlayer.swingProgressInt >= ((EntityLivingBaseInvoker) thePlayer).invokeGetArmSwingAnimationEnd() / 2 || thePlayer.swingProgressInt < 0) {
            thePlayer.swingProgressInt = -1;
            thePlayer.isSwingInProgress = true;
          }
        }
      } else {
        playerController.resetBlockRemoving();
      }
    }
  }

}
