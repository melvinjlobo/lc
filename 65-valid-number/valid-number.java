/**
 START(0) -> <space> -> START(0)
 START(0) -> '+/-' -> SIGN(1)
 START(0) -> digit -> INT(2)
 START(0) -> dot -> DECIMAL(3)
 
 SIGN(1) -> digit -> INT(2)
 SIGN(1) -> dot -> DECIMAL(3)

 INT(2) -> digit -> INT(2)
 INT(2) -> e/E -> EXP(4)
 INT(2) -> dot -> DECIMAL(3)

 DECIMAL(3) -> digit -> FRAC(5)
 DECIMAL(3) -> e/E -> EXP(4)

 EXP(4) -> +/- -> EXP_SIGN(6)
 EXP(4) -> digit -> EXP_NUM(7)

 FRAC(5) -> digit -> FRAC(5)
 FRAC(5) -> e/E -> EXP(4)
 
 EXP_SIGN(6) -> digit -> EXP_NUM(7)
 EXP_NUM(7) -> digit -> EXP_NUM(7) 
 
 ANY -> <space> -> END_SPACE(8)
 END_SPACE(8) -> END_SPACE(8)

 */


class Solution {
    static final char DIGIT = '#', OP = '*', EXPONENT = '^';

    public boolean isNumber(String s) {
        final int START = 0, SIGN = 1, INT = 2, DECIMAL = 3, EXP = 4, FRAC = 5, EXP_SIGN = 6, EXP_NUM = 7, END_SPACE = 8;
        

        int state = START;
        boolean hasDigit = false;

        s = s.trim();

        for(char c : s.toCharArray()) {
            c = getOp(c);
            switch(state) {
                case START:     //START
                    if(c == OP)
                        state = SIGN;
                    else if(c == DIGIT) {
                        state = INT;
                        hasDigit = true;
                    }
                    else if (c == '.')
                        state = DECIMAL;
                    else
                        return false;
                
                    break;
                case SIGN:      //SIGN
                    if(c == DIGIT) {
                        state = INT;
                        hasDigit = true;
                    }
                    else if (c == '.')
                        state = DECIMAL;
                    else
                        return false;
                    
                    break;
                case INT:       // NUM
                    if( c == EXPONENT)
                        state = EXP;
                    else if (c == '.')
                        state = DECIMAL;
                    else if (c == DIGIT) {
                        state = INT;
                        hasDigit = true;
                    }
                    else
                        return false;
                    
                    break;
                case DECIMAL:
                    if( c == DIGIT) {
                        state = FRAC;
                        hasDigit = true;
                    }
                    else if( (c == EXPONENT) && (hasDigit)) {
                        state = EXP;
                    }
                    else
                        return false;
                    break;
                case EXP:
                    if(c == OP)
                        state = EXP_SIGN;
                    else if (c == DIGIT) {
                        state = EXP_NUM;
                        hasDigit = true;
                    }
                    else
                        return false;
                    break;
                case FRAC:
                    if(c == EXPONENT)
                        state = EXP;
                    else if (c == DIGIT)
                        state = FRAC;
                    else
                        return false;
                    break;
                case EXP_SIGN:
                    if(c == DIGIT) {
                        state = EXP_NUM;
                        hasDigit = true;
                    }
                    else
                        return false;
                    break;
                case EXP_NUM:
                    if(c == DIGIT) {
                        state = EXP_NUM;
                        hasDigit = true;
                    }
                    else
                        return false;
                    break;
                
                default:
                    return false;

            }
        }

        System.out.println("Final state is - " + state);

        return hasDigit && (state == INT || state == FRAC || state == EXP_NUM || state == DECIMAL);

    }

    public char getOp(char c) {
        if(Character.isDigit(c))
            return DIGIT;
        else if(c == 'e' || c == 'E')
            return EXPONENT;
        else if (c == '+' || c == '-')
            return OP;
        else return c; 
            
        
    }
}