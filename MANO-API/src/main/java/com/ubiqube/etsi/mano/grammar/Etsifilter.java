// Generated from Etsifilter.g4 by ANTLR 4.4

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
public class Etsifilter extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AMP=10, EQUAL=11, LT=4, DOT=9, NCONT=8, VALUE=14, EQ=1, GT=3, CONT=7, 
		GTE=5, STRING=13, NEQ=2, LTE=6, COMA=12;
	public static final String[] tokenNames = {
		"<INVALID>", "'eq'", "'neq'", "'gt'", "'lt'", "'gte'", "'lte'", "'cont'", 
		"'ncont'", "'.'", "'&'", "'='", "','", "STRING", "VALUE"
	};
	public static final int
		RULE_filterExpr = 0, RULE_simpleFilterExpr = 1, RULE_op = 2, RULE_attrName = 3, 
		RULE_value = 4;
	public static final String[] ruleNames = {
		"filterExpr", "simpleFilterExpr", "op", "attrName", "value"
	};

	@Override
	public String getGrammarFileName() { return "Etsifilter.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Etsifilter(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FilterExprContext extends ParserRuleContext {
		public List<TerminalNode> AMP() { return getTokens(Etsifilter.AMP); }
		public List<SimpleFilterExprContext> simpleFilterExpr() {
			return getRuleContexts(SimpleFilterExprContext.class);
		}
		public TerminalNode AMP(int i) {
			return getToken(Etsifilter.AMP, i);
		}
		public SimpleFilterExprContext simpleFilterExpr(int i) {
			return getRuleContext(SimpleFilterExprContext.class,i);
		}
		public FilterExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).enterFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).exitFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EtsifilterVisitor ) return ((EtsifilterVisitor<? extends T>)visitor).visitFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_filterExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10); simpleFilterExpr();
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AMP) {
				{
				{
				setState(11); match(AMP);
				setState(12); simpleFilterExpr();
				}
				}
				setState(17);
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

	public static class SimpleFilterExprContext extends ParserRuleContext {
		public AttrNameContext attrName(int i) {
			return getRuleContext(AttrNameContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(Etsifilter.EQUAL, 0); }
		public List<TerminalNode> DOT() { return getTokens(Etsifilter.DOT); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<AttrNameContext> attrName() {
			return getRuleContexts(AttrNameContext.class);
		}
		public TerminalNode COMA(int i) {
			return getToken(Etsifilter.COMA, i);
		}
		public TerminalNode DOT(int i) {
			return getToken(Etsifilter.DOT, i);
		}
		public List<TerminalNode> COMA() { return getTokens(Etsifilter.COMA); }
		public SimpleFilterExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleFilterExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).enterSimpleFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).exitSimpleFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EtsifilterVisitor ) return ((EtsifilterVisitor<? extends T>)visitor).visitSimpleFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleFilterExprContext simpleFilterExpr() throws RecognitionException {
		SimpleFilterExprContext _localctx = new SimpleFilterExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_simpleFilterExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(18); attrName();
			setState(23);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(19); match(DOT);
					setState(20); attrName();
					}
					} 
				}
				setState(25);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			{
			setState(26); match(DOT);
			setState(27); op();
			}
			setState(29); match(EQUAL);
			setState(30); value();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(31); match(COMA);
				setState(32); value();
				}
				}
				setState(37);
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
		public TerminalNode NEQ() { return getToken(Etsifilter.NEQ, 0); }
		public TerminalNode NCONT() { return getToken(Etsifilter.NCONT, 0); }
		public TerminalNode LT() { return getToken(Etsifilter.LT, 0); }
		public TerminalNode GTE() { return getToken(Etsifilter.GTE, 0); }
		public TerminalNode GT() { return getToken(Etsifilter.GT, 0); }
		public TerminalNode CONT() { return getToken(Etsifilter.CONT, 0); }
		public TerminalNode EQ() { return getToken(Etsifilter.EQ, 0); }
		public TerminalNode LTE() { return getToken(Etsifilter.LTE, 0); }
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).exitOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EtsifilterVisitor ) return ((EtsifilterVisitor<? extends T>)visitor).visitOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GTE) | (1L << LTE) | (1L << CONT) | (1L << NCONT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		public TerminalNode STRING() { return getToken(Etsifilter.STRING, 0); }
		public AttrNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).enterAttrName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).exitAttrName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EtsifilterVisitor ) return ((EtsifilterVisitor<? extends T>)visitor).visitAttrName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttrNameContext attrName() throws RecognitionException {
		AttrNameContext _localctx = new AttrNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); match(STRING);
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
		public TerminalNode VALUE() { return getToken(Etsifilter.VALUE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EtsifilterListener ) ((EtsifilterListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EtsifilterVisitor ) return ((EtsifilterVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); match(VALUE);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\20/\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3\3"+
		"\3\3\3\3\7\3\30\n\3\f\3\16\3\33\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3$"+
		"\n\3\f\3\16\3\'\13\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\3\3"+
		"\2\3\n,\2\f\3\2\2\2\4\24\3\2\2\2\6(\3\2\2\2\b*\3\2\2\2\n,\3\2\2\2\f\21"+
		"\5\4\3\2\r\16\7\f\2\2\16\20\5\4\3\2\17\r\3\2\2\2\20\23\3\2\2\2\21\17\3"+
		"\2\2\2\21\22\3\2\2\2\22\3\3\2\2\2\23\21\3\2\2\2\24\31\5\b\5\2\25\26\7"+
		"\13\2\2\26\30\5\b\5\2\27\25\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32"+
		"\3\2\2\2\32\34\3\2\2\2\33\31\3\2\2\2\34\35\7\13\2\2\35\36\5\6\4\2\36\37"+
		"\3\2\2\2\37 \7\r\2\2 %\5\n\6\2!\"\7\16\2\2\"$\5\n\6\2#!\3\2\2\2$\'\3\2"+
		"\2\2%#\3\2\2\2%&\3\2\2\2&\5\3\2\2\2\'%\3\2\2\2()\t\2\2\2)\7\3\2\2\2*+"+
		"\7\17\2\2+\t\3\2\2\2,-\7\20\2\2-\13\3\2\2\2\5\21\31%";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}