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
package com.ubiqube.parser.tosca.generator;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

public abstract class AbstractWalker implements ToscaListener {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractWalker.class);
	private final LinkedList<Context> stack = new LinkedList<>();
	protected JDefinedClass currentClass = null;
	protected JFieldVar currentField;
	protected String currentClassName;

	@Override
	public void startClass(final String className) {
		if (null != currentClass) {
			final Context ctx = new Context(currentClass, currentField, currentClassName);
			stack.push(ctx);
			LOG.debug("Pushing context => {}", currentClassName);
		}
		onClassStart(className);
	}

	protected abstract void onClassStart(String className);

	@Override
	public void terminateClass() {
		onClassTerminate();
		if (!stack.isEmpty()) {
			final Context context = stack.pop();
			LOG.debug("Poping context => {} restoring => {}", currentClassName, context.lCurrentClassName);
			currentClass = context.lCurrentClass;
			currentField = context.lCurrentField;
			currentClassName = context.lCurrentClassName;
		}
	}

	protected abstract void onClassTerminate();

	class Context {
		protected JDefinedClass lCurrentClass = null;
		protected JFieldVar lCurrentField;
		protected String lCurrentClassName;

		public Context(final JDefinedClass currentClass, final JFieldVar currentField, final String currentClassName) {
			this.lCurrentClass = currentClass;
			this.lCurrentField = currentField;
			this.lCurrentClassName = currentClassName;
		}
	}
}
