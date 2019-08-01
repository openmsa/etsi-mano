package com.ubiqube.etsi.mano.json;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class ExclusionSerializer extends BeanSerializerModifier {
	private final List<ViewHolder> excluded;

	public ExclusionSerializer(@Nonnull List<ViewHolder> _excluded) {
		excluded = _excluded;
	}

	@Override
	public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
		final List<BeanPropertyWriter> result = new ArrayList<>();
		for (final BeanPropertyWriter beanPropertyWriter : beanProperties) {
			final String partName = beanPropertyWriter.getName();
			boolean shouldAdd = true;
			for (final ViewHolder viewHolder : excluded) {
				if (viewHolder.shouldRemove(partName)) {
					shouldAdd = false;
				}
			}
			if (shouldAdd) {
				result.add(beanPropertyWriter);
			}
		}
		return result;
	}
}
