public class Chromosome {
    private Gene[] chromosome;
    private double fitness;

    public Chromosome(Gene[] chromosome) {
        this.chromosome = chromosome;
        fitness = Double.NaN;
    }

    public double getFitness() {
        return fitness;
    }

    public double getFitness(double[] x, double[] y) {
        if (fitness == fitness) return fitness;
        double returnValue = 0;
        for (int i = 0; i < x.length; i++) {
            String eq = null;
            try {
                eq = getEquation().replace("X", String.valueOf(x[i]));
            } catch (Exception e) {
                //System.out.println(x[i]);
                return Double.MAX_VALUE;
            }
            double ans = calculate(eq);
            if (ans == ans) returnValue += Math.abs(y[i] - ans);
            else returnValue = Double.MAX_VALUE;
            //System.out.println(ans + " " + returnValue);
        }
        fitness = Math.abs(returnValue / x.length);
        return fitness == fitness ? fitness : Double.MAX_VALUE;
    }

    public Gene[] getChromosome() {
        return chromosome;
    }

    public String getEquation() {
        String total = "";
        int openParentheisCount = 0;
        int powCount = 0;
        for (int i = 0; i < chromosome.length; i++) {
            Gene g = chromosome[i];
            if (g == null) System.out.println(i);
            if (i == 0 && g.isOp()) {
                continue;
            } else if (g.isMathOp()) {
                if (i != 0 && chromosome[i - 1].isX()) total += "*";
                if (total.length() != 0 && ((total.charAt(total.length() - 1) == ')' || total.charAt(total.length() - 1) == 'X') || (total.charAt(total.length() - 1) >= '0' && total.charAt(total.length() - 1) <= '9')))
                    total += "*";
                openParentheisCount++;
//                if(g.isPow()){
//                    try {
//                        total += g;
//                        total += chromosome[i + 1];
//                        total += ",";
//                        i++;
//                        continue;
//                    }catch(ArrayIndexOutOfBoundsException e){}
//                    addComma = true;
//                }
                total += g;
            } else if (g.isParanethesis()) {
                if (total.length() != 0 && total.charAt(total.length() - 1) >= '+' && total.charAt(total.length() - 1) <= '/') {
                    continue;
                }
                if (openParentheisCount > 0) total += g;
                openParentheisCount--;
            } else if (g.isX()) {
                if (i == 0) total += g;
                else if (chromosome[i - 1].isOp() || chromosome[i - 1].isMathOp())
                    total += g;
                else if (total.length() == 0) total += g;
                else if (total.length() != 0 && total.charAt(total.length() - 1) == '(') total += g;
                else if (total.length() != 0 && total.charAt(total.length() - 1) >= '0' && total.charAt(total.length() - 1) <= '9')
                    total += "*" + g;
                else total += "*" + g;
            } else if (g.isNum()) {
                if (i == 0) total += g;
                else if (chromosome[i - 1].isOp() || chromosome[i - 1].isMathOp())
                    total += g;
                else if (total.length() == 0) total += g;
                else if (total.length() != 0 && total.charAt(total.length() - 1) == '(') total += g;
                else if (total.length() != 0 && total.charAt(total.length() - 1) >= '0' && total.charAt(total.length() - 1) <= '9')
                    total += "*" + g;
                else total += "*" + g;
            } else if (g.isOp()) {
                if ((total.charAt(total.length() - 1) >= '+' && total.charAt(total.length() - 1) <= '/') || total.charAt(total.length() - 1) == '(')
                    continue;
                else total += g;
            }
            if (powCount > 0 && (total.charAt(total.length() - 1) != '(')) { //FIXME: If addComma is true and the next gene is a MathOP the equation will not compile
                total += ",";
                powCount--;
            }
            if (g.isPow()) powCount++;
        }
        int pCount = 0;
        int powPCount = 0;
        for (int i = 0; i < total.length() - 2; i++) {
            if (total.charAt(i) == '(') pCount++;
            if (total.charAt(i) == 'p' && total.charAt(i + 1) == 'o' && total.charAt(i + 2) == 'w') {
                powPCount = pCount;
            }
        }
        for (int i = 0; i < openParentheisCount; i++) {
            if (powPCount != 0 && i == openParentheisCount - powPCount && powCount > 0) total += ",2";
            total += ")";
        }
        int count = 0;
        for (int i = 0; i < total.length(); i++) {
            if (total.charAt(i) == '(') count++;
            else if (total.charAt(i) == ')') count--;
        }
        for (int i = 0; i < count; i++)
            total += ')';
        for (int i = 1; i < total.length() - 1; i++) {
            if (total.charAt(i) == '(' && total.charAt(i + 1) == ')') {
//                if (total.charAt(i - 1) == 'w') {
//                    total = total.substring(0, i + 1) + ",2" + total.substring(i + 2, total.length());
//                    powCount--;
//                }
//                else if (powCount > 0) {
//                    total = total.substring(0, i + 1) + ",2" + total.substring(i + 2, total.length());
//                    powCount--;
//                }
//                else if (total.charAt(i) == ',' && total.charAt(i + 1) == ')')
//                    total = total.substring(0, i + 1) + "2" + total.substring(i + 2, total.length());
//                else
                total = total.substring(0, i + 1) + "X" + total.substring(i + 1, total.length());
                i++;
            } else if (total.charAt(i) == ',' && total.charAt(i + 1) == '*') {
                total = total.substring(0, i) + total.substring(i + 1, total.length());
            }
        }
        if (!total.contains("X")) {
            for (int i = total.length() - 1; i >= 0; i--) {
                if (total.charAt(i) != ')') {
                    total = total.substring(0, i) + "*X" + total.substring(i, total.length());
                    break;
                }
            }
        }
        return total;
    }

    public String toBinaryString() {
        String s = "";
        for (int i = 0; i < chromosome.length; i++) {
            s += chromosome[i].toBinaryString();
        }
        return s;
    }

    public String toString() {
        return getEquation();
    }

    public double calculate(String equation) {
        try {
            double total = (Double.parseDouble(Main.engine.eval(equation).toString()));
            if (Double.isNaN(total)) {
                return (Double.POSITIVE_INFINITY);
            }
            return total;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean equals(Chromosome c) {
        for (int i = 0; i < c.getChromosome().length; i++) {
            if (!c.getChromosome()[i].equals(chromosome[i])) return false;
        }
        return true;
    }
}
