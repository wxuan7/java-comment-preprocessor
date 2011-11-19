package com.igormaznitsa.jcpreprocessor.expression.operators;

import com.igormaznitsa.jcpreprocessor.expression.ExpressionStackItemPriority;
import com.igormaznitsa.jcpreprocessor.expression.Value;

public final class OperatorDIV extends AbstractOperator {

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String getReference() {
        return "it makes division of two operands";
    }

    @Override
    public String getKeyword() {
        return "/";
    }

    public Value executeIntInt(final Value arg1, final Value arg2) {
        return Value.valueOf(Long.valueOf(arg1.asLong() / arg2.asLong()));
    }
    
    public Value executeIntFloat(final Value arg1, final Value arg2) {
        return Value.valueOf(Float.valueOf(arg1.asLong() / arg2.asFloat()));
    }
    
    public Value executeFloatInt(final Value arg1, final Value arg2) {
         return Value.valueOf(Float.valueOf(arg1.asFloat() / arg2.asLong()));
    }
    
    public Value executeFloatFloat(final Value arg1, final Value arg2) {
        return Value.valueOf(Float.valueOf(arg1.asFloat() / arg2.asFloat()));
    }

    public ExpressionStackItemPriority getPriority() {
        return ExpressionStackItemPriority.ARITHMETIC_MUL_DIV_MOD;
    }
}
