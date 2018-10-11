import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Main {
    public static final int DNA_LENGTH = 20;
    public static final int CHROMOSOME_LENGTH = 6;
    public static final double ELITISM = .4;
    public static final double MUTATION_RATE = .1;
    public static final int REPRODUCER_LENGTH = (int) (ELITISM * DNA_LENGTH);
    public static final ScriptEngineManager mgr = new ScriptEngineManager();
    public static final ScriptEngine engine = mgr.getEngineByName("JavaScript");
    public static Chromosome currentMostElite = null;
    public static int count = 0;
    public static long startTime = System.currentTimeMillis();
    public static boolean done;

    public static void main(String[] args) {
        //Values For cos(x)
        //double[] x = new double[]{0, Math.PI / 2, Math.PI, Math.PI * 1.5, Math.PI * 2};
        //double[] y = new double[]{1, 0, -1, 0, 1};
        //Values for x^2
        double[] x = new double[]{1, 2, 3, 4, 5};
        double[] y = new double[]{1, 4, 9, 16, 25};
        //Values for x^3
        //double[] x = new double[]{1, 2, 3, 4, 5};
        //double[] y = new double[]{2, 3, 5, 7, 11};
        //double[] x = new double[]{1,2,3,4,5};
        //double[] y = new double[]{1,8,27,64,125};
        /*double[] x = new double[100];
        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }
        double[] y = new double[100];
        for (int i = 0; i < y.length; i++) {
            if (isPrime(i)) y[i] = 1;
            else y[i] = 0;
        }*/
        //double[] x = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //double[] y = new double[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
      //  double[] x = {-.93, -.82, -.75, .75, .82, .93};
       // double[] y = {1, .5, .3, .5, .1, .2};
        //double[] y = new double[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        //double[] x = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //double[] y = new double[]{5, 11, 16, 18, 23, 31, 40, 49, 56, 66};
        //Values for 2(x^2)
     //   double[] x = new double[]{1, 2, 3, 4, 5};
       // double[] y = new double[]{2, 8, 18, 32, 50};
        //Values for sqrt(x)
      //  double[] x = new double[]{1, 2, 3, 4, 5};
      //  double[] y = new double[]{2, 2*Math.sqrt(2), 2*Math.sqrt(3), 2*2, 2*Math.sqrt(5)};
        Runtime.getRuntime().addShutdownHook(new Thread(Main::terminate));
//        double[] x = new double[]{1, 2, 3, 4, 5};
//        double[] y = new double[]{18.759, 28.5, 32.1111112, 20, .7};
        //double[] x = new double[]{1,2,3,4,5};
        //double[] y = new double[]{1,8,27,64,125};
        double epsilon = Math.pow(10, -10);
        done = false;
        int maxGenerations = 100000;
        Chromosome[] DNA = new Chromosome[DNA_LENGTH];
        //Only use to demonstrate program glitch with x^3
        //DNA[0] = new Chromosome(new Gene[]{new Gene("00101"), new Gene("01100"), new Gene("00011"), new Gene("01100"), new Gene("10101")});
        //System.out.println(DNA[0].getEquation());
        for (int i = 0; i < DNA_LENGTH; i++) {
            Gene[] gene = new Gene[CHROMOSOME_LENGTH];
            for (int j = 0; j < CHROMOSOME_LENGTH; j++) {
                String total = "";
                for (int k = 0; k < 5; k++) {
                    total += String.valueOf((int) (Math.random() + .5));
                }
                gene[j] = new Gene(total);
            }
            DNA[i] = new Chromosome(gene);
        }
        while (true) {
            for (int i = 0; i < DNA_LENGTH; i++) {
                if (DNA[i] != null && DNA[i].getFitness(x, y) < epsilon) {
                    System.out.println("Match Found " + DNA[i].toBinaryString() + " : " + DNA[i].toString() + " : " + DNA[i].getFitness(x, y));
                    System.out.println("Time : " + (System.currentTimeMillis() - startTime) + " milliseconds\n" + "Generations : " + count);
                    done = true;
                    System.exit(1);
                } else if (count >= maxGenerations) {
                    System.out.println("Closest Match Found : " + currentMostElite.toBinaryString() + " : " + currentMostElite.toString() + " : " + currentMostElite.getFitness(x, y));
                    System.out.println("Time : " + (System.currentTimeMillis() - startTime) + " milliseconds\n" + "Generations : " + count);
                    done = true;
                    System.exit(1);
                }
            }
            Chromosome[] elite = new Chromosome[REPRODUCER_LENGTH];
            for (int i = 0; i < REPRODUCER_LENGTH; i++) {
                elite[i] = DNA[0];
                for (int j = 1; j < DNA_LENGTH; j++) {
                    if (DNA[i] == null) continue;
                    if (DNA[j].getFitness(x, y) < elite[i].getFitness(x, y)) {
                        elite[i] = DNA[j];
                    }
                }
            }
            if (currentMostElite != null && !currentMostElite.equals(elite[0]))
                System.out.println("Current most elite:" + elite[0].toBinaryString() + " : " + elite[0].toString() + " : " + elite[0].getFitness(x, y));
            else if (currentMostElite == null) currentMostElite = elite[0];
            if (!currentMostElite.equals(elite[0])) currentMostElite = elite[0];
            Chromosome[] nextGen = new Chromosome[DNA_LENGTH];
            for (int i = 0; i < DNA_LENGTH - REPRODUCER_LENGTH; i++) {
                nextGen[i] = new Daughter(elite[(int) (Math.random() * REPRODUCER_LENGTH)], elite[(int) (Math.random() * REPRODUCER_LENGTH)]).getDaugher();
            }
            for (int i = DNA_LENGTH - REPRODUCER_LENGTH; i < DNA_LENGTH; i++) {
                nextGen[i] = elite[i - (DNA_LENGTH - REPRODUCER_LENGTH)];
            }
            DNA = nextGen;
            count++;
        }
    }

    public static void terminate() {
        if (!done) {
            System.out.println("Closest Match Found : " + currentMostElite.toBinaryString() + " : " + currentMostElite.toString() + " : " + currentMostElite.getFitness());
            System.out.println("Time : " + (System.currentTimeMillis() - startTime) + " milliseconds\n" + "Generations : " + count);
        }
    }

    public static void print(Chromosome[] DNA) {
        for (Chromosome c : DNA) {
            if (c == null) System.out.print(null + ",");
            System.out.print(c + ",");
        }
    }

    public static boolean isPrime(double d) {
        for (int i = 0; i < Math.sqrt(d) + 1; i++) {
            if (d % i == 0) return false;
        }
        return true;
    }

}
