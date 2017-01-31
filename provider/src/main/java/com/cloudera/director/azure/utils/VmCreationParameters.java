/*
 * Copyright (c) 2016 Cloudera, Inc.
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
package com.cloudera.director.azure.utils;

import com.microsoft.azure.management.compute.models.AvailabilitySet;
import com.microsoft.azure.management.network.models.NetworkSecurityGroup;
import com.microsoft.azure.management.network.models.Subnet;
import com.microsoft.azure.management.network.models.VirtualNetwork;
import com.microsoft.azure.management.storage.models.AccountType;

/**
 * Holds various parameters needed for creating VirtualMachine at Azure.
 */
public class VmCreationParameters {

  private final VirtualNetwork vnet;
  private final NetworkSecurityGroup nsg;
  private final AvailabilitySet availabilitySet;
  private final String vmSize;
  private final String vmName;
  private final String vmNamePrefix;
  private final String instanceId;
  private final String fqdnSuffix;
  private final String adminName;
  private final String sshPublicKey;
  private final Subnet subnet;
  private final AccountType storageAccountType;
  private final int dataDiskCount;
  private final int dataDiskSizeGiB;
  private final AzureVmImageInfo imageInfo;

  /**
   * Create a object that holds parameters used for VM creation.
   *
   * @param vnet               Virtual Network (object) the VM will be using
   * @param subnet             Subnet (object) the VM will be using
   * @param nsg                Network Security Group (object) the VM will be using
   * @param as                 Availability Set (object) the VM will be using
   * @param vmSize             size of the VM (instance type)
   * @param vmNamePrefix       prefix to VM name
   * @param instanceId         VM instance id (generated by director server)
   * @param fqdnSuffix         host FQDN suffix (DNS domain on private network)
   * @param adminName          admin username for VM
   * @param sshPublicKey       SSH public key for admin user to login to the VM
   * @param storageAccountType Storage Account type (enum)
   * @param dataDiskCount      number of data disks to attach to the VM
   * @param dataDiskSizeGiB    size of the data disks
   * @param imageInfo          VM image info (object)
   */
  public VmCreationParameters(VirtualNetwork vnet, Subnet subnet, NetworkSecurityGroup nsg,
    AvailabilitySet as, String vmSize, String vmNamePrefix, String instanceId,
    String fqdnSuffix, String adminName, String sshPublicKey, AccountType storageAccountType,
    int dataDiskCount, int dataDiskSizeGiB, AzureVmImageInfo imageInfo) {
    this.vnet = vnet;
    this.nsg = nsg;
    this.availabilitySet = as;
    this.vmSize = vmSize;
    this.vmName = vmNamePrefix + "-" + instanceId;
    this.vmNamePrefix = vmNamePrefix;
    this.instanceId = instanceId;
    this.fqdnSuffix = fqdnSuffix;
    this.adminName = adminName;
    this.sshPublicKey = sshPublicKey;
    this.storageAccountType = storageAccountType;
    this.dataDiskCount = dataDiskCount;
    this.dataDiskSizeGiB = dataDiskSizeGiB;
    this.imageInfo = imageInfo;
    this.subnet = subnet;
  }

  /**
   * Clone a VmCreationParameters object but give it a new instance ID. Used for test only.
   *
   * @param parameters object to be cloned
   * @param instanceId new instance ID to use
   * @return a new VmCreationParameters object
   */
  public static VmCreationParameters cloneVmCreationParametersWithNewInstanceId(
    VmCreationParameters parameters, String instanceId) {
    return new VmCreationParameters(parameters.vnet, parameters.subnet,
      parameters.nsg, parameters.availabilitySet, parameters.vmSize,
      parameters.vmNamePrefix, instanceId, parameters.fqdnSuffix,
      parameters.adminName, parameters.sshPublicKey, parameters.storageAccountType,
      parameters.dataDiskCount, parameters.dataDiskSizeGiB, parameters.imageInfo);
  }

  public VirtualNetwork getVnet() {
    return vnet;
  }

  public NetworkSecurityGroup getNsg() {
    return nsg;
  }

  public AvailabilitySet getAvailabilitySet() {
    return availabilitySet;
  }

  public String getVmSize() {
    return vmSize;
  }

  public String getVmNamePrefix() {
    return vmNamePrefix;
  }

  public String getInstanceId() {
    return instanceId;
  }

  public String getFqdnSuffix() {
    return fqdnSuffix;
  }

  public String getAdminName() {
    return adminName;
  }

  public String getSshPublicKey() {
    return sshPublicKey;
  }

  public Subnet getSubnet() {
    return subnet;
  }

  public AccountType getStorageAccountType() {
    return storageAccountType;
  }

  public int getDataDiskCount() {
    return dataDiskCount;
  }

  public int getDataDiskSizeGiB() {
    return dataDiskSizeGiB;
  }

  public AzureVmImageInfo getImageInfo() {
    return imageInfo;
  }


  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof VmCreationParameters)) {
      return false;
    }
    if (obj == this) {
      return true;
    }

    VmCreationParameters rhs = (VmCreationParameters) obj;
    return this.toString().equals(rhs.toString());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("vnet: %s, ", vnet.getId()));
    sb.append(String.format("subnet: %s, ", subnet.getId()));
    sb.append(String.format("nsg: %s, ", nsg.getId()));
    sb.append(String.format("availabilitySet: %s, ", availabilitySet.getId()));
    sb.append(String.format("vmSize: %s, ", vmSize));
    sb.append(String.format("vmName: %s, ", vmName));
    sb.append(String.format("vmNamePrefix: %s, ", vmNamePrefix));
    sb.append(String.format("instanceId: %s, ", instanceId));
    sb.append(String.format("fqdnSuffix: %s, ", fqdnSuffix));
    sb.append(String.format("adminName: %s, ", adminName));
    sb.append(String.format("sshPublicKey: %s, ", sshPublicKey));
    sb.append(String.format("storageAccountType: %s, ", storageAccountType));
    sb.append(String.format("dataDiskCount: %d, ", dataDiskCount));
    sb.append(String.format("dataDiskSizeGiB: %d, ", dataDiskSizeGiB));
    sb.append(String.format("imageInfo: %s ", imageInfo.toString()));
    return sb.toString();
  }
}
