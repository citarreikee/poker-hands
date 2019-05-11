package gameTest;

import org.junit.Assert;
import org.junit.Test;
import main.PokerGame;

public class Testcase {
	@Test
	public void test_high_card() {
		PokerGame pg = new PokerGame("2H 3D 5S 9C KD", "2C 3H 4S 8C AH");
		Assert.assertEquals("White wins - high card: Ace", pg.getResult());
	}
	@Test
	public void test_pair() {
		PokerGame pg = new PokerGame("2H 3D 9S 9C KD", "2C 4H 4S 8C AH");
		Assert.assertEquals("Black wins - pair", pg.getResult());
	}
	@Test
	public void test_two_pairs() {
		PokerGame pg = new PokerGame("KH 3D 9S 9C KD", "2C 4H 4S 8C 8H");
		Assert.assertEquals("Black wins - two pairs", pg.getResult());
	}
	@Test
	public void test_three_of_a_kind() {
		PokerGame pg = new PokerGame("7H 3D 9S 9C KD", "2C 4H 4S 4C AH");
		Assert.assertEquals("White wins - three of a kind", pg.getResult());
	}
	@Test
	public void test_straight() {
		PokerGame pg = new PokerGame("7H 6D 4S 5C 3D", "2C 4H 4S 4C AH");
		Assert.assertEquals("Black wins - straight", pg.getResult());
	}
	@Test
	public void test_flush() {
		PokerGame pg = new PokerGame("7D 3D 4D 9D KD", "2C 4H 4S 4C AH");
		Assert.assertEquals("Black wins - flush", pg.getResult());
	}
	@Test
	public void test_full_house() {
		PokerGame pg = new PokerGame("2H 4S 4C 2D 4H", "2S 8S AS QS 3S");
		Assert.assertEquals("Black wins - full house", pg.getResult());
	}
	@Test
	public void test_four_of_a_kind() {
		PokerGame pg = new PokerGame("7D 3D 5D 9D KD", "2C 4H 4S 4C 4D");
		Assert.assertEquals("White wins - four of a kind", pg.getResult());
	}
	@Test
	public void test_straight_flush() {
		PokerGame pg = new PokerGame("7H 6H 4H 5H 3H", "2C 9H 9S 9C 9H");
		Assert.assertEquals("Black wins - straight flush", pg.getResult());
	}
	@Test
	public void test_tie() {
		PokerGame pg = new PokerGame("7H 6H 4H 5H 3H", "7D 6D 4D 5D 3D");
		Assert.assertEquals("Tie", pg.getResult());
	}
}
