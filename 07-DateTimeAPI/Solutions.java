package datetime;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class Solutions {
    public static void main(String[] args) {
        System.out.println("=== DATE AND TIME API SOLUTIONS ===\n");
        
        // 1. Get current date, time, datetime
        System.out.println("Current date: " + LocalDate.now());
        System.out.println("Current time: " + LocalTime.now());
        System.out.println("Current datetime: " + LocalDateTime.now());
        
        // 2. Specific date (birthday)
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        System.out.println("\nBirthday: " + birthday);
        
        // 3. Add/subtract
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        System.out.println("Tomorrow: " + tomorrow);
        System.out.println("Last month: " + lastMonth);
        
        // 4. Extract components
        LocalDate today = LocalDate.now();
        System.out.println("\nYear: " + today.getYear());
        System.out.println("Month: " + today.getMonthValue());
        System.out.println("Day: " + today.getDayOfMonth());
        
        // 5. Compare dates
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 12, 31);
        System.out.println("\ndate1 before date2: " + date1.isBefore(date2));
        
        // 6. Calculate age
        Period age = Period.between(birthday, LocalDate.now());
        System.out.println("\nAge: " + age.getYears() + " years " + 
                          age.getMonths() + " months");
        
        // 7. Days between dates
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("Days between: " + daysBetween);
        
        // 8. Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        System.out.println("\nFormatted: " + today.format(formatter));
        
        // 9. Parse string
        String dateStr = "2024-12-25";
        LocalDate parsed = LocalDate.parse(dateStr);
        System.out.println("Parsed: " + parsed);
        
        // 10. Timezones
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("\nNY: " + nyTime);
        System.out.println("Tokyo: " + tokyoTime);
    }
}
