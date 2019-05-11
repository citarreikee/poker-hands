package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PokerGame {
	private String[] types = {"high card: ", "pair", "two pairs", "three of a kind", "straight", "flush", "full house", "four of a kind", "None","straight flush"};
	private ArrayList<Poker> black = new ArrayList<Poker>();
	private ArrayList<Poker> white = new ArrayList<Poker>();
	private int[] black_situation = new int[4];
	private int[] white_situation = new int[4];
	private int flag = -1; //黑获胜0，白获胜1，平局-1
	private String high_card = "Ace";
	private String type = "high card: ";
	private int black_level = 1;
	private int white_level = 1;
	public PokerGame(String _black, String _white){
		String[] tmp_b = _black.split(" ");
		String[] tmp_w = _white.split(" ");
		for(int i = 0; i < tmp_b.length; i++) {
			black.add(new Poker(tmp_b[i]));
			white.add(new Poker(tmp_w[i]));
		}
		sortByPoint();
		setNums();
		sortByNums();
		compare();
	}
	public static void main(String[] args) {		
		String black = "2H 3D 5S 9C KD";
		String white = "2C 3H 4S 8C AH";
		PokerGame pg = new PokerGame(black, white);
		System.out.println(pg.getResult());	
	}
	private void compare() {
		black_level = getLevel(black, black_situation);
		white_level = getLevel(white, white_situation);
		if(black_level > white_level)
			flag = 0;
		if(white_level > black_level)
			flag = 1;
		if(white_level == black_level)
			getHighCard();	
	}
	public String getResult() {
		String r = "";
		switch(flag) {
			case 0 : {
				covertToType(black_level);
				r += "Black wins - " + type;
				break;
			}
			case 1 : {
				covertToType(white_level);
				r += "White wins - " + type;
				break;
			}
			default : return "Tie";
			}
		return r;		
	}	
	private void covertToType(int level) {
		type = types[level-1];
		if (level == 1)
			type += high_card;
	}	
	private int getLevel(List<Poker> p, int[] situation) {
		int level=1;
		if(situation[3] == 1)
			return 8;
		else if(situation[2] == 1) {
			if(situation[1] == 1)
				return 7;
			else
				return 4;
		}
		else if(situation[1] == 2)
			return 3;
		else if(situation[1] == 1)
			return 2;
		if(isStraight(p))
			level += 4; //5
		if(isFlush(p))	
			level += 5; //6
		return level;	
	}
	private void getHighCard() {
		for(int i = 0; i < black.size(); i++) {
			if(black.get(i).point > white.get(i).point) {				
				flag = 0;
				high_card = black.get(i).getPoint();
				if(high_card.equals("A"))
					high_card = "Ace";
				return;
			}
			if(white.get(i).point > black.get(i).point) {			
				flag = 1;
				high_card = white.get(i).getPoint(); 
				if(high_card.equals("A"))
					high_card = "Ace";
				return;
			}
		}
	}
	private Boolean isStraight(List<Poker> p) {
		for(int i = 0; i < p.size()-2; i++)
			if(p.get(i).point - p.get(i+1).point != 1)
				return false;
		return true;
	}	
	private Boolean isFlush(List<Poker> p) {
		for(int i = 0; i < p.size()-2; i++)
			if(!p.get(i).suit.equals(p.get(i+1).suit))
				return false;
		return true;
	}	
	private void setNums() {
		int counts1 = 0;
		int counts2 = 0;
		for(int index = 0; index < black.size(); index++) {
			int tmp1 = 100;
			int tmp2 = 100;
			if(index < black.size() - 1) {
				tmp1 = black.get(index+1).point;
				tmp2 = white.get(index+1).point;
			}
			if(black.get(index).point == tmp1)
				counts1++;
			else {
				for(int j = 0; j < counts1; j++)
					black.get(index-j).nums += counts1;
				black_situation[counts1] ++;
				counts1 = 0;
			}
			if(white.get(index).point == tmp2)
				counts2++;
			else {
				for(int j = 0; j < counts2; j++)
					white.get(index-j).nums += counts2;
				white_situation[counts2] ++;
				counts2 = 0;
			}
		}
			
	}	
	private void sortByPoint() {
		Comparator comparator = new Comparator<Poker>() {
			@Override
			public int compare(Poker p1, Poker p2) {
				// TODO Auto-generated method stub
				return Integer.valueOf(p2.point).compareTo(Integer.valueOf(p1.point));
			}
		};
		Collections.sort(black, comparator);
		Collections.sort(white, comparator);
	}
	private void sortByNums() {
		Comparator comparator = new Comparator<Poker>() {
			@Override
			public int compare(Poker p1, Poker p2) {
				// TODO Auto-generated method stub
				return Integer.valueOf(p2.nums).compareTo(Integer.valueOf(p1.nums));
			}
		};
		Collections.sort(black, comparator);
		Collections.sort(white, comparator);
	}
}