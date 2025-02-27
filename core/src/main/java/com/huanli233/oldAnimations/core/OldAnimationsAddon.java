package com.huanli233.oldAnimations.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class OldAnimationsAddon extends LabyAddon<OldAnimationsConfiguration> {

  private static OldAnimationsAddon instance;

  public OldAnimationsAddon() {
    instance = this;
  }

  public static OldAnimationsAddon get() {
    return instance;
  }

  @Override
  protected void enable() {
    this.registerSettingCategory();
  }

  @Override
  protected Class<OldAnimationsConfiguration> configurationClass() {
    return OldAnimationsConfiguration.class;
  }
}
