// Generated from Expr.g4 by ANTLR 4.7.1

    package antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, INT_TYPE=2, Variable=3, Constant=4, Return=5, Integer=6, Boolean=7, 
		Void=8, Main=9, If=10, Else=11, True=12, False=13, While=14, Skip=15, 
		COMMA=16, SEMI=17, COLON=18, BEGIN=19, END=20, LBR=21, RBR=22, ASSIGN=23, 
		PLUS=24, MINUS=25, NEGATION=26, OR=27, AND=28, EQUAL=29, NOTEQUAL=30, 
		LT=31, LTE=32, GT=33, GTE=34, NUMBER=35, ID=36, WS=37, COMMENT=38, SINGLE_COMMENT=39;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "INT_TYPE", "Variable", "Constant", "Return", "Integer", "Boolean", 
		"Void", "Main", "If", "Else", "True", "False", "While", "Skip", "COMMA", 
		"SEMI", "COLON", "BEGIN", "END", "LBR", "RBR", "ASSIGN", "PLUS", "MINUS", 
		"NEGATION", "OR", "AND", "EQUAL", "NOTEQUAL", "LT", "LTE", "GT", "GTE", 
		"NUMBER", "ID", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
		"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", 
		"Z", "Letter", "Digit", "Underscore", "WS", "COMMENT", "SINGLE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'*'", "'INT'", null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "','", "';'", "':'", "'{'", "'}'", "'('", 
		"')'", "'='", "'+'", "'-'", "'~'", "'||'", "'&&'", "'=='", "'!='", "'<'", 
		"'<='", "'>'", "'>='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "INT_TYPE", "Variable", "Constant", "Return", "Integer", "Boolean", 
		"Void", "Main", "If", "Else", "True", "False", "While", "Skip", "COMMA", 
		"SEMI", "COLON", "BEGIN", "END", "LBR", "RBR", "ASSIGN", "PLUS", "MINUS", 
		"NEGATION", "OR", "AND", "EQUAL", "NOTEQUAL", "LT", "LTE", "GT", "GTE", 
		"NUMBER", "ID", "WS", "COMMENT", "SINGLE_COMMENT"
	};
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


	public ExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u0174\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3"+
		" \3!\3!\3!\3\"\3\"\3#\3#\3#\3$\3$\7$\u0109\n$\f$\16$\u010c\13$\3$\5$\u010f"+
		"\n$\3%\3%\3%\3%\7%\u0115\n%\f%\16%\u0118\13%\3&\3&\3\'\3\'\3(\3(\3)\3"+
		")\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3"+
		"\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;"+
		"\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\6C\u0155\nC\rC\16C\u0156"+
		"\3C\3C\3D\3D\3D\3D\3D\7D\u0160\nD\fD\16D\u0163\13D\3D\3D\3D\3D\3D\3E\3"+
		"E\3E\3E\7E\u016e\nE\fE\16E\u0171\13E\3E\3E\3\u0161\2F\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y"+
		"\2{\2}\2\177\2\u0081\2\u0083\2\u0085\'\u0087(\u0089)\3\2!\3\2\63;\3\2"+
		"\62;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4"+
		"\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSs"+
		"s\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2"+
		"\\\\||\4\2C\\c|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u015f\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\3\u008b\3\2\2\2\5\u008d"+
		"\3\2\2\2\7\u0091\3\2\2\2\t\u0095\3\2\2\2\13\u009b\3\2\2\2\r\u00a2\3\2"+
		"\2\2\17\u00aa\3\2\2\2\21\u00b2\3\2\2\2\23\u00b7\3\2\2\2\25\u00bc\3\2\2"+
		"\2\27\u00bf\3\2\2\2\31\u00c4\3\2\2\2\33\u00c9\3\2\2\2\35\u00cf\3\2\2\2"+
		"\37\u00d5\3\2\2\2!\u00da\3\2\2\2#\u00dc\3\2\2\2%\u00de\3\2\2\2\'\u00e0"+
		"\3\2\2\2)\u00e2\3\2\2\2+\u00e4\3\2\2\2-\u00e6\3\2\2\2/\u00e8\3\2\2\2\61"+
		"\u00ea\3\2\2\2\63\u00ec\3\2\2\2\65\u00ee\3\2\2\2\67\u00f0\3\2\2\29\u00f3"+
		"\3\2\2\2;\u00f6\3\2\2\2=\u00f9\3\2\2\2?\u00fc\3\2\2\2A\u00fe\3\2\2\2C"+
		"\u0101\3\2\2\2E\u0103\3\2\2\2G\u010e\3\2\2\2I\u0110\3\2\2\2K\u0119\3\2"+
		"\2\2M\u011b\3\2\2\2O\u011d\3\2\2\2Q\u011f\3\2\2\2S\u0121\3\2\2\2U\u0123"+
		"\3\2\2\2W\u0125\3\2\2\2Y\u0127\3\2\2\2[\u0129\3\2\2\2]\u012b\3\2\2\2_"+
		"\u012d\3\2\2\2a\u012f\3\2\2\2c\u0131\3\2\2\2e\u0133\3\2\2\2g\u0135\3\2"+
		"\2\2i\u0137\3\2\2\2k\u0139\3\2\2\2m\u013b\3\2\2\2o\u013d\3\2\2\2q\u013f"+
		"\3\2\2\2s\u0141\3\2\2\2u\u0143\3\2\2\2w\u0145\3\2\2\2y\u0147\3\2\2\2{"+
		"\u0149\3\2\2\2}\u014b\3\2\2\2\177\u014d\3\2\2\2\u0081\u014f\3\2\2\2\u0083"+
		"\u0151\3\2\2\2\u0085\u0154\3\2\2\2\u0087\u015a\3\2\2\2\u0089\u0169\3\2"+
		"\2\2\u008b\u008c\7,\2\2\u008c\4\3\2\2\2\u008d\u008e\7K\2\2\u008e\u008f"+
		"\7P\2\2\u008f\u0090\7V\2\2\u0090\6\3\2\2\2\u0091\u0092\5u;\2\u0092\u0093"+
		"\5K&\2\u0093\u0094\5m\67\2\u0094\b\3\2\2\2\u0095\u0096\5O(\2\u0096\u0097"+
		"\5g\64\2\u0097\u0098\5e\63\2\u0098\u0099\5o8\2\u0099\u009a\5q9\2\u009a"+
		"\n\3\2\2\2\u009b\u009c\5m\67\2\u009c\u009d\5S*\2\u009d\u009e\5q9\2\u009e"+
		"\u009f\5s:\2\u009f\u00a0\5m\67\2\u00a0\u00a1\5e\63\2\u00a1\f\3\2\2\2\u00a2"+
		"\u00a3\5[.\2\u00a3\u00a4\5e\63\2\u00a4\u00a5\5q9\2\u00a5\u00a6\5S*\2\u00a6"+
		"\u00a7\5W,\2\u00a7\u00a8\5S*\2\u00a8\u00a9\5m\67\2\u00a9\16\3\2\2\2\u00aa"+
		"\u00ab\5M\'\2\u00ab\u00ac\5g\64\2\u00ac\u00ad\5g\64\2\u00ad\u00ae\5a\61"+
		"\2\u00ae\u00af\5S*\2\u00af\u00b0\5K&\2\u00b0\u00b1\5e\63\2\u00b1\20\3"+
		"\2\2\2\u00b2\u00b3\5u;\2\u00b3\u00b4\5g\64\2\u00b4\u00b5\5[.\2\u00b5\u00b6"+
		"\5Q)\2\u00b6\22\3\2\2\2\u00b7\u00b8\5c\62\2\u00b8\u00b9\5K&\2\u00b9\u00ba"+
		"\5[.\2\u00ba\u00bb\5e\63\2\u00bb\24\3\2\2\2\u00bc\u00bd\5[.\2\u00bd\u00be"+
		"\5U+\2\u00be\26\3\2\2\2\u00bf\u00c0\5S*\2\u00c0\u00c1\5a\61\2\u00c1\u00c2"+
		"\5o8\2\u00c2\u00c3\5S*\2\u00c3\30\3\2\2\2\u00c4\u00c5\5q9\2\u00c5\u00c6"+
		"\5m\67\2\u00c6\u00c7\5s:\2\u00c7\u00c8\5S*\2\u00c8\32\3\2\2\2\u00c9\u00ca"+
		"\5U+\2\u00ca\u00cb\5K&\2\u00cb\u00cc\5a\61\2\u00cc\u00cd\5o8\2\u00cd\u00ce"+
		"\5S*\2\u00ce\34\3\2\2\2\u00cf\u00d0\5w<\2\u00d0\u00d1\5Y-\2\u00d1\u00d2"+
		"\5[.\2\u00d2\u00d3\5a\61\2\u00d3\u00d4\5S*\2\u00d4\36\3\2\2\2\u00d5\u00d6"+
		"\5o8\2\u00d6\u00d7\5_\60\2\u00d7\u00d8\5[.\2\u00d8\u00d9\5i\65\2\u00d9"+
		" \3\2\2\2\u00da\u00db\7.\2\2\u00db\"\3\2\2\2\u00dc\u00dd\7=\2\2\u00dd"+
		"$\3\2\2\2\u00de\u00df\7<\2\2\u00df&\3\2\2\2\u00e0\u00e1\7}\2\2\u00e1("+
		"\3\2\2\2\u00e2\u00e3\7\177\2\2\u00e3*\3\2\2\2\u00e4\u00e5\7*\2\2\u00e5"+
		",\3\2\2\2\u00e6\u00e7\7+\2\2\u00e7.\3\2\2\2\u00e8\u00e9\7?\2\2\u00e9\60"+
		"\3\2\2\2\u00ea\u00eb\7-\2\2\u00eb\62\3\2\2\2\u00ec\u00ed\7/\2\2\u00ed"+
		"\64\3\2\2\2\u00ee\u00ef\7\u0080\2\2\u00ef\66\3\2\2\2\u00f0\u00f1\7~\2"+
		"\2\u00f1\u00f2\7~\2\2\u00f28\3\2\2\2\u00f3\u00f4\7(\2\2\u00f4\u00f5\7"+
		"(\2\2\u00f5:\3\2\2\2\u00f6\u00f7\7?\2\2\u00f7\u00f8\7?\2\2\u00f8<\3\2"+
		"\2\2\u00f9\u00fa\7#\2\2\u00fa\u00fb\7?\2\2\u00fb>\3\2\2\2\u00fc\u00fd"+
		"\7>\2\2\u00fd@\3\2\2\2\u00fe\u00ff\7>\2\2\u00ff\u0100\7?\2\2\u0100B\3"+
		"\2\2\2\u0101\u0102\7@\2\2\u0102D\3\2\2\2\u0103\u0104\7@\2\2\u0104\u0105"+
		"\7?\2\2\u0105F\3\2\2\2\u0106\u010a\t\2\2\2\u0107\u0109\t\3\2\2\u0108\u0107"+
		"\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010f\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010f\7\62\2\2\u010e\u0106\3"+
		"\2\2\2\u010e\u010d\3\2\2\2\u010fH\3\2\2\2\u0110\u0116\5\177@\2\u0111\u0115"+
		"\5\177@\2\u0112\u0115\5\u0083B\2\u0113\u0115\5G$\2\u0114\u0111\3\2\2\2"+
		"\u0114\u0112\3\2\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114"+
		"\3\2\2\2\u0116\u0117\3\2\2\2\u0117J\3\2\2\2\u0118\u0116\3\2\2\2\u0119"+
		"\u011a\t\4\2\2\u011aL\3\2\2\2\u011b\u011c\t\5\2\2\u011cN\3\2\2\2\u011d"+
		"\u011e\t\6\2\2\u011eP\3\2\2\2\u011f\u0120\t\7\2\2\u0120R\3\2\2\2\u0121"+
		"\u0122\t\b\2\2\u0122T\3\2\2\2\u0123\u0124\t\t\2\2\u0124V\3\2\2\2\u0125"+
		"\u0126\t\n\2\2\u0126X\3\2\2\2\u0127\u0128\t\13\2\2\u0128Z\3\2\2\2\u0129"+
		"\u012a\t\f\2\2\u012a\\\3\2\2\2\u012b\u012c\t\r\2\2\u012c^\3\2\2\2\u012d"+
		"\u012e\t\16\2\2\u012e`\3\2\2\2\u012f\u0130\t\17\2\2\u0130b\3\2\2\2\u0131"+
		"\u0132\t\20\2\2\u0132d\3\2\2\2\u0133\u0134\t\21\2\2\u0134f\3\2\2\2\u0135"+
		"\u0136\t\22\2\2\u0136h\3\2\2\2\u0137\u0138\t\23\2\2\u0138j\3\2\2\2\u0139"+
		"\u013a\t\24\2\2\u013al\3\2\2\2\u013b\u013c\t\25\2\2\u013cn\3\2\2\2\u013d"+
		"\u013e\t\26\2\2\u013ep\3\2\2\2\u013f\u0140\t\27\2\2\u0140r\3\2\2\2\u0141"+
		"\u0142\t\30\2\2\u0142t\3\2\2\2\u0143\u0144\t\31\2\2\u0144v\3\2\2\2\u0145"+
		"\u0146\t\32\2\2\u0146x\3\2\2\2\u0147\u0148\t\33\2\2\u0148z\3\2\2\2\u0149"+
		"\u014a\t\34\2\2\u014a|\3\2\2\2\u014b\u014c\t\35\2\2\u014c~\3\2\2\2\u014d"+
		"\u014e\t\36\2\2\u014e\u0080\3\2\2\2\u014f\u0150\t\3\2\2\u0150\u0082\3"+
		"\2\2\2\u0151\u0152\7a\2\2\u0152\u0084\3\2\2\2\u0153\u0155\t\37\2\2\u0154"+
		"\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2"+
		"\2\2\u0157\u0158\3\2\2\2\u0158\u0159\bC\2\2\u0159\u0086\3\2\2\2\u015a"+
		"\u015b\7\61\2\2\u015b\u015c\7,\2\2\u015c\u0161\3\2\2\2\u015d\u0160\5\u0087"+
		"D\2\u015e\u0160\13\2\2\2\u015f\u015d\3\2\2\2\u015f\u015e\3\2\2\2\u0160"+
		"\u0163\3\2\2\2\u0161\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162\u0164\3\2"+
		"\2\2\u0163\u0161\3\2\2\2\u0164\u0165\7,\2\2\u0165\u0166\7\61\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0168\bD\2\2\u0168\u0088\3\2\2\2\u0169\u016a\7\61"+
		"\2\2\u016a\u016b\7\61\2\2\u016b\u016f\3\2\2\2\u016c\u016e\n \2\2\u016d"+
		"\u016c\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2"+
		"\2\2\u0170\u0172\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173\bE\2\2\u0173"+
		"\u008a\3\2\2\2\13\2\u010a\u010e\u0114\u0116\u0156\u015f\u0161\u016f\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}