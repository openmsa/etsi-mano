/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * AppD
 */
@Validated
public class AppD {
	@JsonProperty("appDId")
	private String appDId = null;

	@JsonProperty("appDNSRule")
	@Valid
	private List<DNSRuleDescriptor> appDNSRule = null;

	@JsonProperty("appDVersion")
	private String appDVersion = null;

	@JsonProperty("appDescription")
	private String appDescription = null;

	@JsonProperty("appExtCpd")
	@Valid
	private List<AppExternalCpd> appExtCpd = null;

	@JsonProperty("appFeatureOptional")
	@Valid
	private List<FeatureDependency> appFeatureOptional = null;

	@JsonProperty("appFeatureRequired")
	@Valid
	private List<FeatureDependency> appFeatureRequired = null;

	@JsonProperty("appInfoName")
	private String appInfoName = null;

	@JsonProperty("appLatency")
	private LatencyDescriptor appLatency = null;

	@JsonProperty("appName")
	private String appName = null;

	@JsonProperty("appProvider")
	private String appProvider = null;

	@JsonProperty("appServiceOptional")
	@Valid
	private List<ServiceDependency> appServiceOptional = null;

	@JsonProperty("appServiceProduced")
	@Valid
	private List<ServiceDescriptor> appServiceProduced = null;

	@JsonProperty("appServiceRequired")
	@Valid
	private List<ServiceDependency> appServiceRequired = null;

	@JsonProperty("appSoftVersion")
	private String appSoftVersion = null;

	@JsonProperty("appTrafficRule")
	@Valid
	private List<TrafficRuleDescriptor> appTrafficRule = null;

	@JsonProperty("changeAppInstanceStateOpConfig")
	private String changeAppInstanceStateOpConfig = null;

	@JsonProperty("mecVersion")
	@Valid
	private List<String> mecVersion = new ArrayList<>();

	@JsonProperty("swImageDescriptor")
	private String swImageDescriptor = null;

	@JsonProperty("terminateAppInstanceOpConfig")
	private String terminateAppInstanceOpConfig = null;

	@JsonProperty("transportDependencies")
	@Valid
	private List<TransportDependency> transportDependencies = null;

	@JsonProperty("virtualComputeDescriptor")
	private String virtualComputeDescriptor = null;

	@JsonProperty("virtualStorageDescriptor")
	@Valid
	private List<String> virtualStorageDescriptor = null;

	public AppD appDId(final String appDId) {
		this.appDId = appDId;
		return this;
	}

	/**
	 * Identifier of this MEC application descriptor. This attribute shall be
	 * globally unique. See note 1.
	 *
	 * @return appDId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this MEC application descriptor. This attribute shall be globally unique. See note 1.")
	@NotNull

	public String getAppDId() {
		return appDId;
	}

	public void setAppDId(final String appDId) {
		this.appDId = appDId;
	}

	public AppD appDNSRule(final List<DNSRuleDescriptor> appDNSRule) {
		this.appDNSRule = appDNSRule;
		return this;
	}

	public AppD addAppDNSRuleItem(final DNSRuleDescriptor appDNSRuleItem) {
		if (this.appDNSRule == null) {
			this.appDNSRule = new ArrayList<>();
		}
		this.appDNSRule.add(appDNSRuleItem);
		return this;
	}

	/**
	 * Describes DNS rules the MEC application requires.
	 *
	 * @return appDNSRule
	 **/
	@ApiModelProperty(value = "Describes DNS rules the MEC application requires.")
	@Valid
	public List<DNSRuleDescriptor> getAppDNSRule() {
		return appDNSRule;
	}

	public void setAppDNSRule(final List<DNSRuleDescriptor> appDNSRule) {
		this.appDNSRule = appDNSRule;
	}

	public AppD appDVersion(final String appDVersion) {
		this.appDVersion = appDVersion;
		return this;
	}

	/**
	 * Identifies the version of the application descriptor.
	 *
	 * @return appDVersion
	 **/
	@ApiModelProperty(required = true, value = "Identifies the version of the application descriptor.")
	@NotNull

	public String getAppDVersion() {
		return appDVersion;
	}

	public void setAppDVersion(final String appDVersion) {
		this.appDVersion = appDVersion;
	}

	public AppD appDescription(final String appDescription) {
		this.appDescription = appDescription;
		return this;
	}

	/**
	 * Human readable description of the MEC application.
	 *
	 * @return appDescription
	 **/
	@ApiModelProperty(required = true, value = "Human readable description of the MEC application.")
	@NotNull

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(final String appDescription) {
		this.appDescription = appDescription;
	}

