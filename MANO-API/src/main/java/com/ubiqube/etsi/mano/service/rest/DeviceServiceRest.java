package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ubiqube.api.commons.id.DeviceId;
import com.ubiqube.api.entities.device.Manufacturer;
import com.ubiqube.api.entities.device.Model;
import com.ubiqube.api.entities.device.SimpleDevice;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;

@Profile("default")
@Service
public class DeviceServiceRest implements DeviceService {

	private final UbiRest rest;

	public DeviceServiceRest(final UbiRest rest) {
		super();
		this.rest = rest;
	}

	@Override
	public DeviceId getDeviceId(final String nsInstanceId) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("device/v1/reference/" + nsInstanceId)
				.build()
				.toUri();
		return rest.get(uri, DeviceIdModel.class);
	}

	static class DeviceIdModel implements DeviceId {
		public long id; // : 125,
		public String prefix; // : "TST",
		public String ubiId; // : "TST125",
		public String name; // : "self",
		public String externalReference; // : "TST125",
		public long operatorId; // : 0,
		public String displayName; // : "self - TST125",
		public String displayNameForJsps; // : "self - TST125"

		@Override
		public String getUbiId() {
			return ubiId;
		}
	}

	@Override
	public void deleteDevice(final DeviceId deviceId, final String name) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("device/reference/" + deviceId.getUbiId())
				.build()
				.toUri();
		rest.delete(uri, String.class);
	}

	@Override
	public SimpleDevice getDeviceModeleAndManId(final DeviceId deviceId) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("device/v1/device")
				.queryParam("deviceId", deviceId)
				.build()
				.toUri();
		return rest.get(uri, SimpleDeviceModel.class);
	}

	static class SimpleDeviceModel implements SimpleDevice {

		@Override
		public DeviceId getUbiqubeId() {
			return null;
		}

		@Override
		public String getName() {
			return null;
		}
	}

	@Override
	public Map<Long, Manufacturer> getAvailableManufacturers() throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("device/v1/manufacturers")
				.build()
				.toUri();
		return toHashMap(new ArrayList<Manufacturer>(
				rest.get(uri, ListOfManufacturers.class)));
	}

	static class ManufacturerModel implements Manufacturer {
		Map<Long, ModelModel> _models;

		public int manufacturerId;
		public String manufacturerName;
		public ArrayList<ModelModel> models;

		@Override
		public Model getModel(final long modelId) {
			if (_models == null) {
				_models = toHashMap(models);
			}
			return _models.get(modelId);
		}

		@Override
		public String getName() {
			return manufacturerName;
		}

		@Override
		public int hashCode() {
			return manufacturerId;
		}
	}

	static class ModelModel implements Model {
		public String modelName;
		public int modelId;

		@Override
		public String getName() {
			return modelName;
		}

		@Override
		public int hashCode() {
			return modelId;
		}
	}

	static class ListOfManufacturers extends ArrayList<ManufacturerModel> {
	}

	static <T> Map<Long, T> toHashMap(final ArrayList<T> list) {
		final Map<Long, T> res = list.stream().collect(Collectors.toMap(x -> (long) x.hashCode(), x -> x));
		return res;
	}

}
