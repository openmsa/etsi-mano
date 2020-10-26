package com.ubiqube.etsi.mano.service.plan;

import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;

public interface ScalingStrategy {
	class NumberOfCompute {
		private int current;
		private int wanted;

		public NumberOfCompute(final int current, final int wanted) {
			super();
			this.current = current;
			this.wanted = wanted;
		}

		public int getCurrent() {
			return current;
		}

		public void setCurrent(final int current) {
			this.current = current;
		}

		public int getWanted() {
			return wanted;
		}

		public void setWanted(final int wanted) {
			this.wanted = wanted;
		}

	}

	NumberOfCompute getNumberOfCompute(Blueprint plan, VnfPackage vnfPackage, Set<ScaleInfo> scaling, VnfCompute x);
}
