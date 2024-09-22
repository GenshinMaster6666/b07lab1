public class Polynomial{
    private double[] coefficient;
    public Polynomial(){
        coefficient = new double[1];
        coefficient[0] = 0;
    }
    public Polynomial(double[] array){
        coefficient = array;
    }
    public Polynomial add(Polynomial input){
        double[] Poly;
        int length = Math.max(coefficient.length, input.coefficient.length);
        Poly = new double[length];
        for(int i=0; i<coefficient.length; i++){
            Poly[i] += coefficient[i];
        }
        for(int i=0; i<input.coefficient.length; i++){
            Poly[i] += input.coefficient[i];
        }
        return new Polynomial(Poly);
    }
    public double evaluate(double value){
        double result = 0;
        for(int i=0; i<coefficient.length; i++){
            result += coefficient[i]*Math.pow(value,i);
        }
        return result;
    }
    public boolean hasRoot(double value){
        return evaluate(value) == 0;
    }
}