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
// Generated from EtsiFilterV25.g4 by ANTLR 4.7.2

	package com.ubiqube.etsi.mano.grammar.v25;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EtsiFilterV25}.
 */
public interface EtsiFilterV25Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#simpleFilterExprOne}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFilterExprOne(EtsiFilterV25.SimpleFilterExprOneContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#simpleFilterExprOne}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFilterExprOne(EtsiFilterV25.SimpleFilterExprOneContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#simpleFilterExprMulti}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFilterExprMulti(EtsiFilterV25.SimpleFilterExprMultiContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#simpleFilterExprMulti}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFilterExprMulti(EtsiFilterV25.SimpleFilterExprMultiContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#simpleFilterExpr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFilterExpr(EtsiFilterV25.SimpleFilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#simpleFilterExpr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFilterExpr(EtsiFilterV25.SimpleFilterExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#filterExpr}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpr(EtsiFilterV25.FilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#filterExpr}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpr(EtsiFilterV25.FilterExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(EtsiFilterV25.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(EtsiFilterV25.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#opOne}.
	 * @param ctx the parse tree
	 */
	void enterOpOne(EtsiFilterV25.OpOneContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#opOne}.
	 * @param ctx the parse tree
	 */
	void exitOpOne(EtsiFilterV25.OpOneContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#opMulti}.
	 * @param ctx the parse tree
	 */
	void enterOpMulti(EtsiFilterV25.OpMultiContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#opMulti}.
	 * @param ctx the parse tree
	 */
	void exitOpMulti(EtsiFilterV25.OpMultiContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#attrName}.
	 * @param ctx the parse tree
	 */
	void enterAttrName(EtsiFilterV25.AttrNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#attrName}.
	 * @param ctx the parse tree
	 */
	void exitAttrName(EtsiFilterV25.AttrNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EtsiFilterV25#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(EtsiFilterV25.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link EtsiFilterV25#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(EtsiFilterV25.ValueContext ctx);
}