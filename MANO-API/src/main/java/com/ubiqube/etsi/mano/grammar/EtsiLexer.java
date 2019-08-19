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
	public static final int
		VALUES=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "VALUES"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27z\b\1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\6\25p\n\25"+
		"\r\25\16\25q\3\26\6\26u\n\26\r\26\16\26v\3\26\3\26\2\2\27\4\3\6\4\b\5"+
		"\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20 \21\"\22$\23"+
		"&\24(\25*\26,\27\4\2\3\4\5\2\62;C\\c|\6\2((..\60\60??\2z\2\4\3\2\2\2\2"+
		"\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2"+
		"\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2"+
		"\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2"+
		"\2(\3\2\2\2\2*\3\2\2\2\3,\3\2\2\2\4.\3\2\2\2\6\60\3\2\2\2\b\62\3\2\2\2"+
		"\n\64\3\2\2\2\f\66\3\2\2\2\168\3\2\2\2\20<\3\2\2\2\22>\3\2\2\2\24@\3\2"+
		"\2\2\26C\3\2\2\2\30G\3\2\2\2\32J\3\2\2\2\34M\3\2\2\2\36Q\3\2\2\2 U\3\2"+
		"\2\2\"X\3\2\2\2$\\\3\2\2\2&a\3\2\2\2(g\3\2\2\2*o\3\2\2\2,t\3\2\2\2./\7"+
		".\2\2/\5\3\2\2\2\60\61\7\61\2\2\61\7\3\2\2\2\62\63\7*\2\2\63\t\3\2\2\2"+
		"\64\65\7+\2\2\65\13\3\2\2\2\66\67\7=\2\2\67\r\3\2\2\289\7?\2\29:\3\2\2"+
		"\2:;\b\7\2\2;\17\3\2\2\2<=\7\60\2\2=\21\3\2\2\2>?\7(\2\2?\23\3\2\2\2@"+
		"A\7g\2\2AB\7s\2\2B\25\3\2\2\2CD\7p\2\2DE\7g\2\2EF\7s\2\2F\27\3\2\2\2G"+
		"H\7i\2\2HI\7v\2\2I\31\3\2\2\2JK\7n\2\2KL\7v\2\2L\33\3\2\2\2MN\7i\2\2N"+
		"O\7v\2\2OP\7g\2\2P\35\3\2\2\2QR\7n\2\2RS\7v\2\2ST\7g\2\2T\37\3\2\2\2U"+
		"V\7k\2\2VW\7p\2\2W!\3\2\2\2XY\7p\2\2YZ\7k\2\2Z[\7p\2\2[#\3\2\2\2\\]\7"+
		"e\2\2]^\7q\2\2^_\7p\2\2_`\7v\2\2`%\3\2\2\2ab\7p\2\2bc\7e\2\2cd\7q\2\2"+
		"de\7p\2\2ef\7v\2\2f\'\3\2\2\2gh\7h\2\2hi\7k\2\2ij\7n\2\2jk\7v\2\2kl\7"+
		"g\2\2lm\7t\2\2m)\3\2\2\2np\t\2\2\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2"+
		"\2\2r+\3\2\2\2su\n\3\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2"+
		"\2\2xy\b\26\3\2y-\3\2\2\2\6\2\3qv\4\4\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}