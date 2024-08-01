public class Voter {
	private Player[] votingPool;
	
	Voter(Player[] v){
		votingPool = v;
	}
	
	public void vote() {
		//put in votes
		for(Player p : votingPool) {
			p.setScore(p.getScore() + (Math.random()*(2.5)));
		}
		
		//sort votes
		Player temp;
		for(int i = votingPool.length-1; i>0; i--) {
			for(int j = 0; j<i; j++) {
				if(votingPool[j].getScore() > votingPool[j+1].getScore()) {
					temp = votingPool[j];
					votingPool[j] = votingPool[j+1];
					votingPool[j+1] = temp;
				}
			}
		}
		
		//assign points
		votingPool[votingPool.length-1].addVoteScore(10);
		//add firstPlaceVote
		votingPool[votingPool.length-1].addFirstVotes(1);
		votingPool[votingPool.length-2].addVoteScore(7);
		votingPool[votingPool.length-3].addVoteScore(5);
		votingPool[votingPool.length-4].addVoteScore(3);
		votingPool[votingPool.length-5].addVoteScore(1);
	}
}