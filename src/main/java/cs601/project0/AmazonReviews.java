package cs601.project0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class AmazonReviews {

	//movies.txt: https://snap.stanford.edu/data/web-Movies.html
	
	public static void main(String[] args) throws IOException {

		String ln = null;
		String check1 = "product/productId";
		String check2 = "review/userId";
		String check3 = "review/score";
		String userId;
		String prodId = null;
		
		String input = null, output = null;

		if("-input".compareTo(args[0]) == 0) {
			input = args[1];
		} else if("-output".compareTo(args[0]) == 0) {
			output = args[1];
		}
		if("-output".compareTo(args[2]) == 0) {
			output = args[3];
		} else if("-input".compareTo(args[2]) == 0) {
			input = args[3];
		}
		if(input == null || output == null) {
			System.out.println("Incorrect input. Please specifiy input and output parameters");
		}
		
		// Read input file
		
		FileInputStream fis = new FileInputStream(input);
		InputStreamReader fisr = new InputStreamReader(fis, "ISO-8859-1");
		
		BufferedReader br = new BufferedReader(fisr);
		FileWriter fw = new FileWriter(output);
		BufferedWriter bw = new BufferedWriter(fw);
		
		// userMap saves userId to review count
		Map<String, Integer> userMap = new HashMap<>();
		
		// productMap saves productId to Product object
		Map<String, ProductList> productMap = new HashMap<>();
		StringTrim trim = new StringTrim();
		
		// Reading all lines and saving the Product details and the User Details using HashMaps
		while ((ln=br.readLine())!=null){ 
			
			//checks if the line contains Product Id or not
			if(ln.contains(check1)) 
			{
				
				//working with Product HashMap: key is the Product Id and value has the product Object
				//checks if productid key already exists, if yes, updates the corresponding object else creates a new object
				prodId = trim.trimString(ln);
				
				
				if(productMap.containsKey(prodId)) 
				{
					productMap.get(prodId).updateCount();
					
					//int value = productMap.get(toProd);
					//productMap.replace(toProd, value+1);
				}else 
				
				{
					ProductList prod1 = new ProductList(prodId);
					
					productMap.put(prodId, prod1);
				}
				
			} 
			
			//checks if the line contains UserId or not
			//working with User HashMap: key is the User Id and value has the No of Reviews given by the user
			//checks if userid key already exists, if yes, updates the corresponding number else creates a new record in the map
			else if(ln.contains(check2)) 
			{
				
				userId = trim.trimString(ln);
				if(userMap.containsKey(userId)) 
				{
					int value = userMap.get(userId);
					userMap.replace(userId, value+1);
				}else 
				
				{
					userMap.put(userId, 1);
				}				
			}
			
			//checks if the line contains Score or not
			//working with Product HashMap: key is the Product Id and value has the product Object
			//checks if productid key already exists, if yes, updates the corresponding object else creates a new object
			else if (ln.contains(check3)) 
			{
				String score = trim.trimString(ln);
				double s= Double.parseDouble(score);
				
				
				productMap.get(prodId).updateScore(s);
				
			}
		}
		
				
		//writes the UserId's with the largest no of reviews into the output file
		
		bw.write("Users with largest number of reviews: " + "\n");
		
		int maxValue = Collections.max(userMap.values());
		
        ArrayList<String> userSort = new ArrayList<String>();
		for(String uId: userMap.keySet()) {
			if(userMap.get(uId) == maxValue) {
                userSort.add(uId);
			}
				
		}
		
        Collections.sort(userSort);
        for(String uId: userSort){
            bw.write("\t" + uId +"\n");
        }
			
        double maxAvgScore = 0.0;
        int maxReviews = 0;
        double sc;
        int count;

		for (String i : productMap.keySet()) {
            sc = productMap.get(i).getAvgScore();
            count = productMap.get(i).getCount();
            
            if (sc > maxAvgScore)
                maxAvgScore = sc;
            if (count > maxReviews)
                maxReviews = count;
			
		}
	
		bw.write("Products with largest number of reviews: " + "\n");
		
		ArrayList<String> scoreProdIds = new ArrayList<String>();
		
		for(String pId: productMap.keySet()) {
			
			if(productMap.get(pId).getCount() == maxReviews) {
				scoreProdIds.add(pId);
			}
			
		}
		
		Collections.sort(scoreProdIds);
		
		for(String pId: scoreProdIds) {
			bw.write("\t" + pId +"\n");
		}
		
		bw.write("Products with the highest average score: " + "\n");
		
		ArrayList<String> scoreSort = new ArrayList<String>();
		for(String pId: productMap.keySet()) {
			if(productMap.get(pId).getAvgScore() == maxAvgScore) {
				scoreSort.add(pId);
			}
			
		}
		
		Collections.sort(scoreSort);
		
        for(String pId : scoreSort) {
			bw.write("\t" + pId +"\n");
		}
		
	
		bw.flush();
		br.close();
		bw.close();
	
	
		
	}

	}	

