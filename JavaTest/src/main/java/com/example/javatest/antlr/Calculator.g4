
//计算器

grammar Calculator;
@header{
//package com.example.javatest.antlr.calculator;
}
INT :[0-9]+; //匹配数字
MUL :'*';
DIV :'/';
ADD :'+';
SUB :'-';

expr :expr op=('*'|'/') expr #MulDiv
    |expr op=('+'|'-') expr #AddSub
    |INT #Int
    |'(' expr ')' #Parens
    ;

