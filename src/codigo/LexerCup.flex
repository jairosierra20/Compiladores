package codigo;
import java_cup.runtime.Symbol;
%%


%class Lexico
%full
%line
%column
%char
%state Coment
%type java_cup.runtime.Symbol
%cup
 

L=[a-zA-Z_]
D=[0-9]
numero = ("(-"{D}+")")|{D}+
identificador = {L}({L}|{D})*
Comillas = [\"]
OPERL = <>|>|<|<=|>=
SumasRest =  ["+"|"-"]
MultDiv = ["*"|"/"]
Enter = \r|\n|\r\n
espacio = [ \t\t]+
apost = [']
comentario = {apost}[^\"]*{apost}
Igual = "="
Op_Booleano = ["true"|"false"|"True"|"False"]
cadena = {Comillas}[^\"]*{Comillas}




%{
private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }


    public String lexeme;
    public int linea;
    public int columna;
%}
%%



<YYINITIAL>{ 
{espacio} { }
( "if" | "IF" | "If") {return new Symbol(sym.IF, yychar, yyline, yytext());}
("for" | "FOR" | "For") {return new Symbol(sym.For, yychar, yyline, yytext());}
("Integer")  {return new Symbol(sym.Integer, yychar, yyline, yytext());} 
("Double") {return new Symbol(sym.Double, yychar, yyline, yytext());}
("while" | "While" | "WHILE") {return new Symbol(sym.While, yychar, yyline, yytext());}
("Boolean") {return new Symbol(sym.Boolean, yychar, yyline, yytext());}
("Or" )  {return new Symbol(sym.OR, yychar, yyline, yytext());}
("And")   {return new Symbol(sym.And, yychar, yyline, yytext());}
("Return" | "return" | "RETURN")    {return new Symbol(sym.Return, yychar, yyline, yytext());}
("End" | "end" | "END")       {return new Symbol(sym.End, yychar, yyline, yytext());}
("Dim" | "dim" | "DIM")       {return new Symbol(sym.Dim, yychar, yyline, yytext());}
("then" | "Then" | "THEN")       {return new Symbol(sym.then, yychar, yyline, yytext());}
("As" | "as" | "AS")       {return new Symbol(sym.AS, yychar, yyline, yytext());}
("Structure")    {return new Symbol(sym.struct, yychar, yyline, yytext());}
("Main" | "main" | "MAIN")    {return new Symbol(sym.RMain, yychar, yyline, yytext());}
("Function" | "FUNCTION" | "function") {return new Symbol(sym.Function, yychar, yyline, yytext());}
("New" | "NEW" | "new" )   {return new Symbol(sym.New, yychar, yyline, yytext());}
("do" | "DO" | "Do")  {return new Symbol(sym.Do, yychar, yyline, yytext());} 
( ".")  {return new Symbol(sym.Punto, yychar, yyline, yytext());}
("TO" | "to" | "To")  {return new Symbol(sym.TO, yychar, yyline, yytext());}
("_") {return new Symbol(sym.ContinuacionLinea, yychar, yyline, yytext());}
("LOOP" | "Loop" | "loop") {return new Symbol(sym.Loop, yychar, yyline, yytext());}
("(")  {return new Symbol(sym.parentesisE, yychar, yyline, yytext());}
(")")  {return new Symbol(sym.parentesisC, yychar, yyline, yytext());}

("{")   {return new Symbol(sym.llaveE, yychar, yyline, yytext());}
("}")   {return new Symbol(sym.llaveC, yychar, yyline, yytext());}
("[")   {return new Symbol(sym.corcheteE, yychar, yyline, yytext());}
("]")   {return new Symbol(sym.corcheteC, yychar, yyline, yytext());}
(",")   {return new Symbol(sym.coma, yychar, yyline, yytext());}
({OPERL})    {return new Symbol(sym.OperadorRelacional, yychar, yyline, yytext());}
({MultDiv})    {return new Symbol(sym.OPMultDiv, yychar, yyline, yytext());}
({SumasRest})    {return new Symbol(sym.OPRestSuma, yychar, yyline, yytext());}
("next" | "NEXT" | "Next")     {return new Symbol(sym.Next, yychar, yyline, yytext());}
{Enter}         { return new Symbol(sym.Enter, yychar, yyline, yytext()); }

("ReadLine")      {return new Symbol(sym.ReadLine, yychar, yyline, yytext());}
{comentario}     {return new Symbol(sym.Comentario, yychar, yyline, yytext());}
("&" )    {return new Symbol(sym.concatenacion, yychar, yyline, yytext());}
("Sub" | "sub" | "SUB") {return new Symbol(sym.Sub, yychar, yyline, yytext());}
("Sub Main" | "sub main" | "Sub main"| "sub Main") {return new Symbol(sym.Main, yychar, yyline, yytext());}


("Module") {return new Symbol(sym.SentenciaModulo, yychar, yyline, yytext());}
("Console") {return new Symbol(sym.Console, yychar, yyline, yytext());}
("WriteLine") {return new Symbol(sym.WriteLine, yychar, yyline, yytext());}


("true"|"false"|"True"|"False"|"TRUE"|"FALSE")   {return new Symbol(sym.Op_Booleano, yychar, yyline, yytext());}
("ELSE" | "else" | "Else")   {return new Symbol(sym.Else, yychar, yyline, yytext());}
{Comillas} {return new Symbol(sym.Comilla, yychar, yyline, yytext());}
{identificador} {return new Symbol(sym.Identificador, yychar, yyline, yytext()); }

{Igual}  {return new Symbol(sym.Igual, yychar, yyline, yytext());}
{cadena}        {return new Symbol(sym.Cadena, yychar, yyline, yytext());}
{numero}        {return new Symbol(sym.Numero, yychar, yyline, yytext());}
 . { return new Symbol(sym.ERROR, yychar, yyline, yytext());   }
}


<Coment>{
        {Enter}   {yybegin(YYINITIAL);}
        .      {return new Symbol(sym.Comentario, yychar, yyline, yytext());}
}