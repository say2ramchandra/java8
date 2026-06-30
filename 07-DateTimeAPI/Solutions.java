package datetime;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO DATE AND TIME API EXERCISES
 * =========================================
 * This file contains comprehensive solutions for the new Java 8 Date Time API.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== DATE AND TIME API - EXERCISE SOLUTIONS ===\n");
        
        // Run all exercise solutions
        exercise1_CurrentDateTime();
        exercise2_SpecificDate();
        exercise3_DateArithmetic();
        exercise4_ExtractComponents();
        exercise5_CompareDates();
        exercise6_CalculateAge();
        exercise7_DaysBetween();
        exercise8_FormatDates();
        exercise9_ParseDates();
        exercise10_Timezones();
        exercise11_EventScheduling();
        exercise12_BusinessDays();
        exercise13_RecurringEvents();
        exercise14_TimezoneConversion();
        exercise15_AgeCalculatorPrecise();
    }

    // ==================== BASIC LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 1: Get current date, time, and datetime
     * Solution demonstrates basic date/time creation
     */
    private static void exercise1_CurrentDateTime() {
        System.out.println("EXERCISE 1: Get Current Date/Time");
        System.out.println("-".repeat(60));
        
        // Current date (no time)
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);
        
        // Current time (no date)
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Time: " + currentTime);
        
        // Current date and time (no timezone)
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentDateTime);
        
        // Current instant (timestamp)
        Instant currentInstant = Instant.now();
        System.out.println("Current Instant: " + currentInstant);
        
        // Current date/time with timezone
        ZonedDateTime currentZoned = ZonedDateTime.now();
        System.out.println("Current Zoned: " + currentZoned);
        
        System.out.println();
    }

    /**
     * EXERCISE 2: Create specific date (birthday)
     * Solution demonstrates creating specific dates
     */
    private static void exercise2_SpecificDate() {
        System.out.println("EXERCISE 2: Create Specific Date");
        System.out.println("-".repeat(60));
        
        // Create birth date
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        System.out.println("Birthday: " + birthday);
        
        // Alternative: Using Month enum
        LocalDate birthdayEnum = LocalDate.of(1990, Month.MAY, 15);
        System.out.println("Birthday (using Month): " + birthdayEnum);
        
        // Create specific time
        LocalTime meetingTime = LocalTime.of(14, 30); // 2:30 PM
        System.out.println("Meeting Time: " + meetingTime);
        
        // Create specific date-time
        LocalDateTime appointment = LocalDateTime.of(2024, 12, 25, 10, 0);
        System.out.println("Appointment: " + appointment);
        
        // Alternative: Combine date and time
        LocalDate date = LocalDate.of(2024, 6, 15);
        LocalTime time = LocalTime.of(9, 0);
        LocalDateTime combined = LocalDateTime.of(date, time);
        System.out.println("Combined: " + combined);
        
        System.out.println();
    }

    /**
     * EXERCISE 3: Add/subtract days, months, years
     * Solution demonstrates date arithmetic
     */
    private static void exercise3_DateArithmetic() {
        System.out.println("EXERCISE 3: Date Arithmetic");
        System.out.println("-".repeat(60));
        
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);
        
        // Adding time periods
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);
        
        System.out.println("Tomorrow: " + tomorrow);
        System.out.println("Next Week: " + nextWeek);
        System.out.println("Next Month: " + nextMonth);
        System.out.println("Next Year: " + nextYear);
        
        // Subtracting time periods
        LocalDate yesterday = today.minusDays(1);
        LocalDate lastWeek = today.minusWeeks(1);
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate lastYear = today.minusYears(1);
        
        System.out.println("\nYesterday: " + yesterday);
        System.out.println("Last Week: " + lastWeek);
        System.out.println("Last Month: " + lastMonth);
        System.out.println("Last Year: " + lastYear);
        
        // Chaining operations
        LocalDate futureDate = today.plusMonths(6).plusDays(15);
        System.out.println("\n6 months and 15 days from now: " + futureDate);
        
        System.out.println();
    }

    /**
     * EXERCISE 4: Extract year, month, day from date
     * Solution demonstrates component extraction
     */
    private static void exercise4_ExtractComponents() {
        System.out.println("EXERCISE 4: Extract Components");
        System.out.println("-".repeat(60));
        
        LocalDate date = LocalDate.of(2024, 12, 25);
        LocalTime time = LocalTime.of(14, 30, 45);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        
        // Extract from LocalDate
        System.out.println("Date: " + date);
        System.out.println("  Year: " + date.getYear());
        System.out.println("  Month: " + date.getMonth());
        System.out.println("  Month Value: " + date.getMonthValue());
        System.out.println("  Day of Month: " + date.getDayOfMonth());
        System.out.println("  Day of Week: " + date.getDayOfWeek());
        System.out.println("  Day of Year: " + date.getDayOfYear());
        
        // Extract from LocalTime
        System.out.println("\nTime: " + time);
        System.out.println("  Hour: " + time.getHour());
        System.out.println("  Minute: " + time.getMinute());
        System.out.println("  Second: " + time.getSecond());
        System.out.println("  Nano: " + time.getNano());
        
        // Extract from LocalDateTime
        System.out.println("\nDateTime: " + dateTime);
        System.out.println("  Year: " + dateTime.getYear());
        System.out.println("  Hour: " + dateTime.getHour());
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Compare two dates
     * Solution demonstrates date comparison
     */
    private static void exercise5_CompareDates() {
        System.out.println("EXERCISE 5: Compare Dates");
        System.out.println("-".repeat(60));
        
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 12, 31);
        LocalDate date3 = LocalDate.of(2024, 1, 1);
        
        // Comparison methods
        System.out.println("date1: " + date1);
        System.out.println("date2: " + date2);
        System.out.println("date3: " + date3);
        
        System.out.println("\nisBefore:");
        System.out.println("  date1.isBefore(date2): " + date1.isBefore(date2));
        System.out.println("  date2.isBefore(date1): " + date2.isBefore(date1));
        
        System.out.println("\nisAfter:");
        System.out.println("  date1.isAfter(date2): " + date1.isAfter(date2));
        System.out.println("  date2.isAfter(date1): " + date2.isAfter(date1));
        
        System.out.println("\nisEqual:");
        System.out.println("  date1.isEqual(date3): " + date1.isEqual(date3));
        System.out.println("  date1.isEqual(date2): " + date1.isEqual(date2));
        
        // compareTo method
        System.out.println("\ncompareTo:");
        System.out.println("  date1.compareTo(date2): " + date1.compareTo(date2));
        System.out.println("  date2.compareTo(date1): " + date2.compareTo(date1));
        System.out.println("  date1.compareTo(date3): " + date1.compareTo(date3));
        
        System.out.println();
    }

    // ==================== INTERMEDIATE LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 6: Calculate age from birthdate
     * Solution demonstrates age calculation
     */
    private static void exercise6_CalculateAge() {
        System.out.println("EXERCISE 6: Calculate Age");
        System.out.println("-".repeat(60));
        
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDate today = LocalDate.now();
        
        // Calculate age using Period
        Period age = Period.between(birthDate, today);
        
        System.out.println("Birth Date: " + birthDate);
        System.out.println("Today: " + today);
        System.out.println("\nAge: " + age.getYears() + " years, " + 
                          age.getMonths() + " months, " + 
                          age.getDays() + " days");
        
        // Just years
        int years = age.getYears();
        System.out.println("Years only: " + years);
        
        // Alternative: Using ChronoUnit for total years
        long totalYears = ChronoUnit.YEARS.between(birthDate, today);
        System.out.println("Total years (ChronoUnit): " + totalYears);
        
        // Check if birthday has passed this year
        LocalDate birthdayThisYear = birthDate.withYear(today.getYear());
        boolean hadBirthdayThisYear = today.isAfter(birthdayThisYear) || 
                                      today.isEqual(birthdayThisYear);
        System.out.println("\nHad birthday this year: " + hadBirthdayThisYear);
        
        // Days until next birthday
        LocalDate nextBirthday = birthdayThisYear.isAfter(today) ? 
                                 birthdayThisYear : 
                                 birthdayThisYear.plusYears(1);
        long daysUntilBirthday = ChronoUnit.DAYS.between(today, nextBirthday);
        System.out.println("Days until next birthday: " + daysUntilBirthday);
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Find days between two dates
     * Solution demonstrates calculating differences
     */
    private static void exercise7_DaysBetween() {
        System.out.println("EXERCISE 7: Days Between Dates");
        System.out.println("-".repeat(60));
        
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 12, 31);
        
        // Calculate days between
        long days = ChronoUnit.DAYS.between(start, end);
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("Days between: " + days);
        
        // Other units
        long weeks = ChronoUnit.WEEKS.between(start, end);
        long months = ChronoUnit.MONTHS.between(start, end);
        long years = ChronoUnit.YEARS.between(start, end);
        
        System.out.println("\nWeeks between: " + weeks);
        System.out.println("Months between: " + months);
        System.out.println("Years between: " + years);
        
        // Using Period
        Period period = Period.between(start, end);
        System.out.println("\nPeriod: " + period.getYears() + " years, " + 
                          period.getMonths() + " months, " + 
                          period.getDays() + " days");
        
        // Time difference
        LocalTime time1 = LocalTime.of(9, 0);
        LocalTime time2 = LocalTime.of(17, 30);
        long hours = ChronoUnit.HOURS.between(time1, time2);
        long minutes = ChronoUnit.MINUTES.between(time1, time2);
        
        System.out.println("\nTime difference:");
        System.out.println("  From: " + time1 + " to " + time2);
        System.out.println("  Hours: " + hours);
        System.out.println("  Minutes: " + minutes);
        
        System.out.println();
    }

    /**
     * EXERCISE 8: Format date in different patterns
     * Solution demonstrates date formatting
     */
    private static void exercise8_FormatDates() {
        System.out.println("EXERCISE 8: Format Dates");
        System.out.println("-".repeat(60));
        
        LocalDate date = LocalDate.of(2024, 12, 25);
        LocalTime time = LocalTime.of(14, 30, 45);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        
        // Predefined formatters
        System.out.println("Predefined formats:");
        System.out.println("  ISO_DATE: " + date.format(DateTimeFormatter.ISO_DATE));
        System.out.println("  ISO_TIME: " + time.format(DateTimeFormatter.ISO_TIME));
        System.out.println("  ISO_DATE_TIME: " + dateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        
        // Custom patterns
        System.out.println("\nCustom patterns:");
        System.out.println("  dd-MM-yyyy: " + 
                          date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("  MM/dd/yyyy: " + 
                          date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        System.out.println("  dd-MMM-yyyy: " + 
                          date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
        System.out.println("  EEEE, MMMM dd, yyyy: " + 
                          date.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy")));
        
        // Time formats
        System.out.println("\nTime formats:");
        System.out.println("  HH:mm:ss: " + 
                          time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("  hh:mm a: " + 
                          time.format(DateTimeFormatter.ofPattern("hh:mm a")));
        
        // DateTime formats
        System.out.println("\nDateTime formats:");
        System.out.println("  dd-MMM-yyyy HH:mm: " + 
                          dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        System.out.println("  MM/dd/yyyy hh:mm a: " + 
                          dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")));
        
        System.out.println();
    }

    /**
     * EXERCISE 9: Parse string to LocalDateTime
     * Solution demonstrates parsing date strings
     */
    private static void exercise9_ParseDates() {
        System.out.println("EXERCISE 9: Parse Dates");
        System.out.println("-".repeat(60));
        
        // Parse ISO format
        String isoDate = "2024-12-25";
        LocalDate date1 = LocalDate.parse(isoDate);
        System.out.println("Parsed ISO date: " + date1);
        
        // Parse custom format
        String customDate = "25-Dec-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate date2 = LocalDate.parse(customDate, formatter);
        System.out.println("Parsed custom date: " + date2);
        
        // Parse different formats
        String usDate = "12/25/2024";
        LocalDate date3 = LocalDate.parse(usDate, 
                                         DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("Parsed US date: " + date3);
        
        // Parse time
        String timeStr = "14:30:45";
        LocalTime time = LocalTime.parse(timeStr);
        System.out.println("\nParsed time: " + time);
        
        // Parse datetime
        String dateTimeStr = "2024-12-25T14:30:45";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        System.out.println("Parsed datetime: " + dateTime);
        
        // Parse with custom format
        String customDateTime = "25-Dec-2024 02:30 PM";
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        LocalDateTime dateTime2 = LocalDateTime.parse(customDateTime, dtFormatter);
        System.out.println("Parsed custom datetime: " + dateTime2);
        
        System.out.println();
    }

    /**
     * EXERCISE 10: Work with different timezones
     * Solution demonstrates timezone handling
     */
    private static void exercise10_Timezones() {
        System.out.println("EXERCISE 10: Work with Timezones");
        System.out.println("-".repeat(60));
        
        // Current time in different zones
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime londonTime = ZonedDateTime.now(ZoneId.of("Europe/London"));
        ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime sydneyTime = ZonedDateTime.now(ZoneId.of("Australia/Sydney"));
        
        System.out.println("Current time in different zones:");
        System.out.println("  New York: " + nyTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("  London: " + londonTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("  Tokyo: " + tokyoTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("  Sydney: " + sydneyTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        
        // Convert between timezones
        System.out.println("\nConverting NY time to other zones:");
        ZonedDateTime nyToLondon = nyTime.withZoneSameInstant(ZoneId.of("Europe/London"));
        ZonedDateTime nyToTokyo = nyTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        
        System.out.println("  NY: " + nyTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("  Same instant in London: " + 
                          nyToLondon.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("  Same instant in Tokyo: " + 
                          nyToTokyo.format(DateTimeFormatter.ofPattern("HH:mm")));
        
        // Get all available zone IDs
        System.out.println("\nSample zone IDs:");
        ZoneId.getAvailableZoneIds().stream()
                .filter(id -> id.startsWith("America/") || id.startsWith("Europe/"))
                .sorted()
                .limit(5)
                .forEach(id -> System.out.println("  " + id));
        
        System.out.println();
    }

    // ==================== ADVANCED LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 11: Build event scheduling system
     * Solution demonstrates practical scheduling application
     */
    private static void exercise11_EventScheduling() {
        System.out.println("EXERCISE 11: Event Scheduling System");
        System.out.println("-".repeat(60));
        
        // Create events
        Event meeting = new Event("Team Meeting", 
                                  LocalDateTime.of(2024, 12, 25, 10, 0),
                                  Duration.ofHours(2));
        
        Event lunch = new Event("Lunch Break",
                               LocalDateTime.of(2024, 12, 25, 12, 0),
                               Duration.ofMinutes(60));
        
        Event presentation = new Event("Project Presentation",
                                      LocalDateTime.of(2024, 12, 25, 14, 0),
                                      Duration.ofMinutes(90));
        
        List<Event> events = Arrays.asList(meeting, lunch, presentation);
        
        // Display schedule
        System.out.println("Event Schedule for " + LocalDate.now());
        System.out.println("-".repeat(50));
        events.forEach(event -> {
            System.out.println(event);
            System.out.println("  Starts: " + event.startTime.format(
                DateTimeFormatter.ofPattern("hh:mm a")));
            System.out.println("  Ends: " + event.getEndTime().format(
                DateTimeFormatter.ofPattern("hh:mm a")));
            System.out.println("  Duration: " + event.duration.toMinutes() + " minutes");
            System.out.println();
        });
        
        // Check for conflicts
        System.out.println("Checking for conflicts:");
        for (int i = 0; i < events.size() - 1; i++) {
            Event e1 = events.get(i);
            Event e2 = events.get(i + 1);
            if (e1.overlapsWith(e2)) {
                System.out.println("  CONFLICT: " + e1.name + " overlaps with " + e2.name);
            } else {
                System.out.println("  OK: " + e1.name + " → " + e2.name);
            }
        }
        
        System.out.println();
    }

    /**
     * EXERCISE 12: Calculate business days between dates
     * Solution demonstrates working day calculations
     */
    private static void exercise12_BusinessDays() {
        System.out.println("EXERCISE 12: Business Days Calculation");
        System.out.println("-".repeat(60));
        
        LocalDate start = LocalDate.of(2024, 12, 23); // Monday
        LocalDate end = LocalDate.of(2024, 12, 31);   // Tuesday
        
        int businessDays = countBusinessDays(start, end);
        
        System.out.println("Start: " + start + " (" + start.getDayOfWeek() + ")");
        System.out.println("End: " + end + " (" + end.getDayOfWeek() + ")");
        System.out.println("Business days: " + businessDays);
        
        // Add business days
        LocalDate today = LocalDate.now();
        LocalDate after5BusinessDays = addBusinessDays(today, 5);
        
        System.out.println("\nToday: " + today);
        System.out.println("After 5 business days: " + after5BusinessDays);
        
        // Check if business day
        System.out.println("\nIs business day?");
        System.out.println("  Monday: " + isBusinessDay(LocalDate.of(2024, 12, 23)));
        System.out.println("  Saturday: " + isBusinessDay(LocalDate.of(2024, 12, 28)));
        System.out.println("  Sunday: " + isBusinessDay(LocalDate.of(2024, 12, 29)));
        
        System.out.println();
    }

    /**
     * EXERCISE 13: Handle recurring events
     * Solution demonstrates recurring event patterns
     */
    private static void exercise13_RecurringEvents() {
        System.out.println("EXERCISE 13: Recurring Events");
        System.out.println("-".repeat(60));
        
        LocalDate startDate = LocalDate.now();
        
        // Daily recurring
        System.out.println("Daily recurring (next 7 occurrences):");
        generateRecurringDates(startDate, 7, Period.ofDays(1))
                .forEach(d -> System.out.println("  " + d));
        
        // Weekly recurring
        System.out.println("\nWeekly recurring (next 5 occurrences):");
        generateRecurringDates(startDate, 5, Period.ofWeeks(1))
                .forEach(d -> System.out.println("  " + d + " (" + d.getDayOfWeek() + ")"));
        
        // Monthly recurring
        System.out.println("\nMonthly recurring (next 6 occurrences):");
        generateRecurringDates(startDate, 6, Period.ofMonths(1))
                .forEach(d -> System.out.println("  " + d));
        
        // Every weekday
        System.out.println("\nWeekdays only (next 10):");
        startDate.datesUntil(startDate.plusMonths(2))
                .filter(d -> d.getDayOfWeek().getValue() <= 5)
                .limit(10)
                .forEach(d -> System.out.println("  " + d + " (" + d.getDayOfWeek() + ")"));
        
        System.out.println();
    }

    /**
     * EXERCISE 14: Timezone conversion utility
     * Solution demonstrates timezone conversion helpers
     */
    private static void exercise14_TimezoneConversion() {
        System.out.println("EXERCISE 14: Timezone Conversion Utility");
        System.out.println("-".repeat(60));
        
        LocalDateTime localTime = LocalDateTime.of(2024, 12, 25, 15, 30);
        
        // Convert to multiple timezones
        Map<String, ZoneId> zones = Map.of(
            "New York", ZoneId.of("America/New_York"),
            "London", ZoneId.of("Europe/London"),
            "Tokyo", ZoneId.of("Asia/Tokyo"),
            "Sydney", ZoneId.of("Australia/Sydney"),
            "Dubai", ZoneId.of("Asia/Dubai")
        );
        
        System.out.println("Original time: " + localTime);
        System.out.println("\nConverted to different timezones:");
        
        ZonedDateTime baseTime = localTime.atZone(ZoneId.systemDefault());
        zones.forEach((name, zoneId) -> {
            ZonedDateTime converted = baseTime.withZoneSameInstant(zoneId);
            System.out.println(String.format("  %-12s: %s", 
                name, 
                converted.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm z"))));
        });
        
        // Time difference calculator
        System.out.println("\nTime differences from UTC:");
        zones.forEach((name, zoneId) -> {
            ZonedDateTime time = ZonedDateTime.now(zoneId);
            ZoneOffset offset = time.getOffset();
            System.out.println(String.format("  %-12s: UTC%s", name, offset));
        });
        
        System.out.println();
    }

    /**
     * EXERCISE 15: Age calculator with precise duration
     * Solution demonstrates precise age calculation
     */
    private static void exercise15_AgeCalculatorPrecise() {
        System.out.println("EXERCISE 15: Precise Age Calculator");
        System.out.println("-".repeat(60));
        
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDateTime birthTime = LocalDateTime.of(1990, 5, 15, 8, 30);
        
        // Precise age
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthDate, now);
        
        System.out.println("Birth Date: " + birthDate);
        System.out.println("Current Date: " + now);
        System.out.println("\nAge Breakdown:");
        System.out.println("  Years: " + age.getYears());
        System.out.println("  Months: " + age.getMonths());
        System.out.println("  Days: " + age.getDays());
        
        // Total measurements
        long totalDays = ChronoUnit.DAYS.between(birthDate, now);
        long totalWeeks = ChronoUnit.WEEKS.between(birthDate, now);
        long totalMonths = ChronoUnit.MONTHS.between(birthDate, now);
        long totalYears = ChronoUnit.YEARS.between(birthDate, now);
        
        System.out.println("\nTotal Time Alive:");
        System.out.println("  " + totalDays + " days");
        System.out.println("  " + totalWeeks + " weeks");
        System.out.println("  " + totalMonths + " months");
        System.out.println("  " + totalYears + " years");
        
        // Next milestone
        LocalDate next30th = birthDate.plusYears(30);
        LocalDate next40th = birthDate.plusYears(40);
        LocalDate next50th = birthDate.plusYears(50);
        
        System.out.println("\nUpcoming Milestones:");
        if (now.isBefore(next30th)) {
            long daysTo30 = ChronoUnit.DAYS.between(now, next30th);
            System.out.println("  30th birthday in " + daysTo30 + " days (" + next30th + ")");
        }
        if (now.isBefore(next40th)) {
            long daysTo40 = ChronoUnit.DAYS.between(now, next40th);
            System.out.println("  40th birthday in " + daysTo40 + " days (" + next40th + ")");
        }
        if (now.isBefore(next50th)) {
            long daysTo50 = ChronoUnit.DAYS.between(now, next50th);
            System.out.println("  50th birthday in " + daysTo50 + " days (" + next50th + ")");
        }
        
        System.out.println();
    }

    // ==================== HELPER METHODS AND CLASSES ====================

    static class Event {
        String name;
        LocalDateTime startTime;
        Duration duration;
        
        Event(String name, LocalDateTime startTime, Duration duration) {
            this.name = name;
            this.startTime = startTime;
            this.duration = duration;
        }
        
        LocalDateTime getEndTime() {
            return startTime.plus(duration);
        }
        
        boolean overlapsWith(Event other) {
            return !this.getEndTime().isBefore(other.startTime) && 
                   !other.getEndTime().isBefore(this.startTime);
        }
        
        @Override
        public String toString() {
            return name;
        }
    }

    private static boolean isBusinessDay(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    private static int countBusinessDays(LocalDate start, LocalDate end) {
        return (int) start.datesUntil(end.plusDays(1))
                .filter(Solutions::isBusinessDay)
                .count();
    }

    private static LocalDate addBusinessDays(LocalDate start, int daysToAdd) {
        LocalDate result = start;
        int added = 0;
        while (added < daysToAdd) {
            result = result.plusDays(1);
            if (isBusinessDay(result)) {
                added++;
            }
        }
        return result;
    }

    private static List<LocalDate> generateRecurringDates(LocalDate start, int count, Period period) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;
        for (int i = 0; i < count; i++) {
            dates.add(current);
            current = current.plus(period);
        }
        return dates;
    }
}
