package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@EntityListeners(AuditListener.class)
public class VnfCompute implements BaseEntity, Auditable, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String toscaId;

	private String toscaName;

	private String state;

	private String name;

	private String description;

	private String bootData;

	private long virtualMemorySize;

	private String cpuArchitecture;

	private long numVcpu;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SoftwareImage softwareImage;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> storages;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> networks;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<MonitoringParams> monitoringParameters;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vnfCompute")
	private Set<VduInstantiationLevel> instantiationLevel;

	/**
	 * Initial delta.
	 */
	private Integer initialNumberOfInstance;

	@Embedded
	private Audit audit;

	@Embedded
	private VduProfile vduProfile;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vnfCompute")
	private List<VnfComputeAspectDelta> scalingAspectDeltas;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SoftwareImage getSoftwareImage() {
		return softwareImage;
	}

	public void setSoftwareImage(final SoftwareImage _softwareImage) {
		softwareImage = _softwareImage;
	}

	public String getToscaId() {
		return toscaId;
	}

	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

	public String getToscaName() {
		return toscaName;
	}

	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public Set<String> getStorages() {
		return storages;
	}

	public void setStorages(final Set<String> storages) {
		this.storages = storages;
	}

	public Set<MonitoringParams> getMonitoringParameters() {
		return monitoringParameters;
	}

	public void setMonitoringParameters(final Set<MonitoringParams> monitoringParameters) {
		this.monitoringParameters = monitoringParameters;
	}

	public long getVirtualMemorySize() {
		return virtualMemorySize;
	}

	public void setVirtualMemorySize(final long virtualMemorySize) {
		this.virtualMemorySize = virtualMemorySize;
	}

	public String getCpuArchitecture() {
		return cpuArchitecture;
	}

	public void setCpuArchitecture(final String cpuArchitecture) {
		this.cpuArchitecture = cpuArchitecture;
	}

	public long getNumVcpu() {
		return numVcpu;
	}

	public void setNumVcpu(final long numVcpu) {
		this.numVcpu = numVcpu;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public Set<String> getNetworks() {
		return networks;
	}

	public void setNetworks(final Set<String> networks) {
		this.networks = networks;
	}

	public VduProfile getVduProfile() {
		return vduProfile;
	}

	public void setVduProfile(final VduProfile vduProfile) {
		this.vduProfile = vduProfile;
	}

	public Set<VduInstantiationLevel> getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final Set<VduInstantiationLevel> instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public Integer getInitialNumberOfInstance() {
		return initialNumberOfInstance;
	}

	public void setInitialNumberOfInstance(final Integer initialNumberOfInstance) {
		this.initialNumberOfInstance = initialNumberOfInstance;
	}

	public List<VnfComputeAspectDelta> getScalingAspectDeltas() {
		return scalingAspectDeltas;
	}

	public void setScalingAspectDeltas(final List<VnfComputeAspectDelta> scalingAspectDeltas) {
		this.scalingAspectDeltas = scalingAspectDeltas;
	}

	public void addScalingAspectDeltas(final VnfComputeAspectDelta scalingDelta) {
		if (null == scalingAspectDeltas) {
			scalingAspectDeltas = new ArrayList<>();
		}
		scalingDelta.setVnfCompute(this);
		scalingAspectDeltas.add(scalingDelta);
	}

	public String getBootData() {
		return bootData;
	}

	public void setBootData(final String bootData) {
		this.bootData = bootData;
	}

	public void addVduInstantiationLevel(final VduInstantiationLevel _vduInstantiationLevel) {
		if (null == instantiationLevel) {
			instantiationLevel = new LinkedHashSet<>();
		}
		instantiationLevel.add(_vduInstantiationLevel);
	}

}
