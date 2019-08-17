lexer grammar EtsiLexerV25;

@lexer::header {
	package com.ubiqube.etsi.mano.grammar.v25;
}

COMMA: ',';
SLASH: '/';
OPEN_BRACKET: '(';
CLOSE_BRACKET: ')';
SEMICOLON: ';';
EQUAL: '=';


EQ: 'eq';
NEQ: 'neq';

GT: 'gt';
LT: 'lt';
GTE: 'gte';
LTE: 'lte';

IN: 'in';
NIN: 'nin';
CONT: 'cont';
NCONT: 'ncont'; 
FILTER: 'filter';

ATTRIBUTE: [a-zA-Z]+;
STRING: ~('('|')'|'.'|'='|','|'/')+;

