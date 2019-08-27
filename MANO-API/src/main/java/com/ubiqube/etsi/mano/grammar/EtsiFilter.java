// Generated from EtsiFilter.g4 by ANTLR 4.7.2

	package com.ubiqube.etsi.mano.grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EtsiFilter extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, SLASH=2, OPEN_BRACKET=3, CLOSE_BRACKET=4, SEMICOLON=5, EQUAL=6, 
		DOT=7, AMPERSAND=8, EQ=9, NEQ=10, GT=11, LT=12, GTE=13, LTE=14, IN=15, 
		NIN=16, CONT=17, NCONT=18, FILTER=19, ATTRIBUTE=20, STRING=21;
	public static final int
		RULE_simpleFilterExpr = 0, RULE_filterExpr = 1, RULE_op = 2, RULE_attrName = 3, 
		RULE_value = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"simpleFilterExpr", "filterExpr", "op", "attrName", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'/'", "'('", "')'", "';'", "'='", "'.'", "'&'", "'eq'", 
			"'neq'", "'gt'", "'lt'", "'gte'", "'lte'", "'in'", "'nin'", "'cont'", 
			"'ncont'", "'filter'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "SLASH", "OPEN_BRACKET", "CLOSE_BRACKET", "SEMICOLON", 
			"EQUAL", "DOT", "AMPERSAND", "EQ", "NEQ", "GT", "LT", "GTE", "LTE", "IN", 
			"NIN", "CONT", "NCONT", "FILTER", "ATTRIBUTE", "STRING"
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
	public String getGrammarFileName() { return "EtsiFilter.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EtsiFilter(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SimpleFilterExprContext extends ParserRuleContext {
		public List<AttrNameContext> attrName() {
			return getRuleContexts(AttrNameContext.class);
		}
		public AttrNameContext attrName(int i) {
			return getRuleContext(AttrNameContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(EtsiFilter.EQUAL, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(EtsiFilter.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(EtsiFilter.DOT, i);
		}
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(EtsiFilter.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(EtsiFilter.COMMA, i);
		}
		public SimpleFilterExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleFilterExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).enterSimpleFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).exitSimpleFilterExpr(this);
		}
	}

	public final SimpleFilterExprContext simpleFilterExpr() throws RecognitionException {
		SimpleFilterExprContext _localctx = new SimpleFilterExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_simpleFilterExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			attrName();
			setState(15);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(11);
					match(DOT);
					setState(12);
					attrName();
					}
					} 
				}
				setState(17);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(18);
				match(DOT);
				setState(19);
				op();
				}
			}

			setState(22);
			match(EQUAL);
			setState(23);
			value();
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(24);
				match(COMMA);
				setState(25);
				value();
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterExprContext extends ParserRuleContext {
		public List<SimpleFilterExprContext> simpleFilterExpr() {
			return getRuleContexts(SimpleFilterExprContext.class);
		}
		public SimpleFilterExprContext simpleFilterExpr(int i) {
			return getRuleContext(SimpleFilterExprContext.class,i);
		}
		public List<TerminalNode> AMPERSAND() { return getTokens(EtsiFilter.AMPERSAND); }
		public TerminalNode AMPERSAND(int i) {
			return getToken(EtsiFilter.AMPERSAND, i);
		}
		public FilterExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).enterFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).exitFilterExpr(this);
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_filterExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			simpleFilterExpr();
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AMPERSAND) {
				{
				{
				setState(32);
				match(AMPERSAND);
				setState(33);
				simpleFilterExpr();
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(EtsiFilter.EQ, 0); }
		public TerminalNode NEQ() { return getToken(EtsiFilter.NEQ, 0); }
		public TerminalNode GT() { return getToken(EtsiFilter.GT, 0); }
		public TerminalNode LT() { return getToken(EtsiFilter.LT, 0); }
		public TerminalNode GTE() { return getToken(EtsiFilter.GTE, 0); }
		public TerminalNode LTE() { return getToken(EtsiFilter.LTE, 0); }
		public TerminalNode CONT() { return getToken(EtsiFilter.CONT, 0); }
		public TerminalNode NCONT() { return getToken(EtsiFilter.NCONT, 0); }
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).exitOp(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GTE) | (1L << LTE) | (1L << CONT) | (1L << NCONT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrNameContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE() { return getToken(EtsiFilter.ATTRIBUTE, 0); }
		public AttrNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).enterAttrName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).exitAttrName(this);
		}
	}

	public final AttrNameContext attrName() throws RecognitionException {
		AttrNameContext _localctx = new AttrNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(ATTRIBUTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(EtsiFilter.STRING, 0); }
		public TerminalNode ATTRIBUTE() { return getToken(EtsiFilter.ATTRIBUTE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsiFilterListener ) ((EtsiFilterListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_la = _input.LA(1);
			if ( !(_la==ATTRIBUTE || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\60\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3"+
		"\2\3\2\5\2\27\n\2\3\2\3\2\3\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\3\3\3\3"+
		"\3\7\3%\n\3\f\3\16\3(\13\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\2\2\7\2\4\6\b\n"+
		"\2\4\4\2\13\20\23\24\3\2\26\27\2.\2\f\3\2\2\2\4!\3\2\2\2\6)\3\2\2\2\b"+
		"+\3\2\2\2\n-\3\2\2\2\f\21\5\b\5\2\r\16\7\t\2\2\16\20\5\b\5\2\17\r\3\2"+
		"\2\2\20\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\26\3\2\2\2\23\21\3\2"+
		"\2\2\24\25\7\t\2\2\25\27\5\6\4\2\26\24\3\2\2\2\26\27\3\2\2\2\27\30\3\2"+
		"\2\2\30\31\7\b\2\2\31\36\5\n\6\2\32\33\7\3\2\2\33\35\5\n\6\2\34\32\3\2"+
		"\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\3\3\2\2\2 \36\3\2\2\2"+
		"!&\5\2\2\2\"#\7\n\2\2#%\5\2\2\2$\"\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2"+
		"\2\2\'\5\3\2\2\2(&\3\2\2\2)*\t\2\2\2*\7\3\2\2\2+,\7\26\2\2,\t\3\2\2\2"+
		"-.\t\3\2\2.\13\3\2\2\2\6\21\26\36&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}