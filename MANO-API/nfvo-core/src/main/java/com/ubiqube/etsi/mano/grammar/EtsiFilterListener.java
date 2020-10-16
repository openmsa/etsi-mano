// Generated from EtsiFilter.g4 by ANTLR 4.7.2

	package com.ubiqube.etsi.mano.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EtsiFilter}.
 */
public interface EtsiFilterListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EtsiFilter#simpleFilterExpr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFilterExpr(EtsiFilter.SimpleFilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilter#simpleFilterExpr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFilterExpr(EtsiFilter.SimpleFilterExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilter#filterExpr}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpr(EtsiFilter.FilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilter#filterExpr}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpr(EtsiFilter.FilterExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilter#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(EtsiFilter.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilter#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(EtsiFilter.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilter#attrName}.
	 * @param ctx the parse tree
	 */
	void enterAttrName(EtsiFilter.AttrNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilter#attrName}.
	 * @param ctx the parse tree
	 */
	void exitAttrName(EtsiFilter.AttrNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilter#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(EtsiFilter.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilter#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(EtsiFilter.ValueContext ctx);
}