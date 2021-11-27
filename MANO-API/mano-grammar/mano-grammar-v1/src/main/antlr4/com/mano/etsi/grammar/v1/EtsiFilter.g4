parser grammar EtsiFilter;

options { tokenVocab=EtsiLexer; }


simpleFilterExpr: attrName(DOT attrName)*(DOT op)? EQUAL value (COMMA value)*;

filterExpr       : simpleFilterExpr (AMPERSAND simpleFilterExpr )*;

op               : EQ | NEQ | GT | LT | GTE | LTE | CONT | NCONT;
attrName         : ATTRIBUTE;
value            : STRING | ATTRIBUTE;

