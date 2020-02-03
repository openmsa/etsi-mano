package com.ubiqube.etsi.mano.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ubiqube.etsi.mano.config.Version;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {
	@GetMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

	@GetMapping(value = "/test/{id}")
	public ResponseEntity<VnfPackage> test(@PathVariable("id") final VnfPackage vnfPackage) {
		return ResponseEntity.ok(vnfPackage);
	}

	@GetMapping(value = "/version")
	public ResponseEntity<VersionBean> version() {
		return ResponseEntity.ok(new VersionBean());
	}

	class VersionBean {
		private String mavenGroup = Version.MAVEN_GROUP;
		private String mavenName = Version.MAVEN_NAME;
		private String version = Version.VERSION;
		private int gitRevision = Version.GIT_REVISION;
		private String gitSha = Version.GIT_SHA;
		private String gitDate = Version.GIT_DATE;
		private String buildDate = Version.BUILD_DATE;
		private long buildUnixTime = Version.BUILD_UNIX_TIME;

		public String getMavenGroup() {
			return mavenGroup;
		}

		public void setMavenGroup(final String mavenGroup) {
			this.mavenGroup = mavenGroup;
		}

		public String getMavenName() {
			return mavenName;
		}

		public void setMavenName(final String mavenName) {
			this.mavenName = mavenName;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(final String version) {
			this.version = version;
		}

		public int getGitRevision() {
			return gitRevision;
		}

		public void setGitRevision(final int gitRevision) {
			this.gitRevision = gitRevision;
		}

		public String getGitSha() {
			return gitSha;
		}

		public void setGitSha(final String gitSha) {
			this.gitSha = gitSha;
		}

		public String getGitDate() {
			return gitDate;
		}

		public void setGitDate(final String gitDate) {
			this.gitDate = gitDate;
		}

		public String getBuildDate() {
			return buildDate;
		}

		public void setBuildDate(final String buildDate) {
			this.buildDate = buildDate;
		}

		public long getBuildUnixTime() {
			return buildUnixTime;
		}

		public void setBuildUnixTime(final long buildUnixTime) {
			this.buildUnixTime = buildUnixTime;
		}

	}
}
