
public class Gene {
    private static final int[] ZERO = new int[]{0, 0, 0, 0, 0};
    private static final int[] ONE = new int[]{0, 0, 0, 0, 1};
    private static final int[] TWO = new int[]{0, 0, 0, 1, 0};
    private static final int[] THREE = new int[]{0, 0, 0, 1, 1};
    private static final int[] FOUR = new int[]{0, 0, 1, 0, 0};
    private static final int[] FIVE = new int[]{0, 0, 1, 0, 1};
    private static final int[] SIX = new int[]{0, 0, 1, 1, 0};
    private static final int[] SEVEN = new int[]{0, 0, 1, 1, 1};
    private static final int[] EIGHT = new int[]{0, 1, 0, 0, 0};
    private static final int[] NINE = new int[]{0, 1, 0, 0, 1};
    private static final int[] PLUS = new int[]{0, 1, 0, 1, 0};
    private static final int[] MINUS = new int[]{0, 1, 0, 1, 1};
    private static final int[] MULT = new int[]{0, 1, 1, 0, 0};
    private static final int[] DIV = new int[]{0, 1, 1, 0, 1};
    private static final int[] SIN = new int[]{0, 1, 1, 1, 0};
    private static final int[] COS = new int[]{0, 1, 1, 1, 1};
    private static final int[] TAN = new int[]{1, 0, 0, 0, 0};
    private static final int[] ARCSIN = new int[]{1, 0, 0, 0, 1};
    private static final int[] ARCCOS = new int[]{1, 0, 0, 1, 0};
    private static final int[] ARCTAN = new int[]{1, 0, 0, 1, 1};
    private static final int[] EXP = new int[]{1, 0, 1, 0, 0};
    private static final int[] X1 = new int[]{1, 0, 1, 0, 1};
    private static final int[] X4 = new int[]{1, 0, 1, 1, 0};
    private static final int[] POW = new int[]{1, 0, 1, 1, 1};
    private static final int[] SQRT = new int[]{1, 1, 0, 0, 0};
    private static final int[] PI = new int[]{1, 1, 0, 0, 1};
    private static final int[] X2 = new int[]{1, 1, 0, 1, 0};
    private static final int[] LN = new int[]{1, 1, 0, 1, 1};
    private static final int[] ABS = new int[]{1, 1, 1, 0, 0};
    //private static final int[] ARCTAN2 = new int[] { 1, 1, 1, 0, 1 };
    private static final int[] X3 = new int[]{1, 1, 1, 0, 1};
    private static final int[] P1 = new int[]{1, 1, 1, 1, 0};
    private static final int[] P2 = new int[]{1, 1, 1, 1, 1};
    private int[] gene;

    public Gene(String gene) {
        // System.out.println(gene);
        if (gene.equals(toString(ZERO)))
            this.gene = ZERO;
        else if (gene.equals(toString(ONE)))
            this.gene = ONE;
        else if (gene.equals(toString(TWO)))
            this.gene = TWO;
        else if (gene.equals(toString(THREE)))
            this.gene = THREE;
        else if (gene.equals(toString(FOUR)))
            this.gene = FOUR;
        else if (gene.equals(toString(FIVE)))
            this.gene = FIVE;
        else if (gene.equals(toString(SIX)))
            this.gene = SIX;
        else if (gene.equals(toString(SEVEN)))
            this.gene = SEVEN;
        else if (gene.equals(toString(EIGHT)))
            this.gene = EIGHT;
        else if (gene.equals(toString(NINE)))
            this.gene = NINE;
        else if (gene.equals(toString(PLUS)))
            this.gene = PLUS;
        else if (gene.equals(toString(MINUS)))
            this.gene = MINUS;
        else if (gene.equals(toString(MULT)))
            this.gene = MULT;
        else if (gene.equals(toString(DIV)))
            this.gene = DIV;
        else if (gene.equals(toString(SIN)))
            this.gene = SIN;
        else if (gene.equals(toString(COS)))
            this.gene = COS;
        else if (gene.equals(toString(TAN)))
            this.gene = TAN;
        else if (gene.equals(toString(ARCSIN)))
            this.gene = ARCSIN;
        else if (gene.equals(toString(ARCCOS)))
            this.gene = ARCCOS;
        else if (gene.equals(toString(ARCTAN)))
            this.gene = ARCTAN;
        else if (gene.equals(toString(EXP)))
            this.gene = EXP;
        else if (gene.equals(toString(X1)))
            this.gene = X1;
        else if (gene.equals(toString(POW)))
            this.gene = POW;
        else if (gene.equals(toString(SQRT)))
            this.gene = SQRT;
        else if (gene.equals(toString(PI)))
            this.gene = PI;
        else if (gene.equals(toString(X2)))
            this.gene = X2;
        else if (gene.equals(toString(LN)))
            this.gene = LN;
        else if (gene.equals(toString(ABS)))
            this.gene = ABS;
        else if (gene.equals(toString(X3)))
            this.gene = X3;
        else if (gene.equals(toString(P1)))
            this.gene = P1;
        else if (gene.equals(toString(P2)))
            this.gene = P2;
        else if (gene.equals(toString(X4)))
            this.gene = X4;
        else { //FIXME: THIS STILL HAPPENS, NO IDEA WHY
            //System.out.println(gene);
            //System.exit(1);
        }
        // System.out.println(toBinaryString());
    }

