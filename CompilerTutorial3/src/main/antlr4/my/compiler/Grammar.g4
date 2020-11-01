grammar Grammar;

start: statements <EOF>;

statements: statement statements
    | statement ;

statement:
   'if' '(' logical_expr')' '{' statements '}' # IfStatement|
   'while' '(' logical_expr')' '{' statements '}' # WhileStatement|
   ID  '=' expr_level0 ';' # AssignStatement|
   'read' ID ';' # ReadStatement|
   'print' expr_level0 ';' # PrintStatement;

logical_expr: expr_level0 '<' expr_level0 #LogicLt |
    expr_level0 '>' expr_level0 #LogicGt |
    expr_level0 '==' expr_level0 #LogicEq |
    expr_level0 '!=' expr_level0 #LogicNeq;

expr_level0: expr_level1 '+' expr_level0 #ArithPlus
    | expr_level1 '-' expr_level0 #ArithMinus
    | expr_level1 #ArithLvl0;

expr_level1: expr_level2 '*' expr_level1 #ArithTimes
    | expr_level2 '/' expr_level1#ArithDiv
    | expr_level2 #ArithLvl1;

expr_level2: expr_level3 #ArithLvl2
    | '-' expr_level3 #ArithMinusUnary;

expr_level3: ID # ArithVar
    | INT # ArithInt
    | '(' expr_level0 ')' #ArithLvl3;

INT: [0-9]+;

ID:[a-zA-Z]+;

WS
:
	(
		' '
		| '\t'
		| '\n'
	)+ -> channel ( HIDDEN )
;