	public AppD appExtCpd(final List<AppExternalCpd> appExtCpd) {
		this.appExtCpd = appExtCpd;
		return this;
	}

	public AppD addAppExtCpdItem(final AppExternalCpd appExtCpdItem) {
		if (this.appExtCpd == null) {
			this.appExtCpd = new ArrayList<>();
		}
		this.appExtCpd.add(appExtCpdItem);
		return this;
	}

	/**
	 * Describes external interface(s) exposed by this MEC application.
	 *
	 * @return appExtCpd
	 **/
	@ApiModelProperty(value = "Describes external interface(s) exposed by this MEC application.")
	@Valid
	public List<AppExternalCpd> getAppExtCpd() {
		return appExtCpd;
	}

	public void setAppExtCpd(final List<AppExternalCpd> appExtCpd) {
		this.appExtCpd = appExtCpd;
	}

	public AppD appFeatureOptional(final List<FeatureDependency> appFeatureOptional) {
		this.appFeatureOptional = appFeatureOptional;
		return this;
	}

	public AppD addAppFeatureOptionalItem(final FeatureDependency appFeatureOptionalItem) {
		if (this.appFeatureOptional == null) {
			this.appFeatureOptional = new ArrayList<>();
		}
		this.appFeatureOptional.add(appFeatureOptionalItem);
		return this;
	}

	/**
	 * Describes features a MEC application may use if available.
	 *
	 * @return appFeatureOptional
	 **/
	@ApiModelProperty(value = "Describes features a MEC application may use if available.")
	@Valid
	public List<FeatureDependency> getAppFeatureOptional() {
		return appFeatureOptional;
	}

	public void setAppFeatureOptional(final List<FeatureDependency> appFeatureOptional) {
		this.appFeatureOptional = appFeatureOptional;
	}

	public AppD appFeatureRequired(final List<FeatureDependency> appFeatureRequired) {
		this.appFeatureRequired = appFeatureRequired;
		return this;
	}

	public AppD addAppFeatureRequiredItem(final FeatureDependency appFeatureRequiredItem) {
		if (this.appFeatureRequired == null) {
			this.appFeatureRequired = new ArrayList<>();
		}
		this.appFeatureRequired.add(appFeatureRequiredItem);
		return this;
	}

	/**
	 * Describes features a MEC application requires to run.
	 *
	 * @return appFeatureRequired
	 **/
	@ApiModelProperty(value = "Describes features a MEC application requires to run.")
	@Valid
	public List<FeatureDependency> getAppFeatureRequired() {
		return appFeatureRequired;
	}

	public void setAppFeatureRequired(final List<FeatureDependency> appFeatureRequired) {
		this.appFeatureRequired = appFeatureRequired;
	}

	public AppD appInfoName(final String appInfoName) {
		this.appInfoName = appInfoName;
		return this;
	}

	/**
	 * Human readable name for the MEC application.
	 *
	 * @return appInfoName
	 **/
	@ApiModelProperty(value = "Human readable name for the MEC application.")

	public String getAppInfoName() {
		return appInfoName;
	}

	public void setAppInfoName(final String appInfoName) {
		this.appInfoName = appInfoName;
	}

	public AppD appLatency(final LatencyDescriptor appLatency) {
		this.appLatency = appLatency;
		return this;
	}

	/**
	 * Get appLatency
	 *
	 * @return appLatency
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public LatencyDescriptor getAppLatency() {
		return appLatency;
	}

	public void setAppLatency(final LatencyDescriptor appLatency) {
		this.appLatency = appLatency;
	}

	public AppD appName(final String appName) {
		this.appName = appName;
		return this;
	}

	/**
	 * Name to identify the MEC application.
	 *
	 * @return appName
	 **/
	@ApiModelProperty(required = true, value = "Name to identify the MEC application.")
	@NotNull

