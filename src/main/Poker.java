package main;

public class Poker {
	private String points = "23456789TJQKA";
	public String suit;//花色
	public int point;//点数下标，方便计算
	public int nums = 1;
	Poker(String face){
		suit = face.substring(1);
		point = points.indexOf(face.substring(0, 1));
		assert points.contains(face.substring(0, 1)) : "请输入正确的扑克点数";
		assert "DSHC".contains(suit) : "请输入正确的花色";
	}
	public String getPoint() {
		return points.substring(point, point+1);
	}
}
