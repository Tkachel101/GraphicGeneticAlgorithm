
public class Daughter {
    private Chromosome parent1;
    private Chromosome parent2;
    public static final double MUTATION_RATE = Main.MUTATION_RATE;

    public Daughter(Chromosome parent1, Chromosome parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
    }

    public Chromosome getDaugher() {
        if (Math.random() < MUTATION_RATE) {
            //System.out.println("MUTATION");
            Gene[] gene = new Gene[Main.CHROMOSOME_LENGTH];
            for (int j = 0; j < Main.CHROMOSOME_LENGTH; j++) {
                String total = "";
                for (int k = 0; k < 5; k++) {
                    total += String.valueOf((int) (Math.random() + .5));
                }
                gene[j] = new Gene(total);
            }
            return new Chromosome(gene);
        }
        int split = (int) (Math.random() * Main.CHROMOSOME_LENGTH * 5);
        String s = "";
        for (int i = 0; i < Main.CHROMOSOME_LENGTH * 5; i++) {
            if (i < split) {
                s += parent1.toBinaryString().charAt(i);
            } else
                s += parent2.toBinaryString().charAt(i);
        }
        Gene[] g = new Gene[Main.CHROMOSOME_LENGTH];
        for (int i = 0; i < Main.CHROMOSOME_LENGTH; i++) {
            String ss = "";
            for (int j = i * 5; j < i * 5 + 5; j++) {
                ss += String.valueOf(s.charAt(j));
            }
            g[i] = new Gene(ss);
        }
        return new Chromosome(g);
    }
}
