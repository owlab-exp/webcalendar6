package edu.cmu.tsp6.client.bo;

public enum MonthEnum {
	January(1), February(2), March(3), April(4), May(5), June(6), July(7), August(
			8), September(9), October(10), November(11), December(12);

	private final int month;

	MonthEnum(int month) {
		this.month = month;
	}
	
	public int getMonthNumber() {
		return month;
	}
}
