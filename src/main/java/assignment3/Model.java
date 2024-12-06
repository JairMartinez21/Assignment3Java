package assignment3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Model {

    public String calculateDaysUntilBirthday(String birthday) {
        // Validate the date input
        String validationMessage = validateDate(birthday);
        if (!validationMessage.equals("Valid")) {
            return validationMessage; // Return the validation error message
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthdayDate = LocalDate.parse(birthday, formatter);
        LocalDate today = LocalDate.now();

        // Adjust year for the next occurrence of the birthday
        LocalDate nextBirthday = birthdayDate.withYear(today.getYear());
        if (nextBirthday.isEqual(today)) {
            return "Your birthday is today! 🎉🎂🎈 Happy Birthday!";
        }

        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(today, nextBirthday);
        return "Days until your next birthday: " + days;
    }

    private String validateDate(String birthday) {
        // Split the date into parts to validate manually
        String[] parts = birthday.split("/");
        if (parts.length != 3) {
            return "Invalid date format. Use MM/DD/YYYY.";
        }

        try {
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Validate month
            if (month < 1 || month > 12) {
                return "Invalid month. Please enter a valid month (MM) between 01 and 12.\n Use MM/DD/YYYY.";
            }

            // Validate day for the given month
            if (!isValidDayForMonth(month, day, year)) {
                return "Invalid day for the given month. Please check the day entered.\n Use MM/DD/YYYY.";
            }

            return "Valid"; // All checks passed
        } catch (NumberFormatException e) {
            return "Invalid date format. Use MM/DD/YYYY.";
        }
    }

    private boolean isValidDayForMonth(int month, int day, int year) {
        switch (month) {
            case 2: // February
                if (isLeapYear(year)) {
                    return day >= 1 && day <= 29; // Leap year
                } else {
                    return day >= 1 && day <= 28; // Non-leap year
                }
            case 4: // April
            case 6: // June
            case 9: // September
            case 11: // November
                return day >= 1 && day <= 30; // Months with 30 days
            default: // All other months have 31 days
                return day >= 1 && day <= 31;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
