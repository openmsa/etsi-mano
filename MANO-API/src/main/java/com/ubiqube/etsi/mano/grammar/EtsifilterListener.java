// Generated from Etsifilter.g4 by ANTLR 4.4

	package com.ubiqube.etsi.mano.grammar;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Etsifilter}.
 */
public interface EtsifilterListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Etsifilter#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(@NotNull Etsifilter.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Etsifilter#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(@NotNull Etsifilter.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link Etsifilter#simpleFilterExpr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFilterExpr(@NotNull Etsifilter.SimpleFilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Etsifilter#simpleFilterExpr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFilterExpr(@NotNull Etsifilter.SimpleFilterExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Etsifilter#filterExpr}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpr(@NotNull Etsifilter.FilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Etsifilter#filterExpr}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpr(@NotNull Etsifilter.FilterExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Etsifilter#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull Etsifilter.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Etsifilter#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull Etsifilter.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Etsifilter#attrName}.
	 * @param ctx the parse tree
	 */
	void enterAttrName(@NotNull Etsifilter.AttrNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Etsifilter#attrName}.
	 * @param ctx the parse tree
	 */
	void exitAttrName(@NotNull Etsifilter.AttrNameContext ctx);
}
