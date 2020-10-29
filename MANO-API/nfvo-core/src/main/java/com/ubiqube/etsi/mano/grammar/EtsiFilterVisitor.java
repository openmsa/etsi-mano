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