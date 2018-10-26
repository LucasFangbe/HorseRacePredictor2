/**
 * 
 */
package com.folk.winner.dc.helper;

/**
 * @author fangbe
 *
 */
public class RacingHorsePerformance {
	
	private int dateGap;
	private int raceCount;
	private int placedCount;

	/**
	 * 
	 */
	private RacingHorsePerformance(RacingHorsePerformanceBuilder builder) {
		this.dateGap = builder.dateGap;
		this.raceCount = builder.raceCount;
		this.placedCount = builder.placedCount;
	}
	
	/**
	 * 
	 * @return
	 */
	public double get(){
		double result = (((double)placedCount / raceCount) * Math.log1p(raceCount)) / (dateGap + 1);
		return result;
	}
	
	/**
	 * @return the dateGap
	 */
	public int getDateGap() {
		return dateGap;
	}

	/**
	 * @return the raceCount
	 */
	public int getRaceCount() {
		return raceCount;
	}

	/**
	 * @return the placedCount
	 */
	public int getPlacedCount() {
		return placedCount;
	}

	/**
	 * 
	 * @author fangbe
	 *
	 */
	public static class RacingHorsePerformanceBuilder {
		private int dateGap;
		private int raceCount;
		private int placedCount;
		
		/**
		 * 
		 * @return
		 */
		public RacingHorsePerformance build(){
			return new RacingHorsePerformance(this);
		}
		
		/**
		 * @param dateGap the dateGap to set
		 */
		public RacingHorsePerformanceBuilder setDateGap(int dateGap) {
			this.dateGap = dateGap;
			return this;
		}
		
		/**
		 * @param raceCount the raceCount to set
		 */
		public RacingHorsePerformanceBuilder setRaceCount(int raceCount) {
			this.raceCount = raceCount;
			return this;
		}
		
		/**
		 * @param placedCount the placedCount to set
		 */
		public RacingHorsePerformanceBuilder setPlacedCount(int placedCount) {
			this.placedCount = placedCount;
			return this;
		}
		
	}
	

}
