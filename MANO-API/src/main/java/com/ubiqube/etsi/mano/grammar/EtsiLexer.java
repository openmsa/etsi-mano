// Generated from EtsiLexer.g4 by ANTLR 4.4

	package com.ubiqube.etsi.mano.grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EtsiLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EQ=1, NEQ=2, GT=3, LT=4, GTE=5, LTE=6, CONT=7, NCONT=8, DOT=9, AMP=10, 
		EQUAL=11, COMA=12, STRING=13, VALUE=14;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'"
	};
	public static final String[] ruleNames = {
		"EQ", "NEQ", "GT", "LT", "GTE", "LTE", "CONT", "NCONT", "DOT", "AMP", 
		"EQUAL", "COMA", "STRING", "VALUE"
	};


	public EtsiLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EtsiLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20Q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\6"+
		"\16I\n\16\r\16\16\16J\3\17\6\17N\n\17\r\17\16\17O\2\2\20\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\3\2\4\4\2C\\c"+
		"|\5\2\62;C\\c|R\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5\""+
		"\3\2\2\2\7&\3\2\2\2\t)\3\2\2\2\13,\3\2\2\2\r\60\3\2\2\2\17\64\3\2\2\2"+
		"\219\3\2\2\2\23?\3\2\2\2\25A\3\2\2\2\27C\3\2\2\2\31E\3\2\2\2\33H\3\2\2"+
		"\2\35M\3\2\2\2\37 \7g\2\2 !\7s\2\2!\4\3\2\2\2\"#\7p\2\2#$\7g\2\2$%\7s"+
		"\2\2%\6\3\2\2\2&\'\7i\2\2\'(\7v\2\2(\b\3\2\2\2)*\7n\2\2*+\7v\2\2+\n\3"+
		"\2\2\2,-\7i\2\2-.\7v\2\2./\7g\2\2/\f\3\2\2\2\60\61\7n\2\2\61\62\7v\2\2"+
		"\62\63\7g\2\2\63\16\3\2\2\2\64\65\7e\2\2\65\66\7q\2\2\66\67\7p\2\2\67"+
		"8\7v\2\28\20\3\2\2\29:\7p\2\2:;\7e\2\2;<\7q\2\2<=\7p\2\2=>\7v\2\2>\22"+
		"\3\2\2\2?@\7\60\2\2@\24\3\2\2\2AB\7(\2\2B\26\3\2\2\2CD\7?\2\2D\30\3\2"+
		"\2\2EF\7.\2\2F\32\3\2\2\2GI\t\2\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3"+
		"\2\2\2K\34\3\2\2\2LN\t\3\2\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2P"+
		"\36\3\2\2\2\5\2JO\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
