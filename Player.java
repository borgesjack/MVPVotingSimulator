public class Player {
	private String name;
	private double score;
	private int voteScore;
	private int firstVotes;
	
	public Player() {
		name = "";
		score = 0;
		voteScore = 0;
		firstVotes = 0;
	}
	
	public Player(String n, double ppg, double per, double tsp, double twp, double ts, double gp, double cmvp) {
		name = n;
		score = ppg*.5 + per + tsp*10 + (gp/82)*10 + twp*13 - ts*1.5 - cmvp*2;
		voteScore = 0;
		firstVotes = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double s) {
		score = s;
	}
	
	public int getFirstVotes() {
		return firstVotes;
	}
	
	public void addFirstVotes(int v) {
		firstVotes += v;
	}
	
	public int getVoteScore() {
		return voteScore;
	}
	
	public void addVoteScore(int v) {
		voteScore += v;
	}
	
	public String toString() {
		return name + ": " + voteScore; 
	}
}