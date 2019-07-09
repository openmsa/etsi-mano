package com.ubiqube.etsi.mano.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ubiqube.api.commons.id.DeviceId;
import com.ubiqube.api.entities.device.Manufacturer;
import com.ubiqube.api.entities.device.SimpleDevice;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;

@Service
public class DeviceServiceRest implements DeviceService {

	@Override
	public DeviceId getDeviceId(String nsInstanceId) throws ServiceException {
		return null;
	}

	@Override
	public void deleteDevice(DeviceId deviceId, String name) throws ServiceException {
		//
	}

	@Override
	public SimpleDevice getDeviceModeleAndManId(DeviceId deviceId) throws ServiceException {
		return null;
	}

	@Override
	public Map<Long, Manufacturer> getAvailableManufacturers() throws ServiceException {
		return null;
	}

}
