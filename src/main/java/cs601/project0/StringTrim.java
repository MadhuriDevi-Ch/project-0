package cs601.project0;

public class StringTrim {

	public StringTrim() {
		
	}
	
	public String trimString(String s){
		String tmpword = s;
		int j= tmpword.indexOf(":");
		j=j+2;
		int c = tmpword.length();
		String word = tmpword.substring(j,c);
		//System.out.println(word);
		return word;
		
	}
	
}
