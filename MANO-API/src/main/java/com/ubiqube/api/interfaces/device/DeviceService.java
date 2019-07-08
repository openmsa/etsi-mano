package com.ubiqube.api.interfaces.device;

import com.ubiqube.api.commons.id.DeviceId;
import com.ubiqube.api.entities.device.SimpleDevice;
import com.ubiqube.api.exception.ServiceException;


public interface DeviceService {

	public DeviceId getDeviceId(String nsInstanceId) throws ServiceException;
	public void deleteDevice(DeviceId deviceId, String name) throws ServiceException;
	public SimpleDevice getDeviceModeleAndManId(DeviceId deviceId) throws ServiceException;
}
