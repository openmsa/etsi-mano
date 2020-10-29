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
package com.ubiqube.ejb;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.support.ThreadPoolUtil;
import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.graph.TestUnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public class WfTest {

	@Test
	void testName() throws Exception {
		final ExecutorService executorService = Executors.newFixedThreadPool(ThreadPoolUtil.ioIntesivePoolSize());
		final UnitOfWork start = new TestUnitOfWork("Start");
		final UnitOfWork end = new TestUnitOfWork("End");

		final UnitOfWork vduA = new TestUnitOfWork("A");
		final UnitOfWork vduB = new TestUnitOfWork("B");
		final UnitOfWork vduC = new TestUnitOfWork("C");
		final UnitOfWork vduD = new TestUnitOfWork("D");
		final UnitOfWork vduE = new TestUnitOfWork("E");
		final UnitOfWork vduF = new TestUnitOfWork("F");

		final DexecutorConfig<UnitOfWork, Integer> config = new DexecutorConfig<>(executorService, new SleepyTaskProvider());
		// config.setExecutionListener(listener);
		final DefaultDexecutor<UnitOfWork, Integer> executor = new DefaultDexecutor<>(config);
		executor.addDependency(vduA, vduB);
		executor.addDependency(vduA, vduC);

		executor.addDependency(vduB, vduD);
		executor.addDependency(vduC, vduD);

		executor.addDependency(vduE, vduC);
		executor.addDependency(vduE, vduF);

		executor.addAsDependencyToAllInitialNodes(start);
		executor.addAsDependentOnAllLeafNodes(end);
		// executor.print((x,y) -> {}, action);
		final ExecutionResults<UnitOfWork, Integer> res = executor.execute(ExecutionConfig.NON_TERMINATING);
		assertNotNull(res);
	}
	
	
	@Test
    void testName2() throws Exception {
        File file = new File("/home/olivier/workspace/workspace17.1.1/msa-api2/test.txt");
        System.out.println(""  + file.getName());
    }
}
