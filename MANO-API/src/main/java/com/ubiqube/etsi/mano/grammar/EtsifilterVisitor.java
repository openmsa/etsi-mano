// Generated from Etsifilter.g4 by ANTLR 4.4

	package com.ubiqube.etsi.mano.grammar;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Etsifilter}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EtsifilterVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Etsifilter#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(@NotNull Etsifilter.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Etsifilter#simpleFilterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFilterExpr(@NotNull Etsifilter.SimpleFilterExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Etsifilter#filterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpr(@NotNull Etsifilter.FilterExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Etsifilter#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull Etsifilter.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Etsifilter#attrName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrName(@NotNull Etsifilter.AttrNameContext ctx);
}