	public String getAppName() {
		return appName;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public AppD appProvider(final String appProvider) {
		this.appProvider = appProvider;
		return this;
	}

	/**
	 * Provider of the application and of the AppD.
	 *
	 * @return appProvider
	 **/
	@ApiModelProperty(required = true, value = "Provider of the application and of the AppD.")
	@NotNull

	public String getAppProvider() {
		return appProvider;
	}

	public void setAppProvider(final String appProvider) {
		this.appProvider = appProvider;
	}

	public AppD appServiceOptional(final List<ServiceDependency> appServiceOptional) {
		this.appServiceOptional = appServiceOptional;
		return this;
	}

	public AppD addAppServiceOptionalItem(final ServiceDependency appServiceOptionalItem) {
		if (this.appServiceOptional == null) {
			this.appServiceOptional = new ArrayList<>();
		}
		this.appServiceOptional.add(appServiceOptionalItem);
		return this;
	}

	/**
	 * Describes services a MEC application may use if available.
	 *
	 * @return appServiceOptional
	 **/
	@ApiModelProperty(value = "Describes services a MEC application may use if available.")
	@Valid
	public List<ServiceDependency> getAppServiceOptional() {
		return appServiceOptional;
	}

	public void setAppServiceOptional(final List<ServiceDependency> appServiceOptional) {
		this.appServiceOptional = appServiceOptional;
	}

	public AppD appServiceProduced(final List<ServiceDescriptor> appServiceProduced) {
		this.appServiceProduced = appServiceProduced;
		return this;
	}

	public AppD addAppServiceProducedItem(final ServiceDescriptor appServiceProducedItem) {
		if (this.appServiceProduced == null) {
			this.appServiceProduced = new ArrayList<>();
		}
		this.appServiceProduced.add(appServiceProducedItem);
		return this;
	}

	/**
	 * Describes services a MEC application is able to produce to the platform or
	 * other MEC applications. Only relevant for service-producing apps.
	 *
	 * @return appServiceProduced
	 **/
	@ApiModelProperty(value = "Describes services a MEC application is able to produce to the platform or other MEC applications. Only relevant for service-producing apps.")
	@Valid
	public List<ServiceDescriptor> getAppServiceProduced() {
		return appServiceProduced;
	}

	public void setAppServiceProduced(final List<ServiceDescriptor> appServiceProduced) {
		this.appServiceProduced = appServiceProduced;
	}

	public AppD appServiceRequired(final List<ServiceDependency> appServiceRequired) {
		this.appServiceRequired = appServiceRequired;
		return this;
	}

	public AppD addAppServiceRequiredItem(final ServiceDependency appServiceRequiredItem) {
		if (this.appServiceRequired == null) {
			this.appServiceRequired = new ArrayList<>();
		}
		this.appServiceRequired.add(appServiceRequiredItem);
		return this;
	}

	/**
	 * Describes services a MEC application requires to run.
	 *
	 * @return appServiceRequired
	 **/
	@ApiModelProperty(value = "Describes services a MEC application requires to run.")
	@Valid
	public List<ServiceDependency> getAppServiceRequired() {
		return appServiceRequired;
	}

	public void setAppServiceRequired(final List<ServiceDependency> appServiceRequired) {
		this.appServiceRequired = appServiceRequired;
	}

	public AppD appSoftVersion(final String appSoftVersion) {
		this.appSoftVersion = appSoftVersion;
		return this;
	}

	/**
	 * Identifies the version of software of the MEC application.
	 *
	 * @return appSoftVersion
	 **/
	@ApiModelProperty(required = true, value = "Identifies the version of software of the MEC application.")
	@NotNull

	public String getAppSoftVersion() {
		return appSoftVersion;
	}

	public void setAppSoftVersion(final String appSoftVersion) {
		this.appSoftVersion = appSoftVersion;
	}

	public AppD appTrafficRule(final List<TrafficRuleDescriptor> appTrafficRule) {
		this.appTrafficRule = appTrafficRule;
		return this;
	}

	public AppD addAppTrafficRuleItem(final TrafficRuleDescriptor appTrafficRuleItem) {
		if (this.appTrafficRule == null) {
			this.appTrafficRule = new ArrayList<>();
		}
		this.appTrafficRule.add(appTrafficRuleItem);
		return this;
	}

	/**
	 * Describes traffic rules the MEC application requires.
	 *
	 * @return appTrafficRule
	 **/
	@ApiModelProperty(value = "Describes traffic rules the MEC application requires.")
	@Valid
	public List<TrafficRuleDescriptor> getAppTrafficRule() {
		return appTrafficRule;
	}

	public void setAppTrafficRule(final List<TrafficRuleDescriptor> appTrafficRule) {
		this.appTrafficRule = appTrafficRule;
	}

	public AppD changeAppInstanceStateOpConfig(final String changeAppInstanceStateOpConfig) {
		this.changeAppInstanceStateOpConfig = changeAppInstanceStateOpConfig;
		return this;
	}

	/**
	 * Get changeAppInstanceStateOpConfig
	 *
	 * @return changeAppInstanceStateOpConfig
	 **/
	@ApiModelProperty(value = "")

	public String getChangeAppInstanceStateOpConfig() {
		return changeAppInstanceStateOpConfig;
	}

	public void setChangeAppInstanceStateOpConfig(final String changeAppInstanceStateOpConfig) {
		this.changeAppInstanceStateOpConfig = changeAppInstanceStateOpConfig;
	}

	public AppD mecVersion(final List<String> mecVersion) {
		this.mecVersion = mecVersion;
		return this;
	}

	public AppD addMecVersionItem(final String mecVersionItem) {
		this.mecVersion.add(mecVersionItem);
		return this;
	}

	/**
	 * Identifies version(s) of MEC system compatible with the MEC application
	 * described in this version of the AppD.
	 *
	 * @return mecVersion
	 **/
	@ApiModelProperty(required = true, value = "Identifies version(s) of MEC system compatible with the MEC application described in this version of the AppD.")
	@NotNull

	@Size(min = 1)
	public List<String> getMecVersion() {
		return mecVersion;
	}

	public void setMecVersion(final List<String> mecVersion) {
		this.mecVersion = mecVersion;
	}

	public AppD swImageDescriptor(final String swImageDescriptor) {
		this.swImageDescriptor = swImageDescriptor;
		return this;
	}

	/**
	 * Get swImageDescriptor
	 *
	 * @return swImageDescriptor
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getSwImageDescriptor() {
		return swImageDescriptor;
	}

	public void setSwImageDescriptor(final String swImageDescriptor) {
		this.swImageDescriptor = swImageDescriptor;
	}

	public AppD terminateAppInstanceOpConfig(final String terminateAppInstanceOpConfig) {
		this.terminateAppInstanceOpConfig = terminateAppInstanceOpConfig;
		return this;
	}

	/**
	 * Get terminateAppInstanceOpConfig
	 *
	 * @return terminateAppInstanceOpConfig
	 **/
	@ApiModelProperty(value = "")

	public String getTerminateAppInstanceOpConfig() {
		return terminateAppInstanceOpConfig;
	}

	public void setTerminateAppInstanceOpConfig(final String terminateAppInstanceOpConfig) {
		this.terminateAppInstanceOpConfig = terminateAppInstanceOpConfig;
	}

	public AppD transportDependencies(final List<TransportDependency> transportDependencies) {
		this.transportDependencies = transportDependencies;
		return this;
	}

	public AppD addTransportDependenciesItem(final TransportDependency transportDependenciesItem) {
		if (this.transportDependencies == null) {
			this.transportDependencies = new ArrayList<>();
		}
		this.transportDependencies.add(transportDependenciesItem);
		return this;
	}

	/**
	 * Transports, if any, that this application requires to be provided by the
	 * platform. These transports will be used by the application to deliver
	 * services provided by this application. Only relevant for service-producing
	 * apps. See note 2.
	 *
	 * @return transportDependencies
	 **/
	@ApiModelProperty(value = "Transports, if any, that this application requires to be provided by the platform. These transports will be used by the application to deliver services provided by this application. Only relevant for service-producing apps. See note 2.")
	@Valid
	public List<TransportDependency> getTransportDependencies() {
		return transportDependencies;
	}

	public void setTransportDependencies(final List<TransportDependency> transportDependencies) {
		this.transportDependencies = transportDependencies;
	}

	public AppD virtualComputeDescriptor(final String virtualComputeDescriptor) {
		this.virtualComputeDescriptor = virtualComputeDescriptor;
		return this;
	}

	/**
	 * Get virtualComputeDescriptor
	 *
	 * @return virtualComputeDescriptor
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVirtualComputeDescriptor() {
		return virtualComputeDescriptor;
	}

	public void setVirtualComputeDescriptor(final String virtualComputeDescriptor) {
		this.virtualComputeDescriptor = virtualComputeDescriptor;
	}

	public AppD virtualStorageDescriptor(final List<String> virtualStorageDescriptor) {
		this.virtualStorageDescriptor = virtualStorageDescriptor;
		return this;
	}

	public AppD addVirtualStorageDescriptorItem(final String virtualStorageDescriptorItem) {
		if (this.virtualStorageDescriptor == null) {
			this.virtualStorageDescriptor = new ArrayList<>();
		}
		this.virtualStorageDescriptor.add(virtualStorageDescriptorItem);
		return this;
	}

	/**
	 * Defines descriptors of virtual storage resources to be used by the MEC
	 * application.
	 *
	 * @return virtualStorageDescriptor
	 **/
	@ApiModelProperty(value = "Defines descriptors of virtual storage resources to be used by the MEC application.")

	public List<String> getVirtualStorageDescriptor() {
		return virtualStorageDescriptor;
	}

	public void setVirtualStorageDescriptor(final List<String> virtualStorageDescriptor) {
		this.virtualStorageDescriptor = virtualStorageDescriptor;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AppD appD = (AppD) o;
		return Objects.equals(this.appDId, appD.appDId) &&
				Objects.equals(this.appDNSRule, appD.appDNSRule) &&
				Objects.equals(this.appDVersion, appD.appDVersion) &&
				Objects.equals(this.appDescription, appD.appDescription) &&
				Objects.equals(this.appExtCpd, appD.appExtCpd) &&
				Objects.equals(this.appFeatureOptional, appD.appFeatureOptional) &&
				Objects.equals(this.appFeatureRequired, appD.appFeatureRequired) &&
				Objects.equals(this.appInfoName, appD.appInfoName) &&
				Objects.equals(this.appLatency, appD.appLatency) &&
				Objects.equals(this.appName, appD.appName) &&
				Objects.equals(this.appProvider, appD.appProvider) &&
				Objects.equals(this.appServiceOptional, appD.appServiceOptional) &&
				Objects.equals(this.appServiceProduced, appD.appServiceProduced) &&
				Objects.equals(this.appServiceRequired, appD.appServiceRequired) &&
				Objects.equals(this.appSoftVersion, appD.appSoftVersion) &&
				Objects.equals(this.appTrafficRule, appD.appTrafficRule) &&
				Objects.equals(this.changeAppInstanceStateOpConfig, appD.changeAppInstanceStateOpConfig) &&
				Objects.equals(this.mecVersion, appD.mecVersion) &&
				Objects.equals(this.swImageDescriptor, appD.swImageDescriptor) &&
				Objects.equals(this.terminateAppInstanceOpConfig, appD.terminateAppInstanceOpConfig) &&
				Objects.equals(this.transportDependencies, appD.transportDependencies) &&
				Objects.equals(this.virtualComputeDescriptor, appD.virtualComputeDescriptor) &&
				Objects.equals(this.virtualStorageDescriptor, appD.virtualStorageDescriptor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(appDId, appDNSRule, appDVersion, appDescription, appExtCpd, appFeatureOptional, appFeatureRequired, appInfoName, appLatency, appName, appProvider, appServiceOptional, appServiceProduced, appServiceRequired, appSoftVersion, appTrafficRule, changeAppInstanceStateOpConfig, mecVersion, swImageDescriptor, terminateAppInstanceOpConfig, transportDependencies, virtualComputeDescriptor, virtualStorageDescriptor);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AppD {\n");

		sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
		sb.append("    appDNSRule: ").append(toIndentedString(appDNSRule)).append("\n");
		sb.append("    appDVersion: ").append(toIndentedString(appDVersion)).append("\n");
		sb.append("    appDescription: ").append(toIndentedString(appDescription)).append("\n");
		sb.append("    appExtCpd: ").append(toIndentedString(appExtCpd)).append("\n");
		sb.append("    appFeatureOptional: ").append(toIndentedString(appFeatureOptional)).append("\n");
		sb.append("    appFeatureRequired: ").append(toIndentedString(appFeatureRequired)).append("\n");
		sb.append("    appInfoName: ").append(toIndentedString(appInfoName)).append("\n");
		sb.append("    appLatency: ").append(toIndentedString(appLatency)).append("\n");
		sb.append("    appName: ").append(toIndentedString(appName)).append("\n");
		sb.append("    appProvider: ").append(toIndentedString(appProvider)).append("\n");
		sb.append("    appServiceOptional: ").append(toIndentedString(appServiceOptional)).append("\n");
		sb.append("    appServiceProduced: ").append(toIndentedString(appServiceProduced)).append("\n");
		sb.append("    appServiceRequired: ").append(toIndentedString(appServiceRequired)).append("\n");
		sb.append("    appSoftVersion: ").append(toIndentedString(appSoftVersion)).append("\n");
		sb.append("    appTrafficRule: ").append(toIndentedString(appTrafficRule)).append("\n");
		sb.append("    changeAppInstanceStateOpConfig: ").append(toIndentedString(changeAppInstanceStateOpConfig)).append("\n");
		sb.append("    mecVersion: ").append(toIndentedString(mecVersion)).append("\n");
		sb.append("    swImageDescriptor: ").append(toIndentedString(swImageDescriptor)).append("\n");
		sb.append("    terminateAppInstanceOpConfig: ").append(toIndentedString(terminateAppInstanceOpConfig)).append("\n");
		sb.append("    transportDependencies: ").append(toIndentedString(transportDependencies)).append("\n");
		sb.append("    virtualComputeDescriptor: ").append(toIndentedString(virtualComputeDescriptor)).append("\n");
		sb.append("    virtualStorageDescriptor: ").append(toIndentedString(virtualStorageDescriptor)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
