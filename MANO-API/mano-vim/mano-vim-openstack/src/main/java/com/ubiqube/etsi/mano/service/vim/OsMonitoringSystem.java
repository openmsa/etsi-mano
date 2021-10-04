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
package com.ubiqube.etsi.mano.service.vim;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.telemetry.Alarm;
import org.openstack4j.model.telemetry.Alarm.AggregationMethod;
import org.openstack4j.model.telemetry.Alarm.ComparisonOperator;
import org.openstack4j.model.telemetry.Alarm.Type;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm.CeilometerGnocchiResourcesThresholdRule;

import com.ubiqube.etsi.mano.dao.mano.VnfMonitoringParameter;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

public class OsMonitoringSystem {

	private final OSClientV3 os;

	private final Properties props;

	public OsMonitoringSystem(final OSClientV3 os) {
		this.os = os;
		try (InputStream mappting = this.getClass().getClassLoader().getResourceAsStream("gnocchi-mapping.properties")) {
			props = new Properties();
			props.load(mappting);
		} catch (final IOException e) {
			throw new VimGenericException(e);
		}
	}

	private Metric map(final String x) {
		final String prop = props.getProperty(x);
		if (null == prop) {
			throw new VimGenericException("Unable to map monitoring key : " + x);
		}
		final String[] metric = prop.split(",");
		if (metric.length != 2) {
			throw new VimGenericException("bad mapping key : " + x + "/" + prop + ". Should have one ','");
		}
		return new Metric(metric[0], MetricFunction.fromValue(metric[1]));
	}

	public String getProviderId() {
		return "OS-GNOCCHI-V1";
	}

	public SystemBuilder getImplementation(final OrchestrationService orchestrationService, final VirtualTask<VnfMonitoringParameter> virtualTask, final SystemConnections vim) {
		final VnfMonitoringParameter mp = virtualTask.getParameters();
		// final MonitoringTask task = createTask(MonitoringTask::new);
		// task.setVnfCompute(mp.getVnfCompute());
		// task.setAlias(mp.getName());
		// task.setBlueprint(mp.getBlueprint());
		// task.setChangeType(ChangeType.ADDED);
		// task.setType(ResourceTypeEnum.MONITORING);
		// task.setMonitoringParams(mp.getMonitoringParameters());
		// task.setToscaName(mp.getName());
		// return SystemBuilder.of(new MonitoringUow());
		return null;
	}

	public String registerAlarm(final UUID x, final String performanceMetric, final Double thresholdValue, final Double hysteresis, final String url) {
		final CeilometerGnocchiResourcesThresholdRule rule = new CeilometerAlarm.CeilometerGnocchiResourcesThresholdRule();
		final Metric metric = map(performanceMetric);
		rule.setAggregationMethod(AggregationMethod.MEAN);
		rule.setThreshold(thresholdValue.floatValue());
		rule.setComparisonOperator(ComparisonOperator.GT);
		// XXX: If it's not equal to original metric it will fail.
		rule.setEvaluationPeriods(600);
		rule.setMetric(metric.getName());
		rule.setResourceId(x.toString());
		final Alarm alarm = Builders.alarm()
				.alarmActions(Arrays.asList(url))
				.gnocchiResourcesThresholdRule(rule)
				.type(Type.THRESHOLD)
				.build();
		os.telemetry().alarms().create(alarm);
		return null;
	}

	public boolean removeAlarm(final String resource) {
		final ActionResponse resp = os.telemetry().alarms().delete(resource);
		return resp.isSuccess();
	}

}
