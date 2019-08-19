// Generated from EtsiFilter.g4 by ANTLR 4.7.2

	package com.ubiqube.etsi.mano.grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EtsiFilter}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EtsiFilterVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EtsiFilter#simpleFilterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFilterExpr(EtsiFilter.SimpleFilterExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilter#filterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpr(EtsiFilter.FilterExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilter#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(EtsiFilter.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilter#attrName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrName(EtsiFilter.AttrNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilter#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(EtsiFilter.ValueContext ctx);
}