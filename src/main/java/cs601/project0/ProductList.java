package cs601.project0;

public class ProductList {
	
	private String prodId;
	private int count;
	private double avgScore;
	
	
	
	public ProductList(String s) {
		
		this.prodId = s;
		this.count = 1;
		this.avgScore = 0.0;
	}
	
	public void updateScore(double as) {
		this.avgScore = (((this.avgScore * (this.count-1)) + as)/ this.count);
		//System.out.println("avg Score is : " + this.avgScore+"\n");
	}
	
	public void updateCount() {
		
		this.count = (this.count + 1);
		
		//System.out.println("Count of the review : " + count+"\n");
	}
	

	public int getCount() {
		return this.count;
	}

	

	public double getAvgScore() {
		return this.avgScore;
	}


}
