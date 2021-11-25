grammar Expr;


@header {
    package antlr;
}

prog: (decl | expr)+ EOF                #Program
    ;

decl: ID ':' INT_TYPE '=' NUMBER           #Declaration
    ;

expr: expr '*' expr                     #Multiplication
    | expr '+' expr                     #Addition
    | ID                                #Variable
    | NUMBER                               #Number
    ;

INT_TYPE: 'INT';


////////////////////////////////////////////////////////////////////////////////
//ID: [a-z][a-zA-Z0-9_]*;
//NUM: '0' | '-'?[1-9][0-9]*;


//Whitespaces and Comments.
//WS:			               [ \t\r\n\u000C]+ -> skip;
//COMMENT:               '/*' (COMMENT| .)*? '*/' -> skip;
//SINGLE_COMMENT:        '//' ~[\r\n]*    -> skip;

///////////////////////////////////////////////////////////////////////////////

// Terminals / Reserved words.
Variable:              V A R;
Constant:              C O N S T;
Return:                R E T U R N;
Integer:               I N T E G E R;
Boolean:				       B O O L E A N;
Void:                  V O I D;
Main:                  M A I N;
If:                    I F;
Else:                  E L S E;
True:                  T R U E;
False:                 F A L S E;
While:                 W H I L E;
Skip:                  S K I P;

// Separators.
COMMA:				         ',';
SEMI:			             ';';
COLON: 				         ':';
BEGIN:					       '{';
END:					         '}';
LBR:		               '(';
RBR:		               ')';

// Operators.
ASSIGN:				         '=';
PLUS:				           '+';
MINUS:				         '-';
NEGATION:              '~';
OR:					           '||';
AND:				           '&&';
EQUAL:				         '==';
NOTEQUAL:			         '!=';
LT:				             '<';
LTE:		               '<=';
GT:			               '>';
GTE:	                 '>=';

// Definitions
NUMBER:		             ([1-9][0-9]*) | '0';
ID:					           Letter (Letter | Underscore | NUMBER)*;


// Alphabet Fragments: for case insensitivity.
fragment A:		         'a' | 'A';
fragment B:		         'b' | 'B';
fragment C:		         'c' | 'C';
fragment D:		         'd' | 'D';
fragment E:		         'e' | 'E';
fragment F:		         'f' | 'F';
fragment G:		         'g' | 'G';
fragment H:		         'h' | 'H';
fragment I:		         'i' | 'I';
fragment J:		         'j' | 'J';
fragment K:		         'k' | 'K';
fragment L:		         'l' | 'L';
fragment M:		         'm' | 'M';
fragment N:		         'n' | 'N';
fragment O:		         'o' | 'O';
fragment P:		         'p' | 'P';
fragment Q:		         'q' | 'Q';
fragment R:		         'r' | 'R';
fragment S:		         's' | 'S';
fragment T:		         't' | 'T';
fragment U:		         'u' | 'U';
fragment V:		         'v' | 'V';
fragment W:		         'w' | 'W';
fragment X:		         'x' | 'X';
fragment Y:		         'y' | 'Y';
fragment Z:		         'z' | 'Z';

fragment Letter:		   [a-zA-Z];
fragment Digit:			   [0-9];
fragment Underscore:	 '_';


//Whitespaces and Comments.
WS:			               [ \t\r\n\u000C]+ -> skip;
COMMENT:               '/*' (COMMENT| .)*? '*/' -> skip;
SINGLE_COMMENT:        '//' ~[\r\n]*    -> skip;
