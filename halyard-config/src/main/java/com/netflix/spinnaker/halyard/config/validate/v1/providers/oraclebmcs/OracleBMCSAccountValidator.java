/*
 * Copyright (c) 2017 Oracle America, Inc.
 *
 * The contents of this file are subject to the Apache License Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * If a copy of the Apache License Version 2.0 was not distributed with this file,
 * You can obtain one at https://www.apache.org/licenses/LICENSE-2.0.html
 */

package com.netflix.spinnaker.halyard.config.validate.v1.providers.oraclebmcs;

import com.netflix.spinnaker.halyard.config.model.v1.node.Validator;
import com.netflix.spinnaker.halyard.config.model.v1.providers.oraclebmcs.OracleBMCSAccount;
import com.netflix.spinnaker.halyard.config.problem.v1.ConfigProblemSetBuilder;
import com.netflix.spinnaker.halyard.config.validate.v1.util.ValidatingFileReader;
import com.netflix.spinnaker.halyard.core.problem.v1.Problem.Severity;
import org.springframework.stereotype.Component;

@Component
public class OracleBMCSAccountValidator extends Validator<OracleBMCSAccount> {
  @Override
  public void validate(ConfigProblemSetBuilder psBuilder, OracleBMCSAccount account) {
    String configPath = System.getenv("HOME") + "/.oraclebmc/config";


    String compartmentId = account.getCompartmentId();
    if (compartmentId == null || compartmentId.isEmpty()) {
      psBuilder.addProblem(Severity.FATAL, "You must provide a compartment id");
    }

    String sshPrivateKey = ValidatingFileReader.contents(psBuilder, account.getSshPrivateKeyFilePath());
    if (sshPrivateKey == null) {
      return;
    }
    if (sshPrivateKey.isEmpty()) {
      psBuilder.addProblem(Severity.WARNING, "The supplied ssh private key file is empty.");
    }

    String config = ValidatingFileReader.contents(psBuilder, account.getOracleBmcsConfigFilePath());
    if (config == null) {
      return;
    } else if (config.isEmpty()) {
      psBuilder.addProblem(Severity.FATAL, "You must configure the oraclebmcs config file: ~/.oraclebmc/config");
      return;
    }
    if (!config.contains("[" + account.getName() + "]")) {
      psBuilder.addProblem(Severity.FATAL, "The oraclebmcs config file (~/.oraclebmc/config) is missing a profile called " + account.getName());
    }

    // TODO (simonlord): Once BMCS SDK is in maven we can access via spinnaker.dependency("clouddriverOracleBmcs") and test account login
  }
}