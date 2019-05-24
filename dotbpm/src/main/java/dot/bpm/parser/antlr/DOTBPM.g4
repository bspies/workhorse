grammar DOTBPM;

process         :   PROCESS id '{' stmt_list '}' ;
stmt_list       :   ( stmt ';'? )* ;
stmt            :   activity ;
activity        :   '[' activity_type ':' id ('|' attr_list)? ']' ;
activity_type   : 'task' | 'script' | 'action';
event           :   '(' event_type ')' ;
event_type      :   '>' | '-' | '<<' | 'X' | '*' | '+' | '!' | '?' | '^' | '~' | '@' | '..' ;
gateway         :   '<' gateway_type | event '>' ;
gateway_type    :   'X' | 'O' | '+' | '*';
association     :   '--' ;
seq_flow        :   '=>' ;
msg_flow        :   '->' ;
attr_list       :   (id ('=' id)? ','?)+ ;
id              :   ID | STRING ;

/* "keywords are case-insensitive" */
PROCESS     :   [Pp][Rr][Oo][Cc][Ee][Ss][Ss] ;
SUBPROCESS  :   [Ss][Uu][Bb][Pp][Rr][Oo][Cc][Ee][Ss][Ss] ;

/** "any double-quoted string ("...") possibly containing escaped quotes" */
STRING      :   '"' ('\\"'|.)*? '"' ;

/** "a numeral [-]?(.[0-9]+ | [0-9]+(.[0-9]*)? )" */
NUMBER      :   '-'? ('.' DIGIT+ | DIGIT+ ('.' DIGIT*)? ) ;
fragment
DIGIT       :   [0-9] ;

/** "Any string of alphabetic ([a-zA-Z\200-\377]) characters, underscores
 *  ('_') or digits ([0-9]), not beginning with a digit"
 */
ID          :   LETTER (LETTER|DIGIT)*;
fragment
LETTER      :   [a-zA-Z\u0080-\u00FF_] ;

COMMENT     :   '/*' .*? '*/'       -> skip ;
LINE_COMMENT:   '//' .*? '\r'? '\n' -> skip ;