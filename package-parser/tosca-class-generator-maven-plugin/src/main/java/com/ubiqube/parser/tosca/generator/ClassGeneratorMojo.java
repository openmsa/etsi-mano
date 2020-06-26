package com.ubiqube.parser.tosca.generator;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "tosca-class-generator", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ClassGeneratorMojo extends AbstractMojo {

	@Parameter(property = "file")
	private File file;

	@Parameter(defaultValue = "${project.build.directory}/generated-sources/tosca", required = true)
	private File outputDirectory;

	/**
	 * The injected Maven project.
	 */
	@Parameter(defaultValue = "${project}", readonly = true)
	private MavenProject project;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Starting class generation using: " + file);
		final JavaWalker jw = new JavaWalker(outputDirectory.getAbsolutePath());
		final ToscaWalker tw = new ToscaWalker();
		tw.generate(file.getAbsolutePath(), jw);
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
		}
		return objectOrNull;
	}

}
