package com.huanli233.oldAnimations.core;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
public class OldAnimationsConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  public final ConfigProperty<Boolean> blockhit = new ConfigProperty<>(false);

  @SwitchSetting
  public final ConfigProperty<Boolean> pushing = new ConfigProperty<>(false);

  @SwitchSetting
  public final ConfigProperty<Boolean> pushingParticle = new ConfigProperty<>(false);

  @SwitchSetting
  public final ConfigProperty<Boolean> armorDamage = new ConfigProperty<>(false);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }
}
