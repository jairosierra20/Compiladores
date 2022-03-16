package codigo;
import static codigo.Tokens.*;
%%


%class Lexer
%full
%line
%column
%char
%state Coment
%type Tokens
 

L=[a-zA-Z_]
D=[0-9]
numero = ("(-"{D}+")")|{D}+
identificador = {L}({L}|{D})*
Comillas = [\"]
apost = "'"
OPERL = <>|>|<|=|<=|>=
SumasRest =  ["+"|"-"]
MultDiv = ["*"|"/"]
Enter = \r|\n|\r\n
espacio = [ \t\t]+
comentario = '
cadena = {Comillas}[^\"]*{Comillas}




%{


    public String lexeme;
    public int linea;
    public int columna;
%}
%%



<YYINITIAL>{
Impresion  {lexeme=yytext(); return Impresion;}
{espacio} { }
( "if" | "IF" | "If") {lexeme=yytext();return IF;}
("for" | "FOR" | "For") {lexeme=yytext();return For;}
"Integer"  {lexeme=yytext(); return Integer;} 
"Double" {lexeme=yytext(); return Double;}
("while" | "While" | "WHILE") {lexeme=yytext(); return While;}
"Boolean" {lexeme=yytext(); return Boolean;}
"Or"   {lexeme=yytext();return OR;}
"And"   {lexeme=yytext();return And;}
("New" | "NEW" | "new" )  {lexeme=yytext();return New;}
"Return"    {lexeme=yytext();return Return;}
("End" | "end" | "END")      {lexeme=yytext();return End;}
("Dim" | "dim" | "DIM")      {lexeme=yytext();return Dim;}
("then" | "Then" | "THEN")      {lexeme=yytext();return then;}
("next" | "NEXT" | "Next")      {lexeme=yytext();return Next;}
"ReadLine"           {lexeme=yytext();return ReadLine;}
("As" | "as" | "AS")       {lexeme=yytext();return AS;}
"Structure"    {lexeme=yytext();return struct;}
("do" | "DO" | "Do")  {lexeme=yytext();return Do;} 
("Main" | "main" | "MAIN")     {lexeme=yytext(); return RMain;}

("Function" | "FUNCTION" | "function") {lexeme=yytext();return Function;}

("LOOP" | "Loop" | "loop")    { lexeme=yytext();return Loop;}
 "."  {lexeme=yytext();return Punto;}
"Console" {lexeme=yytext();return Console;}
"WriteLine" {lexeme=yytext();return WriteLine;}
"_" {lexeme=yytext();return ContinuacionLinea;}

("TO" | "to" | "To")  {lexeme=yytext();return TO;}

"("  {lexeme=yytext();return parentesisE;}
")"  {lexeme=yytext();return parentesisC;}

"{"   {lexeme=yytext();return llaveE;}
"}"   {lexeme=yytext();return llaveC;}
"["   {lexeme=yytext();return corcheteE;}
"]"   {lexeme=yytext();return corcheteC;}
","    {lexeme=yytext();return coma;}

{OPERL}    {lexeme=yytext();return OperadorRelacional;}
{MultDiv}    {lexeme=yytext();return OPMultDiv;}
{SumasRest}    {lexeme=yytext();return OPRestSuma;}

{Enter}         {return Enter;}
{comentario}     {yybegin(Coment);}
"&"     {lexeme=yytext();return concatenacion;}
("Sub" | "sub" | "SUB") {lexeme=yytext(); return Sub; }
"Module" {lexeme=yytext(); return SentenciaModulo;}
("Sub Main" | "sub main" | "Sub main"| "sub Main")   {lexeme=yytext(); return Main;}
("ELSE" | "else" | "Else") {lexeme=yytext(); return Else;}
{Comillas} {return Comilla;}
{identificador} {lexeme=yytext(); return Identificador;}
{cadena}        {lexeme=yytext(); return Cadena;}
{numero}        {lexeme=yytext(); return Numero;}
 . { lexeme=yytext();linea = yyline;columna = yycolumn ;return ERROR;   }
}


<Coment>{
        {Enter}   {yybegin(YYINITIAL);}
        .      {lexeme=yytext(); return Comentario;}
}