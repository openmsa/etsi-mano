lexer grammar EtsiLexer;

COMMA: ',';
SLASH: '/';
OPEN_BRACKET: '(';
CLOSE_BRACKET: ')';
SEMICOLON: ';';
EQUAL: '=';
DOT: '.';
AMPERSAND: '&';

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

ATTRIBUTE: [a-zA-Z0-9]+;

STRING: ~[.=,&]+;
