grammar Grammar;

start: statements <EOF>;

statements: statement statements | statement;

statement:
   'if' '(' logical_expr')' '{' statements '}' |
   'while' '(' logical_expr')' '{' statements '}' |
   ID  '=' expr_level0 ';' |
   'read' ID ';' |
   'print' expr_level0 ';' ;

logical_expr: expr_level0 '<' expr_level0|
    expr_level0 '>' expr_level0 |
    expr_level0 '==' expr_level0 |
    expr_level0 '!=' expr_level0;

expr_level0: expr_level1 '+' expr_level0
    | expr_level1 '-' expr_level0
    | expr_level1;

expr_level1: expr_level2 '*' expr_level1
    | expr_level2 '/' expr_level1
    | expr_level2;

expr_level2: expr_level3 | '-' expr_level3;

expr_level3: ID
    | INT
    | '(' expr_level0 ')';

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