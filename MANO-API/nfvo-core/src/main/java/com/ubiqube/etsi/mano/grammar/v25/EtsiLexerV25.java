// Generated from EtsiLexerV25.g4 by ANTLR 4.7.2

package com.ubiqube.etsi.mano.grammar.v25;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class EtsiLexerV25 extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int COMMA = 1, SLASH = 2, OPEN_BRACKET = 3, CLOSE_BRACKET = 4, SEMICOLON = 5, EQUAL = 6,
			EQ = 7, NEQ = 8, GT = 9, LT = 10, GTE = 11, LTE = 12, IN = 13, NIN = 14, CONT = 15, NCONT = 16,
			FILTER = 17, ATTRIBUTE = 18, STRING = 19;
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
			"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
				"COMMA", "SLASH", "OPEN_BRACKET", "CLOSE_BRACKET", "SEMICOLON", "EQUAL",
				"EQ", "NEQ", "GT", "LT", "GTE", "LTE", "IN", "NIN", "CONT", "NCONT",
				"FILTER", "ATTRIBUTE", "STRING"
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

	public EtsiLexerV25(final CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "EtsiLexerV25.g4";
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
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25m\b\1\4\2\t\2\4" +
			"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
			"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
			"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3" +
			"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f" +
			"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20" +
			"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22" +
			"\3\23\6\23e\n\23\r\23\16\23f\3\24\6\24j\n\24\r\24\16\24k\2\2\25\3\3\5" +
			"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21" +
			"!\22#\23%\24\'\25\3\2\4\4\2C\\c|\6\2*+..\60\61??\2n\2\3\3\2\2\2\2\5\3" +
			"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2" +
			"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3" +
			"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'" +
			"\3\2\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r\63" +
			"\3\2\2\2\17\65\3\2\2\2\218\3\2\2\2\23<\3\2\2\2\25?\3\2\2\2\27B\3\2\2\2" +
			"\31F\3\2\2\2\33J\3\2\2\2\35M\3\2\2\2\37Q\3\2\2\2!V\3\2\2\2#\\\3\2\2\2" +
			"%d\3\2\2\2\'i\3\2\2\2)*\7.\2\2*\4\3\2\2\2+,\7\61\2\2,\6\3\2\2\2-.\7*\2" +
			"\2.\b\3\2\2\2/\60\7+\2\2\60\n\3\2\2\2\61\62\7=\2\2\62\f\3\2\2\2\63\64" +
			"\7?\2\2\64\16\3\2\2\2\65\66\7g\2\2\66\67\7s\2\2\67\20\3\2\2\289\7p\2\2" +
			"9:\7g\2\2:;\7s\2\2;\22\3\2\2\2<=\7i\2\2=>\7v\2\2>\24\3\2\2\2?@\7n\2\2" +
			"@A\7v\2\2A\26\3\2\2\2BC\7i\2\2CD\7v\2\2DE\7g\2\2E\30\3\2\2\2FG\7n\2\2" +
			"GH\7v\2\2HI\7g\2\2I\32\3\2\2\2JK\7k\2\2KL\7p\2\2L\34\3\2\2\2MN\7p\2\2" +
			"NO\7k\2\2OP\7p\2\2P\36\3\2\2\2QR\7e\2\2RS\7q\2\2ST\7p\2\2TU\7v\2\2U \3" +
			"\2\2\2VW\7p\2\2WX\7e\2\2XY\7q\2\2YZ\7p\2\2Z[\7v\2\2[\"\3\2\2\2\\]\7h\2" +
			"\2]^\7k\2\2^_\7n\2\2_`\7v\2\2`a\7g\2\2ab\7t\2\2b$\3\2\2\2ce\t\2\2\2dc" +
			"\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2g&\3\2\2\2hj\n\3\2\2ih\3\2\2\2j" +
			"k\3\2\2\2ki\3\2\2\2kl\3\2\2\2l(\3\2\2\2\5\2fk\2";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}