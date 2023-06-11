import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


public class Polynomial {
	public double [] coefficients;
	public int [] exponents;

	
	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
		
	}
	
	public Polynomial(double [] coeff, int [] exp) {
		coefficients = coeff;
		exponents = exp;
	}
	
	public Polynomial(File f) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(f)); 
			String line = file.readLine();
			int c = 0;
			//loop should only run once
			while (line != null) {
				String [] parts = line.split("-|\\+");
				coefficients = new double [parts.length];
				exponents = new int [parts.length];
				
				for (String part:parts) {
					
					String [] moreParts = part.split("x");
					if (moreParts.length > 1) {
						coefficients[c] = Double.parseDouble(moreParts[0]);
						exponents[c] = Integer.parseInt(moreParts[1]);
					}else {
						coefficients[c] = Double.parseDouble(moreParts[0]);
						exponents[c] = 0;
	
					}
					c++;
				

				}
				
				line = file.readLine();
			}
		}catch (FileNotFoundException error) {
		    System.out.println("File not found: " + error.getMessage());
		} catch (IOException error) {
		    System.out.println("Error reading file: " + error.getMessage());
		}
	}
	
	public void saveToFile(String file) {
		try {
			PrintStream ps = new PrintStream(file);
			String output = "";
			
			if(coefficients[0] <0) {
				output += "-"+coefficients[0];
			}else {
				output += coefficients[0];
			}
			if (exponents[0] !=0) {
				output += "x"+exponents[0];
			}
			
			for (int i=1;i<exponents.length;i++) {
				if(coefficients[i] <0) {
					output += "-"+coefficients[i];
				}else {
					output += "+"+coefficients[i];
				}
				output += "x"+exponents[i];
			}
			
			ps.println(output);
			
			
		}catch (FileNotFoundException error) {
		    System.out.println("File not found: " + error.getMessage());
		}
		
	}
	
	
	public Polynomial add(Polynomial poly) {
        int length = exponents.length;
        int length2 = poly.exponents.length;        
                
        int max = 0;
        for (int i=0; i<length;i++) {
        	if (exponents [i] > max) {
        		max = exponents[i];
        	}
        }
        for (int i=0; i<length2;i++) {
        	if (poly.exponents[i] > max) {
        		max = poly.exponents[i];
        	}
        }
     
        max +=1;
        double [] co_result = new double[max];
        int [] exp_result = new int[max];

        for (int i=0; i < length; i++) {
        	exp_result[exponents[i]] = exponents[i];
        	co_result[exponents[i]] += coefficients[i];        	
        }
        
        for (int i=0; i< length2; i++) {
      		exp_result[poly.exponents[i]] = poly.exponents[i];
        	co_result[poly.exponents[i]] += poly.coefficients[i];
        }       
       
        int count = 0;
        for (int i=0; i< max; i++) {
        	if (co_result[i]!=0) {
        		count ++;
        	}
        }
        
        
        double [] co_result2 = new double[count];
        int [] exp_result2 = new int[count];
        int j=0;
        for (int i=0; i< max; i++) {
        	if (co_result[i]!=0) {
        		co_result2[j] = co_result[i];
        		exp_result2[j] = exp_result[i];
        		j++;
        	}
        }
        
        
        return new Polynomial(co_result2,exp_result2);
	}
	
	
	public Polynomial multiply(Polynomial poly) {
        int length = exponents.length;
        int length2 = poly.exponents.length;        
                
        int max = 0;
        int max2 = 0;
        for (int i=0; i<length;i++) {
        	if (exponents[i] > max) {
        		max = exponents[i];
        	}
        }
        for (int i=0; i<length2;i++) {
        	if (poly.exponents[i] > max2) {
        		max2 = poly.exponents[i];
        	}
        }
     
        
        
        max = max + max2 +1;
        double [] co_result = new double[max];
        int [] exp_result = new int[max];

      
        for (int i=0;i<length;i++) {
        	for(int j=0;j<length2;j++) {
        		int ex = exponents[i] + poly.exponents[j];
        		double c = coefficients[i] * poly.coefficients[j];
        		//System.out.println("THIS IS EXPONENT: "+ex);
        		exp_result[ex]=ex;
        		co_result[ex] += c;
        		
        	}
        }
       
        
        
        int count = 0;
        for (int i=0; i< max; i++) {
        	if (co_result[i]!=0) {
        		count ++;
        	}
        }
        
        
        double [] co_result2 = new double[count];
        int [] exp_result2 = new int[count];
        int j=0;
        for (int i=0; i< max; i++) {
        	if (co_result[i]!=0) {
        		co_result2[j] = co_result[i];
        		exp_result2[j] = exp_result[i];
        		j++;
        	}
        }
        
        
        return new Polynomial(co_result2,exp_result2);
	}
	
	
	public void printing() {
		for (int i=0;i<coefficients.length;i++) {
			System.out.println(coefficients[i]+"");
		}
		System.out.println("\n");
		
		for (int i=0;i<exponents.length;i++) {
			System.out.println(exponents[i]+"");
		}
		System.out.println("\n");

	}

	
	
	public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
	}
	
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
    
}
