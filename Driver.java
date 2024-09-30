import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        try {
            File file = new File("polynomial.txt");
            Polynomial theFile = new Polynomial(file);
            System.out.println("Polynomial from file:");
            res_poly(theFile);

            double[] coe1 = {1, 1, 4};
            int[] exp1 = {0, 1, 2};
            Polynomial p1 = new Polynomial(coe1, exp1);

            double[] coe2 = {5, 1, 4};
            int[] exp2 = {0, 1, 2};
            Polynomial p2 = new Polynomial(coe2, exp2);

            Polynomial sum = p1.add(p2);
            System.out.println("Sum:");
            res_poly(sum);

            Polynomial product = p1.multiply(p2);
            System.out.println("Product:");
            res_poly(product);

            double value = 2.0;
            System.out.println("Evaluation when x=" + value + ": " + p1.evaluate(value));

            System.out.println("Polynomial has a root at x=0.0? " + p1.hasRoot(0.0));

        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void res_poly(Polynomial input) {
        StringBuilder str = new StringBuilder();
        double[] coefficient = input.getCoefficient();
        int[] exponent = input.getExponent();
        for (int i = 0; i < coefficient.length; i++) {
            if (coefficient[i] == 0) {
                continue;
            }
            if (i > 0 && coefficient[i] > 0) str.append("+");
            str.append((int)coefficient[i]);
            if (exponent[i] > 0) {
                str.append("x");
                if (exponent[i] > 1) {
                    str.append("^").append(exponent[i]);
                }
            }
        }
        System.out.println(str.toString());
    }
    
}
