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