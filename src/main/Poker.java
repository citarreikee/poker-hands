package main;

public class Poker {
	private String points = "23456789TJQKA";
	public String suit;//��ɫ
	public int point;//�����±꣬�������
	public int nums = 1;
	Poker(String face){
		suit = face.substring(1);
		point = points.indexOf(face.substring(0, 1));
		assert points.contains(face.substring(0, 1)) : "��������ȷ���˿˵���";
		assert "DSHC".contains(suit) : "��������ȷ�Ļ�ɫ";
	}
	public String getPoint() {
		return points.substring(point, point+1);
	}
}
