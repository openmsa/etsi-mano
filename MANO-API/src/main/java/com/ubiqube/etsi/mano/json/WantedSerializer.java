package com.ubiqube.etsi.mano.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class WantedSerializer extends BeanSerializerModifier {
	private final Set<String> list;

	public WantedSerializer(final List<String> _list) {
		list = new HashSet<>();
		for (final String string : _list) {
			final String[] part = string.split("\\.");
			list.addAll(Arrays.asList(part));
		}
	}

	@Override
	public List<BeanPropertyWriter> changeProperties(final SerializationConfig config, final BeanDescription beanDesc, final List<BeanPropertyWriter> beanProperties) {
		final List<BeanPropertyWriter> result = new ArrayList<>();

		for (final BeanPropertyWriter beanPropertyWriter : beanProperties) {
			final String partName = beanPropertyWriter.getName();
			if (list.contains(partName)) {
				result.add(beanPropertyWriter);
			}
		}
		return result;
	}

}
