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
package com.ubiqube.etsi.mano.test.controllers;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = { "com.ubiqube.etsi.mano" }, importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchMainTest {

	@ArchTest
	public static final ArchRule ensure_interface_name = classes()
			.that().resideInAPackage("..v*.controller..").and().areInterfaces()
			.should().haveNameMatching(".*[0-9]{3}Sol0[0-1][1-6]Api");

	@ArchTest
	public static final ArchRule ensure_interface_annotations = classes()
			.that().resideInAPackage("..v*.controller..").and().areInterfaces()
			.should().beAnnotatedWith(RolesAllowed.class).andShould().beAnnotatedWith(RequestMapping.class);

	@ArchTest
	public static final ArchRule ensure_controller_name = classes()
			.that().resideInAPackage("..v*.controller..").and().areNotInterfaces()
			.should().haveNameMatching(".*[0-9]{3}Sol0[0-1][1-6]Controller");

	@ArchTest
	public static final ArchRule ensure_rest_controller = classes()
			.that().resideInAPackage("..v*.controller..").and().areNotInterfaces()
			.should().beAnnotatedWith(RestController.class);

	/**
	 * @ArchTest public static final ArchRule no_cycle = slices()
	 *           .matching("com.ubiqube.etsi.mano.(*)..")
	 *           .should().beFreeOfCycles();
	 */
}
