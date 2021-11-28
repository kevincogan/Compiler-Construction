grammar Expr;


@header {
    package antlr;
}

// Non-terminals
prog
    : (decl_list) (function_list) (main) 											#progStm //
    ;

decl_list
    : ((decl) (SEMI) (decl_list) | ( /*empty*/ ))													#declListStm //no empty in orginal
    ;

decl
    : (var_decl)																	#varDeclRef //
    | (const_decl)																	#constDeclRef //
    ;

var_decl
    : (Variable) (ID) (COLON) (type)												#varDeclStm  //
    ;

const_decl
    : (Constant) (ID) (COLON) (type) (<assoc=right> ASSIGN) (expression) 			#constDeclStm  //
    ;

function_list
    : ((function) (function_list) | /*empty*/ ) 									#funcListStm // No empty
    ;

function
    : (type) (ID) (LBR) (parameter_list) (RBR)
      (BEGIN)
             (decl_list)
             (statement_block)
             (Return) (LBR) (expression |/*empty*/) (RBR) (SEMI) 
      (END)																			#funcDeclStm //
    ;

type
    : Integer
    | Boolean
    | Void
    ;

parameter_list
    : (nemp_parameter_list| /*empty*/) 															# nonEmptyParamRef // No empty
    ;

nemp_parameter_list
    : (ID) (COLON) (type) 															# singleParamStm //
    | (ID) (COLON) (type) (COMMA) (nemp_parameter_list)  							# multipleParamStm//
    ;

main
    : (Main) (BEGIN)
                (decl_list)
                (statement_block)
             (END)                        											# mainStm //
    ;

statement_block
    : (statement) (statement_block)      											# stmBlockRef //
    | ((decl) (SEMI) (statement_block)|( /*empty*/ ))												# emptyStatment //Orginally no empty statement at the end
    ;

statement
    : (ID) ( <assoc=right> ASSIGN) (expression) (SEMI)    							# assignStm //
    | (ID) (LBR) (arg_list) (RBR) (SEMI)				  							# funcCallStm //
    | (BEGIN) (statement_block) (END)                     							# beginStm //
    | (If) (condition) (BEGIN) (statement_block) (END)(Else) (BEGIN) (statement_block) (END) # conditionalStm //
    | (While) (condition) (BEGIN) (statement_block) (END) 							# whileStm //
    | (Skip) (SEMI) 																# skipStm //
    ;

expression
    : (frag) (binary_arith_op) (frag) 												# fragBinArithStm //
    | (LBR) (expression) (RBR)														# parenExpreStm  //
    | (ID) (LBR) (arg_list) (RBR)  													# funcCallExpr //
    | (binary_arith_op) (expression)											    # fragUnaArithStm  // New to the grammar
    | (frag) 																		# fragRef //
    ;

binary_arith_op
    : PLUS																			# additionStm //
    | MINUS																			# subtractionStm //
    ;

frag
    : ID																			# idFrag //
    | (<assoc=right> MINUS) (ID)													# negationStm //
    | NUMBER																		# intStm //
    | True																			# trueStm //
    | False																			# falseStm //
    | (LBR) (expression) (RBR)                                                      #bracketExpr //Not in orginal
    ;

condition
    : <assoc=right> (NEGATION) (condition) 										    # boolNegStm //
    | (LBR) (condition) (RBR)														# parenConditionalStm //
    | (expression) (comp_op) (expression)											# boolArithStm //
    | condition (OR | AND) condition												# boolEvalStm //
    ;

comp_op  // OR is in this orginal version...
    :  OR																			# logOr //
    |EQUAL																			# logEq //
    | NOTEQUAL																		# logNEq //
    | LT																			# logLT //
    | LTE																			# logLTE //
    | GT																		 	# logGT //
    | GTE																			# logGTE //
    ;

arg_list
    : (nemp_arg_list|( /*empty*/ )) 																# nonEmptyArgListRef //
    ;

nemp_arg_list
    : ID																			# idArgRef //
    | ID COMMA nemp_arg_list														# multIdArgRef //
    ;


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