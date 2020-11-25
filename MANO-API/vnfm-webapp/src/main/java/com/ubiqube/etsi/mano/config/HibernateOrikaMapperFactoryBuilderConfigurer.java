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

package com.ubiqube.etsi.mano.config;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;
import ma.glasnost.orika.unenhance.HibernateUnenhanceStrategy;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryBuilderConfigurer;

@Component
public class HibernateOrikaMapperFactoryBuilderConfigurer implements OrikaMapperFactoryBuilderConfigurer {

	@Override
	public void configure(final MapperFactoryBuilder<?, ?> orikaMapperFactoryBuilder) {
		orikaMapperFactoryBuilder.unenhanceStrategy(new HibernateUnenhanceStrategy());
	}

}
