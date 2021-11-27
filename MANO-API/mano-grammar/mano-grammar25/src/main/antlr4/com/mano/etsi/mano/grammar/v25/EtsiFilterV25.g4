parser grammar EtsiFilterV25;

options { tokenVocab=EtsiLexerV25; }

simpleFilterExprOne : opOne COMMA attrName(SLASH attrName)* COMMA value;
simpleFilterExprMulti : opMulti COMMA attrName (SLASH attrName)*COMMA value(COMMA value)*;
simpleFilterExpr: OPEN_BRACKET simpleFilterExprOne CLOSE_BRACKET | OPEN_BRACKET simpleFilterExprMulti CLOSE_BRACKET;

filterExpr       : simpleFilterExpr ( SEMICOLON simpleFilterExpr)*;
filter: FILTER EQUAL filterExpr;


opOne: EQ | NEQ | GT | LT | GTE | LTE;
opMulti: IN | NIN | CONT | NCONT;

attrName      : ATTRIBUTE;
value            : STRING;
