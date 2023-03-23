package com.example.javatest.antlr.calculator;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/23 16:27
 */
public class AntlrListener extends CalculatorBaseListener{
    private final Deque<Integer> stack = new LinkedList<>();

    /**
     * The last value on the stack is the result of all applied calculations.
     *
     * @return Integer
     */
    public Integer getResult() {
        return this.stack.peek();
    }

    @Override
    public void exitMulDiv(CalculatorParser.MulDivContext ctx) {
        int right = this.stack.pop();
        int left = this.stack.pop();
        if (ctx.op.getType() == CalculatorParser.MUL) {
            this.stack.push(left * right);
        } else {
            this.stack.push(left / right);
        }
    }

    @Override
    public void exitAddSub(CalculatorParser.AddSubContext ctx) {
        int right = this.stack.pop();
        int left = this.stack.pop();
        if (ctx.op.getType() == CalculatorParser.ADD) {
            this.stack.push(left + right);
        } else {
            this.stack.push(left - right);
        }
    }

    @Override
    public void exitInt(CalculatorParser.IntContext ctx) {
        Integer number = Integer.parseInt(ctx.INT().getText());
        this.stack.push(number);
    }
}
