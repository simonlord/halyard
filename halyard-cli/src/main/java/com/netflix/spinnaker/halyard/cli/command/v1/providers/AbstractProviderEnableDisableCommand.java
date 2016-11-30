/*
 * Copyright 2016 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.halyard.cli.command.v1.providers;

import com.netflix.spinnaker.halyard.cli.services.v1.Daemon;
import com.netflix.spinnaker.halyard.cli.services.v1.DaemonService;
import com.netflix.spinnaker.halyard.cli.ui.v1.AnsiUi;

public abstract class AbstractProviderEnableDisableCommand extends AbstractProviderCommand {
  @Override
  public String getCommandName() {
    return isEnable() ? "enable" : "disable";
  }

  private String futurePerfectAction() {
    return isEnable() ? "enabled" : "disabled";
  }

  protected abstract boolean isEnable();

  @Override
  public String getDescription() {
    return "Set the " + getProviderName() + " provider as " + getCommandName() + "d";
  }

  private void setEnable() {
    DaemonService service = Daemon.getService();
    String currentDeployment = service.getCurrentDeployment();
    service.setProviderEnabled(currentDeployment, getProviderName(), !noValidate, isEnable());
  }

  @Override
  protected void executeThis() {
    setEnable();
    AnsiUi.success("Successfully " + futurePerfectAction() + getProviderName());
  }
}