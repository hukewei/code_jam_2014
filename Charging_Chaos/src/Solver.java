import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class Solver {
	
	public static void main(String[] args) {
		Solver solver = new Solver();
		solver.readFromFile("src/A-large.in");
	}

	private int nb_case = 0;
	private int N = 0;
	private int L = 0;
	private ArrayList<String> current;
	private ArrayList<String> object;
	private int nb_demande = 0;
	
	String shift_string(String in, ArrayList<Integer> pos) {
		StringBuilder res = new StringBuilder(in);
		for (int i = 0; i < pos.size(); i++) {
			if (in.charAt(pos.get(i)) == '0') {
				res.setCharAt(pos.get(i), '1');
			} else {
				res.setCharAt(pos.get(i), '0');
			}
		}
		return res.toString();
	}
	
	private void solveACase() {
		nb_demande = 0;
		boolean stop = false;
		ArrayList<String> origin = (ArrayList<String>) current.clone();
		int max = (int) Math.pow(2, L);
		ZuHe robot = new ZuHe();		
		int[ ] num=new int[L];
		for (int i = 0; i < num.length; i++) {
			num[i] = i;
		}
		while (!stop) {
			{
				if (object.containsAll(current)) {
					System.out.println(nb_demande);
					return;
				} else {
					for (int j = 0; j < L; j++) {
						ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>(); 
						robot.combine(num,j+1, resultList);
						System.out.println(j+1);
						for (int i = 0; i < resultList.size(); i++) {
							//System.out.println(resultList.get(i));
							for (int j1 = 0; j1 < current.size(); j1++) {
								current.set(j1, shift_string(current.get(j1), resultList.get(i)));
							}
							if (object.containsAll(current)) {
								System.out.println(j+1);
								stop = true;
								return;
							}
							current = (ArrayList<String>) origin.clone();
						}
					}
					System.out.println("NOT POSSIBLE");
					return;
				}
			}
		}
	}
	
	
	public void readFromFile(String f) {
		BufferedReader br=null;
		String chaine = null;
		
	    try {
	    	try {
	    		br = new BufferedReader(new FileReader(f));
	    		nb_case = Integer.parseInt(br.readLine());
	    		if (nb_case > 0) {
	    			for (int i = 1; i <= nb_case; i++) {
	    				String two_case[] = new String[2];
	    				chaine = br.readLine();
	    				two_case = chaine.split(" ");
	    				System.out.print("Case #"+ i + ": ");
						N = Integer.parseInt(two_case[0]);
						L = Integer.parseInt(two_case[1]);
						current = new ArrayList<String>();
						chaine = br.readLine();
						current.addAll(Arrays.asList(chaine.split(" ")));
						object = new ArrayList<String>();
						chaine = br.readLine();
						object.addAll(Arrays.asList(chaine.split(" ")));
	    				solveACase();
					}
	    		}
       		} catch(EOFException e) {
       			br.close();
       		}
	    } catch(FileNotFoundException e) {
	    	System.out.println("fichier inconnu : " + f);
	    } catch(IOException e) {
	    	System.out.println("IO Exception");
	    }
	}

}