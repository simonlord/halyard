/*
 * Copyright (c) 2017 Oracle America, Inc.
 *
 * The contents of this file are subject to the Apache License Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * If a copy of the Apache License Version 2.0 was not distributed with this file,
 * You can obtain one at https://www.apache.org/licenses/LICENSE-2.0.html
 */

package com.netflix.spinnaker.halyard.cli.command.v1.config.providers.oraclebmcs;

public class OracleBMCSCommandProperties {
  static final String COMPARTMENT_ID_DESCRIPTION = "Provide the ocid of the Oracle BMCS Compartment to use.";
  static final String SSH_PRIVATE_KEY_FILE_PATH_DESCRIPTION = "Provide the path to the private key for accessing Oracle BMCS.";
  static final String ORACLE_BMCS_CONFIG_FILE_PATH_DESCRIPTION = "Provide the path to the Oracle BMCS config file.";
}
