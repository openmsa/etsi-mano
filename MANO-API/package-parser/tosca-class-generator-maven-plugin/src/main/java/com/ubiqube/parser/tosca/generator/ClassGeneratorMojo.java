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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.ubiqube.parser.tosca.ParseException;

@Mojo(name = "tosca-class-generator", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ClassGeneratorMojo extends AbstractMojo {

	@Parameter(property = "files")
	private List<File> files;

	@Parameter(defaultValue = "${project.build.directory}/generated-sources/tosca", required = true)
	private File outputDirectory;

	/**
	 * The injected Maven project.
	 */
	@Parameter(defaultValue = "${project}", readonly = true)
	private MavenProject project;

	@Parameter(defaultValue = "")
	private String packagePrefix;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		final JavaPoetWalker jw = new JavaPoetWalker(outputDirectory.getAbsolutePath());
		final ToscaWalker tw = new ToscaWalker();
		files.forEach(x -> {
			getLog().info("Starting class generation using: " + x);
			tw.generate(x.getAbsolutePath(), jw);
		});
		try {
			getProject().addCompileSourceRoot(outputDirectory.getCanonicalPath());
		} catch (final IOException e) {
			throw new MojoFailureException("Unable to add resources.", e);
		}
	}

	/**
	 * @return The active MavenProject.
	 */
	private final MavenProject getProject() {
		return getInjectedObject(project, "project");
	}

	private <T> T getInjectedObject(final T objectOrNull, final String objectName) {
		if (objectOrNull == null) {
			getLog().error(
					"Found null '" + objectName + "', implying that Maven @Component injection was not done properly.");
			throw new ParseException("");
		}
		return objectOrNull;
	}

}
