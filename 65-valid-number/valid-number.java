/**
A DFA is like a flowchart that processes characters in a string one by one. The DFA has:

States: Each state represents where we are in the parsing process.
Transitions: Rules that dictate how we move between states based on the current character.
Final States: Specific states that indicate the string is valid.

Key Transitions
Start with spaces: Ignore any leading spaces.
Optional sign: Accept + or - as the first character.
Digits: The main body of the number can be digits (integer or decimal).
Decimal point: The dot (.) transitions to a fraction if digits follow.
Exponent: The e or E transitions to the exponent part, which must have digits.
Trailing spaces: Spaces at the end are allowed.


Let’s validate the string " 1.23e10 " step by step:

Character	State Before	State After	            Explanation
' '	        0	                0	            Ignore leading space
'1'	        0	                2	            Enter INTEGER state
'.'	        2	                4	            Enter FRACTION state
'2'	        4	                4	            Stay in FRACTION state
'3'	        4	                4	            Stay in FRACTION state
'e'	        4	                5	            Enter EXPONENT state
'1'	        5	                7	            Enter EXPONENT_NUMBER state
'0'	        7	                7	            Stay in EXPONENT_NUMBER state
' '	        7	                7	            Ignore trailing space (valid end state)

Final State: 7 (Valid).


DFA STATES (Each case in the switch refers to a DFA state):

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
 DECIMAL(3) -> e/E && DIGIT -> EXP(4)  <<-  SPECIAL CASE

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

    public boolean isNumberDFA(String s) {
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
        // - We always neewd at least one digit to form a number 
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

    /**
    EASY VERSION WITHOUT DFA

    IMP RULES:
      - We always neewd at least one digit to form a number - seenDigit
      - Sign must be at the beginning ot after exponent (e/E) and only ONE sign
      - Exponent must come AFTER a decimal or dot. If we have see an exponent, we MUST have seen a number before. Only one Exponent Allowed - seenExponent
      - ONLY one dot allowed. NO dots AFTER exponent
      - Everything else invalidates an input
     */
    public boolean isNumber(String s) {
        boolean seenNumber = false, seenExponent = false, seenDot = false;

        for(int i = 0; i < s.length(); i++) {
            char c = getOp(s.charAt(i));
            if (c == DIGIT) {
                seenNumber = true;
            } else if (c == EXPONENT) {
                if(seenExponent || !seenNumber)
                    return false;

                seenExponent = true;
                seenNumber = false;      // We need to do this because a number will restart after every exponent
            } else if (c == OP) {
                // Beginning of the string is fine. We have to handle anything after the first position
                // Sign MUST appear after an e
                if(i > 0 && getOp(s.charAt(i -1)) != EXPONENT)
                    return false;
                
                // No need to set anything as we have already covered all sign constraints with statement above
               
            } else if(c == '.') {
                if(seenDot || seenExponent)
                    return false;
                
                seenDot = true;
            } else {
                return false;
            }

        }

        return seenNumber;

     }


}