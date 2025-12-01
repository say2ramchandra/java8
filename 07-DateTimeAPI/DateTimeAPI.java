package datetime;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;

/**
 * DATE AND TIME API (java.time) - Java 8
 * =======================================
 * 
 * KEY CLASSES:
 * ------------
 * - LocalDate: Date without time (2024-01-15)
 * - LocalTime: Time without date (14:30:00)
 * - LocalDateTime: Date and time without timezone
 * - ZonedDateTime: Date and time with timezone
 * - Instant: Point in time (timestamp)
 * - Duration: Time-based amount (hours, minutes, seconds)
 * - Period: Date-based amount (years, months, days)
 * - DateTimeFormatter: Formatting and parsing
 * 
 * ADVANTAGES OVER OLD API:
 * ------------------------
 * - Immutable and thread-safe
 * - Clear and fluent API
 * - Better timezone handling
 * - Separate classes for different use cases
 * 
 * @author Java 8 Mastery Course
 */
public class DateTimeAPI {

    public static void main(String[] args) {
        System.out.println("=== JAVA 8 DATE AND TIME API ===\n");
        
        demonstrateLocalDate();
        demonstrateLocalTime();
        demonstrateLocalDateTime();
        demonstrateDurationAndPeriod();
        demonstrateFormatting();
        realWorldScenario_EventScheduling();
    }

    private static void demonstrateLocalDate() {
        System.out.println("1. LOCAL DATE:");
        System.out.println("-".repeat(60));
        
        // Current date
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);
        
        // Specific date
        LocalDate specificDate = LocalDate.of(2024, 12, 25);
        System.out.println("Christmas 2024: " + specificDate);
        
        // Parse from string
        LocalDate parsed = LocalDate.parse("2024-01-15");
        System.out.println("Parsed: " + parsed);
        
        // Date arithmetic
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);
        System.out.println("Tomorrow: " + tomorrow);
        System.out.println("Next week: " + nextWeek);
        
        // Extracting components
        System.out.println("Year: " + today.getYear());
        System.out.println("Month: " + today.getMonth());
        System.out.println("Day: " + today.getDayOfMonth());
        System.out.println("Day of week: " + today.getDayOfWeek());
        
        // Comparisons
        System.out.println("Is today before Christmas? " + today.isBefore(specificDate));
        System.out.println("Is today after Christmas? " + today.isAfter(specificDate));
        
        System.out.println();
    }

    private static void demonstrateLocalTime() {
        System.out.println("2. LOCAL TIME:");
        System.out.println("-".repeat(60));
        
        // Current time
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now);
        
        // Specific time
        LocalTime lunchTime = LocalTime.of(12, 30);
        System.out.println("Lunch time: " + lunchTime);
        
        LocalTime precise = LocalTime.of(14, 30, 45, 500);
        System.out.println("Precise time: " + precise);
        
        // Time arithmetic
        LocalTime later = now.plusHours(2).plusMinutes(30);
        System.out.println("2.5 hours later: " + later);
        
        // Extracting components
        System.out.println("Hour: " + now.getHour());
        System.out.println("Minute: " + now.getMinute());
        
        System.out.println();
    }

    private static void demonstrateLocalDateTime() {
        System.out.println("3. LOCAL DATE TIME:");
        System.out.println("-".repeat(60));
        
        // Current date-time
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Now: " + now);
        
        // Specific date-time
        LocalDateTime meeting = LocalDateTime.of(2024, 12, 25, 10, 30);
        System.out.println("Meeting: " + meeting);
        
        // Combining date and time
        LocalDate date = LocalDate.of(2024, 6, 15);
        LocalTime time = LocalTime.of(14, 0);
        LocalDateTime combined = LocalDateTime.of(date, time);
        System.out.println("Combined: " + combined);
        
        // With timezone
        ZonedDateTime zoned = ZonedDateTime.now();
        System.out.println("With timezone: " + zoned);
        
        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Tokyo time: " + tokyo);
        
        System.out.println();
    }

    private static void demonstrateDurationAndPeriod() {
        System.out.println("4. DURATION AND PERIOD:");
        System.out.println("-".repeat(60));
        
        // Duration - time-based
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 30);
        Duration workDay = Duration.between(start, end);
        System.out.println("Work day duration: " + workDay.toHours() + " hours " + 
                          workDay.toMinutesPart() + " minutes");
        
        Duration twoHours = Duration.ofHours(2);
        System.out.println("2 hours in minutes: " + twoHours.toMinutes());
        
        // Period - date-based
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        System.out.println("Age: " + age.getYears() + " years " + 
                          age.getMonths() + " months " + 
                          age.getDays() + " days");
        
        Period threeMonths = Period.ofMonths(3);
        LocalDate future = today.plus(threeMonths);
        System.out.println("3 months from now: " + future);
        
        System.out.println();
    }

    private static void demonstrateFormatting() {
        System.out.println("5. FORMATTING AND PARSING:");
        System.out.println("-".repeat(60));
        
        LocalDateTime now = LocalDateTime.now();
        
        // Predefined formatters
        System.out.println("ISO_DATE_TIME: " + now.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("ISO_LOCAL_DATE: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        
        // Custom formatters
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Custom 1: " + now.format(formatter1));
        
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        System.out.println("Custom 2: " + now.format(formatter2));
        
        // Parsing
        String dateStr = "15-01-2024 14:30";
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsed = LocalDateTime.parse(dateStr, parser);
        System.out.println("Parsed: " + parsed);
        
        System.out.println();
    }

    private static void realWorldScenario_EventScheduling() {
        System.out.println("REAL-WORLD: Event Scheduling System");
        System.out.println("-".repeat(60));
        
        // Create event
        LocalDateTime eventStart = LocalDateTime.of(2024, 12, 25, 19, 0);
        LocalDateTime eventEnd = eventStart.plusHours(3);
        
        System.out.println("\nEvent: Christmas Party");
        System.out.println("Start: " + eventStart.format(
                DateTimeFormatter.ofPattern("EEEE, MMMM dd 'at' HH:mm")));
        System.out.println("End: " + eventEnd.format(
                DateTimeFormatter.ofPattern("HH:mm")));
        
        // Duration
        Duration duration = Duration.between(eventStart, eventEnd);
        System.out.println("Duration: " + duration.toHours() + " hours");
        
        // Days until event
        LocalDate today = LocalDate.now();
        Period until = Period.between(today, eventStart.toLocalDate());
        System.out.println("Days until event: " + until.getDays());
        
        // Check if event is in the past
        boolean isPast = eventStart.isBefore(LocalDateTime.now());
        System.out.println("Event passed: " + isPast);
        
        // Recurring event - every week
        List<LocalDateTime> occurrences = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            occurrences.add(eventStart.plusWeeks(i));
        }
        System.out.println("\nNext 4 occurrences:");
        occurrences.forEach(occ -> System.out.println("  " + occ.format(
                DateTimeFormatter.ofPattern("MMM dd, yyyy"))));
        
        System.out.println();
    }
}
