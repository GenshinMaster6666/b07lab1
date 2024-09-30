import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial{
    double[] coefficient;
    int[] exponent;

    public Polynomial(){
        coefficient = new double[1];
        exponent = new int[1];
    }
    public Polynomial(double[] in_coe, int[] in_exp){
        coefficient = in_coe;
        exponent = in_exp;
    }

    public double[] getCoefficient() {
        return coefficient;
    }

    public int[] getExponent() {
        return exponent;
    }

    public Polynomial add(Polynomial input) {
        int length = Math.max(exponent[exponent.length - 1], input.exponent[input.exponent.length - 1]);
        double[] new_coe = new double[length + 1];
        int n = 0;
        while (n < coefficient.length) {
            new_coe[exponent[n]] += coefficient[n];
            n++;
        }
        int m = 0;
        while (m < input.coefficient.length) {
            new_coe[input.exponent[m]] += input.coefficient[m];
            m++;
        }        
        int count = 0;
        for (int i = 0; i < new_coe.length; i++) {
            if (new_coe[i] != 0) {
                count++;
            }
        }
        double[] new_new_coe = new double[count];
        int[] new_new_exp = new int[count];
        int k = 0;
        for (int i = 0; i < new_coe.length; i++) {
            if (new_coe[i] != 0) {
                new_new_coe[k] = new_coe[i];
                new_new_exp[k] = i;
                k++;
            }
        }
        return new Polynomial(new_new_coe, new_new_exp);
    }
    

    public double evaluate(double value){
        double result = 0.0;
        for(int i=0; i<coefficient.length; i++){
            result += coefficient[i]*Math.pow(value,exponent[i]);
        }
        return result;
    }
    public boolean hasRoot(double value){
        return evaluate(value) == 0.0;
    }


    public Polynomial multiply(Polynomial input) {
        int new_length = exponent[exponent.length - 1] + input.exponent[input.exponent.length - 1];
        double[] new_coe = new double[new_length + 1];
        for (int i = 0; i < coefficient.length; i++) {
            for (int j = 0; j < input.coefficient.length; j++) {
                new_coe[exponent[i] + input.exponent[j]] += coefficient[i] * input.coefficient[j];
            }
        }
        int count = 0;
        for (int i = 0; i < new_coe.length; i++) {
            if (new_coe[i] != 0) {
                count++;
            }
        }        
        double[] final_coeffs = new double[count];
        int[] final_exps = new int[count];
        int k = 0;
        for (int i = 0; i < new_coe.length; i++) {
            if (new_coe[i] != 0) {
                final_coeffs[k] = new_coe[i];
                final_exps[k] = i;
                k++;
            }
        }
        return new Polynomial(final_coeffs, final_exps);
    }

    public Polynomial(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        line = line.replace("-", "+-");
        String[] terms = line.split("\\+");
    
        coefficient = new double[terms.length];
        exponent = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String[] ceo_exp = terms[i].split("x");
            coefficient[i] = Double.parseDouble(ceo_exp[0]);
            if (ceo_exp.length == 1) {
                exponent[i] = 0;
            } else {
                exponent[i] = Integer.parseInt(ceo_exp[1]);
            }
        }
    }
    
    public void saveToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < coefficient.length; i++) {
                if (i > 0 && coefficient[i] > 0) {
                    str.append("+");
                }
                str.append(coefficient[i]);
                if (exponent[i] == 1) {
                    str.append("x");
                } else if (exponent[i] > 1) {
                    str.append("x^").append(exponent[i]);
                }
            }
            writer.write(str.toString());
        }
    }
}