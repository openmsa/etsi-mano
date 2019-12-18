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
			LOG.debug("Poping context => {} restoring => {}", currentClassName, context.currentClassName);
			currentClass = context.currentClass;
			currentField = context.currentField;
			currentClassName = context.currentClassName;
		}
	}

	protected abstract void onClassTerminate();

	class Context {
		protected JDefinedClass currentClass = null;
		protected JFieldVar currentField;
		protected String currentClassName;

		public Context(final JDefinedClass _currentClass, final JFieldVar _currentField, final String _currentClassName) {
			currentClass = _currentClass;
			currentField = _currentField;
			currentClassName = _currentClassName;
		}
	}
}
