package com.ubiqube.etsi.mano.json;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class CustomSerializer extends BeanSerializerModifier {
	private final List<ViewHolder> excluded;
	private final List<ViewHolder> wanted;

	public CustomSerializer(@Nonnull List<ViewHolder> _viewHolders, @Nonnull List<ViewHolder> _wantedList) {
		excluded = _viewHolders;
		wanted = _wantedList;
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
