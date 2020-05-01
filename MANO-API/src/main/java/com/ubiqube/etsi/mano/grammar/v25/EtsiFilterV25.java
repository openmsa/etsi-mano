// Generated from EtsiFilterV25.g4 by ANTLR 4.7.2

package com.ubiqube.etsi.mano.grammar.v25;

import java.util.List;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class EtsiFilterV25 extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int COMMA = 1, SLASH = 2, OPEN_BRACKET = 3, CLOSE_BRACKET = 4, SEMICOLON = 5, EQUAL = 6,
			EQ = 7, NEQ = 8, GT = 9, LT = 10, GTE = 11, LTE = 12, IN = 13, NIN = 14, CONT = 15, NCONT = 16,
			FILTER = 17, ATTRIBUTE = 18, STRING = 19;
	public static final int RULE_simpleFilterExprOne = 0, RULE_simpleFilterExprMulti = 1, RULE_simpleFilterExpr = 2,
			RULE_filterExpr = 3, RULE_filter = 4, RULE_opOne = 5, RULE_opMulti = 6,
			RULE_attrName = 7, RULE_value = 8;

	private static String[] makeRuleNames() {
		return new String[] {
				"simpleFilterExprOne", "simpleFilterExprMulti", "simpleFilterExpr", "filterExpr",
				"filter", "opOne", "opMulti", "attrName", "value"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
				null, "','", "'/'", "'('", "')'", "';'", "'='", "'eq'", "'neq'", "'gt'",
				"'lt'", "'gte'", "'lte'", "'in'", "'nin'", "'cont'", "'ncont'", "'filter'"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	private static String[] makeSymbolicNames() {
		return new String[] {
				null, "COMMA", "SLASH", "OPEN_BRACKET", "CLOSE_BRACKET", "SEMICOLON",
				"EQUAL", "EQ", "NEQ", "GT", "LT", "GTE", "LTE", "IN", "NIN", "CONT",
				"NCONT", "FILTER", "ATTRIBUTE", "STRING"
		};
	}

	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() {
		return "EtsiFilterV25.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public EtsiFilterV25(final TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class SimpleFilterExprOneContext extends ParserRuleContext {
		public OpOneContext opOne() {
			return getRuleContext(OpOneContext.class, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(EtsiFilterV25.COMMA);
		}

		public TerminalNode COMMA(final int i) {
			return getToken(EtsiFilterV25.COMMA, i);
		}

		public List<AttrNameContext> attrName() {
			return getRuleContexts(AttrNameContext.class);
		}

		public AttrNameContext attrName(final int i) {
			return getRuleContext(AttrNameContext.class, i);
		}

		public ValueContext value() {
			return getRuleContext(ValueContext.class, 0);
		}

		public List<TerminalNode> SLASH() {
			return getTokens(EtsiFilterV25.SLASH);
		}

		public TerminalNode SLASH(final int i) {
			return getToken(EtsiFilterV25.SLASH, i);
		}

		public SimpleFilterExprOneContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_simpleFilterExprOne;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterSimpleFilterExprOne(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitSimpleFilterExprOne(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitSimpleFilterExprOne(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final SimpleFilterExprOneContext simpleFilterExprOne() throws RecognitionException {
		final SimpleFilterExprOneContext _localctx = new SimpleFilterExprOneContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_simpleFilterExprOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(18);
				opOne();
				setState(19);
				match(COMMA);
				setState(20);
				attrName();
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == SLASH) {
					{
						{
							setState(21);
							match(SLASH);
							setState(22);
							attrName();
						}
					}
					setState(27);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(28);
				match(COMMA);
				setState(29);
				value();
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleFilterExprMultiContext extends ParserRuleContext {
		public OpMultiContext opMulti() {
			return getRuleContext(OpMultiContext.class, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(EtsiFilterV25.COMMA);
		}

		public TerminalNode COMMA(final int i) {
			return getToken(EtsiFilterV25.COMMA, i);
		}

		public List<AttrNameContext> attrName() {
			return getRuleContexts(AttrNameContext.class);
		}

		public AttrNameContext attrName(final int i) {
			return getRuleContext(AttrNameContext.class, i);
		}

		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}

		public ValueContext value(final int i) {
			return getRuleContext(ValueContext.class, i);
		}

		public List<TerminalNode> SLASH() {
			return getTokens(EtsiFilterV25.SLASH);
		}

		public TerminalNode SLASH(final int i) {
			return getToken(EtsiFilterV25.SLASH, i);
		}

		public SimpleFilterExprMultiContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_simpleFilterExprMulti;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterSimpleFilterExprMulti(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitSimpleFilterExprMulti(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitSimpleFilterExprMulti(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final SimpleFilterExprMultiContext simpleFilterExprMulti() throws RecognitionException {
		final SimpleFilterExprMultiContext _localctx = new SimpleFilterExprMultiContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_simpleFilterExprMulti);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(31);
				opMulti();
				setState(32);
				match(COMMA);
				setState(33);
				attrName();
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == SLASH) {
					{
						{
							setState(34);
							match(SLASH);
							setState(35);
							attrName();
						}
					}
					setState(40);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(41);
				match(COMMA);
				setState(42);
				value();
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(43);
							match(COMMA);
							setState(44);
							value();
						}
					}
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleFilterExprContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() {
			return getToken(EtsiFilterV25.OPEN_BRACKET, 0);
		}

		public SimpleFilterExprOneContext simpleFilterExprOne() {
			return getRuleContext(SimpleFilterExprOneContext.class, 0);
		}

		public TerminalNode CLOSE_BRACKET() {
			return getToken(EtsiFilterV25.CLOSE_BRACKET, 0);
		}

		public SimpleFilterExprMultiContext simpleFilterExprMulti() {
			return getRuleContext(SimpleFilterExprMultiContext.class, 0);
		}

		public SimpleFilterExprContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_simpleFilterExpr;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterSimpleFilterExpr(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitSimpleFilterExpr(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitSimpleFilterExpr(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final SimpleFilterExprContext simpleFilterExpr() throws RecognitionException {
		final SimpleFilterExprContext _localctx = new SimpleFilterExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_simpleFilterExpr);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(50);
				match(OPEN_BRACKET);
				setState(51);
				simpleFilterExprOne();
				setState(52);
				match(CLOSE_BRACKET);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(54);
				match(OPEN_BRACKET);
				setState(55);
				simpleFilterExprMulti();
				setState(56);
				match(CLOSE_BRACKET);
			}
				break;
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterExprContext extends ParserRuleContext {
		public List<SimpleFilterExprContext> simpleFilterExpr() {
			return getRuleContexts(SimpleFilterExprContext.class);
		}

		public SimpleFilterExprContext simpleFilterExpr(final int i) {
			return getRuleContext(SimpleFilterExprContext.class, i);
		}

		public List<TerminalNode> SEMICOLON() {
			return getTokens(EtsiFilterV25.SEMICOLON);
		}

		public TerminalNode SEMICOLON(final int i) {
			return getToken(EtsiFilterV25.SEMICOLON, i);
		}

		public FilterExprContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_filterExpr;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterFilterExpr(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitFilterExpr(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitFilterExpr(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		final FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_filterExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(60);
				simpleFilterExpr();
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == SEMICOLON) {
					{
						{
							setState(61);
							match(SEMICOLON);
							setState(62);
							simpleFilterExpr();
						}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public TerminalNode FILTER() {
			return getToken(EtsiFilterV25.FILTER, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(EtsiFilterV25.EQUAL, 0);
		}

		public FilterExprContext filterExpr() {
			return getRuleContext(FilterExprContext.class, 0);
		}

		public FilterContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_filter;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterFilter(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitFilter(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitFilter(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final FilterContext filter() throws RecognitionException {
		final FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(68);
				match(FILTER);
				setState(69);
				match(EQUAL);
				setState(70);
				filterExpr();
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpOneContext extends ParserRuleContext {
		public TerminalNode EQ() {
			return getToken(EtsiFilterV25.EQ, 0);
		}

		public TerminalNode NEQ() {
			return getToken(EtsiFilterV25.NEQ, 0);
		}

		public TerminalNode GT() {
			return getToken(EtsiFilterV25.GT, 0);
		}

		public TerminalNode LT() {
			return getToken(EtsiFilterV25.LT, 0);
		}

		public TerminalNode GTE() {
			return getToken(EtsiFilterV25.GTE, 0);
		}

		public TerminalNode LTE() {
			return getToken(EtsiFilterV25.LTE, 0);
		}

		public OpOneContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_opOne;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterOpOne(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitOpOne(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitOpOne(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final OpOneContext opOne() throws RecognitionException {
		final OpOneContext _localctx = new OpOneContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_opOne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(72);
				_la = _input.LA(1);
				if (!(((((_la) & ~0x3f) == 0) && (((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GTE) | (1L << LTE))) != 0)))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpMultiContext extends ParserRuleContext {
		public TerminalNode IN() {
			return getToken(EtsiFilterV25.IN, 0);
		}

		public TerminalNode NIN() {
			return getToken(EtsiFilterV25.NIN, 0);
		}

		public TerminalNode CONT() {
			return getToken(EtsiFilterV25.CONT, 0);
		}

		public TerminalNode NCONT() {
			return getToken(EtsiFilterV25.NCONT, 0);
		}

		public OpMultiContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_opMulti;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterOpMulti(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitOpMulti(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitOpMulti(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final OpMultiContext opMulti() throws RecognitionException {
		final OpMultiContext _localctx = new OpMultiContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_opMulti);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(74);
				_la = _input.LA(1);
				if (!(((((_la) & ~0x3f) == 0) && (((1L << _la) & ((1L << IN) | (1L << NIN) | (1L << CONT) | (1L << NCONT))) != 0)))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrNameContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE() {
			return getToken(EtsiFilterV25.ATTRIBUTE, 0);
		}

		public AttrNameContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attrName;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterAttrName(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitAttrName(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitAttrName(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final AttrNameContext attrName() throws RecognitionException {
		final AttrNameContext _localctx = new AttrNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(76);
				match(ATTRIBUTE);
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() {
			return getToken(EtsiFilterV25.STRING, 0);
		}

		public ValueContext(final ParserRuleContext parent, final int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_value;
		}

		@Override
		public void enterRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).enterValue(this);
			}
		}

		@Override
		public void exitRule(final ParseTreeListener listener) {
			if (listener instanceof EtsiFilterV25Listener) {
				((EtsiFilterV25Listener) listener).exitValue(this);
			}
		}

		@Override
		public <T> T accept(final ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof EtsiFilterV25Visitor) {
				return ((EtsiFilterV25Visitor<? extends T>) visitor).visitValue(this);
			} else {
				return visitor.visitChildren(this);
			}
		}
	}

	public final ValueContext value() throws RecognitionException {
		final ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(78);
				match(STRING);
			}
		} catch (final RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25S\4\2\t\2\4\3\t" +
			"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2" +
			"\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7" +
			"\3\'\n\3\f\3\16\3*\13\3\3\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13\3\3" +
			"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4=\n\4\3\5\3\5\3\5\7\5B\n\5\f\5\16\5" +
			"E\13\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\2\2\13\2\4" +
			"\6\b\n\f\16\20\22\2\4\3\2\t\16\3\2\17\22\2N\2\24\3\2\2\2\4!\3\2\2\2\6" +
			"<\3\2\2\2\b>\3\2\2\2\nF\3\2\2\2\fJ\3\2\2\2\16L\3\2\2\2\20N\3\2\2\2\22" +
			"P\3\2\2\2\24\25\5\f\7\2\25\26\7\3\2\2\26\33\5\20\t\2\27\30\7\4\2\2\30" +
			"\32\5\20\t\2\31\27\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34" +
			"\36\3\2\2\2\35\33\3\2\2\2\36\37\7\3\2\2\37 \5\22\n\2 \3\3\2\2\2!\"\5\16" +
			"\b\2\"#\7\3\2\2#(\5\20\t\2$%\7\4\2\2%\'\5\20\t\2&$\3\2\2\2\'*\3\2\2\2" +
			"(&\3\2\2\2()\3\2\2\2)+\3\2\2\2*(\3\2\2\2+,\7\3\2\2,\61\5\22\n\2-.\7\3" +
			"\2\2.\60\5\22\n\2/-\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62" +
			"\5\3\2\2\2\63\61\3\2\2\2\64\65\7\5\2\2\65\66\5\2\2\2\66\67\7\6\2\2\67" +
			"=\3\2\2\289\7\5\2\29:\5\4\3\2:;\7\6\2\2;=\3\2\2\2<\64\3\2\2\2<8\3\2\2" +
			"\2=\7\3\2\2\2>C\5\6\4\2?@\7\7\2\2@B\5\6\4\2A?\3\2\2\2BE\3\2\2\2CA\3\2" +
			"\2\2CD\3\2\2\2D\t\3\2\2\2EC\3\2\2\2FG\7\23\2\2GH\7\b\2\2HI\5\b\5\2I\13" +
			"\3\2\2\2JK\t\2\2\2K\r\3\2\2\2LM\t\3\2\2M\17\3\2\2\2NO\7\24\2\2O\21\3\2" +
			"\2\2PQ\7\25\2\2Q\23\3\2\2\2\7\33(\61<C";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}