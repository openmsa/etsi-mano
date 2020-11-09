/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
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
