// Generated from EtsiLexer.g4 by ANTLR 4.7.2

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
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, SLASH=2, OPEN_BRACKET=3, CLOSE_BRACKET=4, SEMICOLON=5, EQUAL=6, 
		DOT=7, AMPERSAND=8, EQ=9, NEQ=10, GT=11, LT=12, GTE=13, LTE=14, IN=15, 
		NIN=16, CONT=17, NCONT=18, FILTER=19, ATTRIBUTE=20, STRING=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMA", "SLASH", "OPEN_BRACKET", "CLOSE_BRACKET", "SEMICOLON", "EQUAL", 
			"DOT", "AMPERSAND", "EQ", "NEQ", "GT", "LT", "GTE", "LTE", "IN", "NIN", 
			"CONT", "NCONT", "FILTER", "ATTRIBUTE", "STRING"
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


	public EtsiLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EtsiLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27u\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\6\25m\n\25\r\25\16"+
		"\25n\3\26\6\26r\n\26\r\26\16\26s\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"\3\2\4\4\2C\\c|\5\2..\60\60??\2v\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3\2\2\2\t\63\3\2\2\2\13\65\3\2"+
		"\2\2\r\67\3\2\2\2\179\3\2\2\2\21;\3\2\2\2\23=\3\2\2\2\25@\3\2\2\2\27D"+
		"\3\2\2\2\31G\3\2\2\2\33J\3\2\2\2\35N\3\2\2\2\37R\3\2\2\2!U\3\2\2\2#Y\3"+
		"\2\2\2%^\3\2\2\2\'d\3\2\2\2)l\3\2\2\2+q\3\2\2\2-.\7.\2\2.\4\3\2\2\2/\60"+
		"\7\61\2\2\60\6\3\2\2\2\61\62\7*\2\2\62\b\3\2\2\2\63\64\7+\2\2\64\n\3\2"+
		"\2\2\65\66\7=\2\2\66\f\3\2\2\2\678\7?\2\28\16\3\2\2\29:\7\60\2\2:\20\3"+
		"\2\2\2;<\7(\2\2<\22\3\2\2\2=>\7g\2\2>?\7s\2\2?\24\3\2\2\2@A\7p\2\2AB\7"+
		"g\2\2BC\7s\2\2C\26\3\2\2\2DE\7i\2\2EF\7v\2\2F\30\3\2\2\2GH\7n\2\2HI\7"+
		"v\2\2I\32\3\2\2\2JK\7i\2\2KL\7v\2\2LM\7g\2\2M\34\3\2\2\2NO\7n\2\2OP\7"+
		"v\2\2PQ\7g\2\2Q\36\3\2\2\2RS\7k\2\2ST\7p\2\2T \3\2\2\2UV\7p\2\2VW\7k\2"+
		"\2WX\7p\2\2X\"\3\2\2\2YZ\7e\2\2Z[\7q\2\2[\\\7p\2\2\\]\7v\2\2]$\3\2\2\2"+
		"^_\7p\2\2_`\7e\2\2`a\7q\2\2ab\7p\2\2bc\7v\2\2c&\3\2\2\2de\7h\2\2ef\7k"+
		"\2\2fg\7n\2\2gh\7v\2\2hi\7g\2\2ij\7t\2\2j(\3\2\2\2km\t\2\2\2lk\3\2\2\2"+
		"mn\3\2\2\2nl\3\2\2\2no\3\2\2\2o*\3\2\2\2pr\n\3\2\2qp\3\2\2\2rs\3\2\2\2"+
		"sq\3\2\2\2st\3\2\2\2t,\3\2\2\2\5\2ns\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}