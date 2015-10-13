package servers.misc;

import java.util.GregorianCalendar;

public class Calendar extends GregorianCalendar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4160175957910141482L;

	public Calendar(int year, int month, int date) {
		super(year, month, date);
	}
	/*
	public Calendar() {
		
	}
	*/
	@Override
	public boolean equals(Object obj) {
		Calendar calendar = (Calendar) obj;
		if (get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
			if (get(Calendar.MONTH) == calendar.get(Calendar.MONTH)){
				if (get(Calendar.DATE) == calendar.get(Calendar.DATE)){
					return true;
				}
			}
		}
		return false;
	}
}
