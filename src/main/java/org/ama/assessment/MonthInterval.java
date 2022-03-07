package org.ama.assessment;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class MonthInterval {
	public static String[] generateMonthsInInterval(LocalDate startDate, LocalDate endDate) {
        String[] monthNames = null;

        // ### TODO: add month names generation logic here ###
		int numYears = endDate.getYear() - startDate.getYear();
		int totalNumMonths;
		// If numYears > 1, then there will always be 12+ months
		if (numYears > 1) {
			totalNumMonths = 12;
			monthNames = new String[totalNumMonths];
			for (int i = 1; i <= 12; i++) {
				String monthName = Month.of(i).toString().toLowerCase();
				monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
				monthNames[i-1] = monthName;
			}
		}
		// If numYears == 1, we have to see if there is overlap in the months
		if (numYears == 1) {
			int numMonthsStartYear = 12 - startDate.getMonthValue() + 1;
			int numMonthsEndYear = endDate.getMonthValue();
			totalNumMonths = numMonthsStartYear + numMonthsEndYear;
			// If totalNumMonths >= 12, do the 12 month logic again
			if (totalNumMonths >= 12) {
				monthNames = new String[12];
				for (int i = 1; i <= 12; i++) {
					String monthName = Month.of(i).toString().toLowerCase();
					monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
					monthNames[i-1] = monthName;
				}
			} else { // Iterate through endDate's months first to get the desired chronological order
				monthNames = new String[totalNumMonths];
				int monthNamesIndex = 0;
				for (int i = 1; i <= endDate.getMonthValue(); i++) {
					String monthName = Month.of(i).toString().toLowerCase();
					monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
					monthNames[monthNamesIndex] = monthName;
					monthNamesIndex++;
				}
				for (int i = startDate.getMonthValue(); i <= 12; i++) {
					String monthName = Month.of(i).toString().toLowerCase();
					monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
					monthNames[monthNamesIndex] = monthName;
					monthNamesIndex++;
				}
			}
		}
		// If numYears == 0, simply iterate through the month values
		if (numYears == 0) {
			totalNumMonths = endDate.getMonthValue() - startDate.getMonthValue() + 1;
			monthNames = new String[totalNumMonths];
			int monthNamesIndex = 0;
			for (int i = startDate.getMonthValue(); i <= endDate.getMonthValue(); i++) {
				String monthName = Month.of(i).toString().toLowerCase();
				monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
				monthNames[monthNamesIndex] = monthName;
				monthNamesIndex++;
			}
		}
        // ##########################################

		return monthNames;
	 }

	 public static void main(String[] args) {
		String[] months = generateMonthsInInterval(LocalDate.parse("2017-10-01"), LocalDate.parse("2019-03-01"));
		for (String month: months) {
			System.out.println(month);
		}
	 }
}
