package com.ubiqube.parser.tosca.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Repeatable(Members.List.class)
public @interface Members {

	String value();

	@Documented
	@Retention(RUNTIME)
	@Target({ FIELD, METHOD, ANNOTATION_TYPE })
	@interface List {
		Members[] value();
	}
}
