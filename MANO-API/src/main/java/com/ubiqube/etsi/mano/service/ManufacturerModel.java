package com.ubiqube.etsi.mano.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcelo.ses.entities.device.Manufacturer;
import com.netcelo.ses.entities.device.Model;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;

/**
 *
 * @author olivier
 *
 */
@Service
public class ManufacturerModel {
	private final DeviceService deviceBean;
	private final Map<Long, Manufacturer> manufacturers;

	@Autowired
	public ManufacturerModel(DeviceService _devicebeService) {
		deviceBean = _devicebeService;
		try {
			manufacturers = deviceBean.getAvailableManufacturers();
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	public String getManufacturerById(String _id) {
		final Manufacturer manufacturer = manufacturers.get(Long.parseLong(_id));
		if (null == manufacturer) {
			throw new NotFoundException("Manufacturer not found [" + _id + "]");
		}
		return manufacturer.getName();
	}

	public String getModelById(String _manufacturerId, String _modelId) {
		final Manufacturer manufacturer = manufacturers.get(Long.parseLong(_manufacturerId));
		final Model model = manufacturer.getModel(Long.parseLong(_modelId));
		if (null == model) {
			throw new NotFoundException("Model not found [" + _modelId + "]");
		}
		return model.getName();
	}

}
