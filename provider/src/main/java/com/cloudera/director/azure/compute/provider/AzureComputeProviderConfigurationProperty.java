/*
 * Copyright (c) 2015 Cloudera, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.cloudera.director.azure.compute.provider;

import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.ConfigurationPropertyToken;
import com.cloudera.director.spi.v1.model.util.SimpleConfigurationPropertyBuilder;

/**
 * Azure provider level configuration properties.
 */
public enum AzureComputeProviderConfigurationProperty implements ConfigurationPropertyToken {

  REGION(new SimpleConfigurationPropertyBuilder()
    .configKey("region")
    .name("Region")
    .defaultValue("westus")
    .defaultDescription(
      "Region for deployment.<br />" +
        "All resources used for deployment must exist in the same region.<br />" +
        "<a target='_blank' href='https://azure.microsoft.com/en-us/regions/'>More Information</a>")
    .widget(ConfigurationProperty.Widget.OPENLIST)
    .addValidValues(
        // Americas
        "eastus",
        "eastus2",
        "centralus",
        "northcentralus",
        "southcentralus",
        "westcentralus",
        "westus",
        "westus2",
        "canadaeast",
        "canadacentral",
        "brazilsouth",
        // Europe
        "northeurope",
        "westeurope",
        "ukwest",
        "uksouth",
        // Asia Pacific
        "southeastasia",
        "eastasia",
        "australiaeast",
        "australiasoutheast",
        "centralindia",
        "southindia",
        "japaneast",
        "japanwest",
        // US Gov
        "usgovvirginia",
        //"usgoviowa", // this region does not support premium storage yet
        // Germany
        "germanycentral",
        "germanynortheast")
    .build());

  /**
   * The configuration property.
   */
  private final ConfigurationProperty configurationProperty;

  /**
   * Creates a configuration property token with the specified parameters.
   *
   * @param configurationProperty the configuration property
   */
  AzureComputeProviderConfigurationProperty(ConfigurationProperty configurationProperty) {
    this.configurationProperty = configurationProperty;
  }

  @Override
  public ConfigurationProperty unwrap() {
    return configurationProperty;
  }
}
