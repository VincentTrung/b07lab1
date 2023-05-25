
public class Polynomial {
	public double [] coefficients;
	
	public Polynomial() {
		coefficients = new double[] {0};
	}
	
	public Polynomial(double [] nums) {
		coefficients = nums;
	}
	
	
	public Polynomial add(Polynomial poly) {
        int length = 0;

        if (coefficients.length >= poly.coefficients.length){
        	length = coefficients.length;
        }else if (coefficients.length < poly.coefficients.length){
        	length = poly.coefficients.length;
        }
        
        double [] result = new double[length];
        
        for (int i = 0; i < length; i++) {
        	result[i] = 0;
        }
        for (int i = 0; i < coefficients.length; i++) {
        	result[i] += coefficients[i] ;
        }
        for (int i = 0; i < coefficients.length; i++) {
        	result[i] += poly.coefficients[i] ;
        }
        
        
        return new Polynomial(result);
	}

	public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
	}
	
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
    
}