    private String toString(int[] gene) {
        String s = "";
        for (int i = 0; i < gene.length; i++) {
            s += gene[i];
        }
        return s;
    }

    public int[] getGene() {
        return gene;
    }

    public boolean isOp() {
        return (gene.equals((PLUS)) || gene.equals((MINUS)) || gene.equals((MULT)) || gene.equals((DIV)));
    }

    public boolean isMathOp() {
        return gene.equals(SIN) || gene.equals(COS) || gene.equals(TAN) || gene.equals(ARCSIN) || gene.equals(ARCCOS) || gene.equals(ARCTAN)
                || gene.equals(EXP) || gene.equals(POW) || gene.equals(SQRT) || gene.equals(ABS) || gene.equals(LN);
    }

    public boolean isX() {
        return gene.equals(X1) || gene.equals(X2) || gene.equals(X3) || gene.equals(X4);
    }

    public boolean isParanethesis() {
        return gene.equals(P1) || gene.equals(P2);
    }

    public boolean isPow() {
        return gene.equals(POW);
    }

    public boolean isNum() {
        return !isParanethesis() && !isOp() && !isMathOp() && !isX();
    }

    public String toBinaryString() {
        return toString(gene);
    }

    public String toString() {
        // System.out.println(toString(gene));
        String total = "";
        if (gene.equals((ZERO)))
            total = "0";
        else if (gene.equals((ONE)))
            total = "1";
        else if (gene.equals((TWO)))
            total = "2";
        else if (gene.equals((THREE)))
            total = "3";
        else if (gene.equals((FOUR)))
            total = "4";
        else if (gene.equals((FIVE)))
            total = "5";
        else if (gene.equals((SIX)))
            total = "6";
        else if (gene.equals((SEVEN)))
            total = "7";
        else if (gene.equals((EIGHT)))
            total = "8";
        else if (gene.equals((NINE)))
            total = "9";
        else if (gene.equals((PLUS)))
            total = "+";
        else if (gene.equals((MINUS)))
            total = "-";
        else if (gene.equals((MULT)))
            total = "*";
        else if (gene.equals((DIV)))
            total = "/";
        else if (gene.equals((SIN)))
            total = "Math.sin(";
        else if (gene.equals((COS)))
            total = "Math.cos(";
        else if (gene.equals((TAN)))
            total = "Math.tan(";
        else if (gene.equals((ARCSIN)))
            total = "Math.asin(";
        else if (gene.equals((ARCCOS)))
            total = "Math.acos(";
        else if (gene.equals((ARCTAN)))
            total = "Math.atan(";
        else if (gene.equals((EXP)))
            total = "Math.exp(";
            // else if (gene.equals((LOG)))
            // total = "Math.log( ) / Math.log(10)";
        else if (gene.equals((X1)))
            total = "X";
        else if (gene.equals((POW)))
            total = "Math.pow(";
        else if (gene.equals((SQRT)))
            total = "Math.sqrt(";
        else if (gene.equals((PI)))
            total = "Math.PI";
        else if (gene.equals((X2)))
            total = "X";
        else if (gene.equals((LN)))
            total = "Math.log(";
        else if (gene.equals((ABS)))
            total = "Math.abs(";
        else if (gene.equals((X3)))
            total = "X";
        else if (gene.equals((P1)))
            total = ")";
        else if (gene.equals((P2)))
            total = ")";
        else if (gene.equals((X4)))
            total = "X";
        return total;
    }

    public boolean equals(Gene g) {
        return g.getGene().equals(gene);
    }
}
