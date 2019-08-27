// Generated from EtsiFilterV25.g4 by ANTLR 4.7.2

	package com.ubiqube.etsi.mano.grammar.v25;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EtsiFilterV25}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EtsiFilterV25Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#simpleFilterExprOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFilterExprOne(EtsiFilterV25.SimpleFilterExprOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#simpleFilterExprMulti}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFilterExprMulti(EtsiFilterV25.SimpleFilterExprMultiContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#simpleFilterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFilterExpr(EtsiFilterV25.SimpleFilterExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#filterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpr(EtsiFilterV25.FilterExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(EtsiFilterV25.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#opOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpOne(EtsiFilterV25.OpOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#opMulti}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpMulti(EtsiFilterV25.OpMultiContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#attrName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrName(EtsiFilterV25.AttrNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EtsiFilterV25#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(EtsiFilterV25.ValueContext ctx);
}