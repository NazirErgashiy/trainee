package uz.nazir.trainee.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * Changes time pattern to specific - which declared in application.properties
 */
@Component
public class StandardTime {

    private static String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static String timezone = "GMT+5";

    private static DateFormat df = new SimpleDateFormat(pattern);

    static {
        TimeZone tz = TimeZone.getTimeZone(timezone);
        df.setTimeZone(tz);
    }

    /**
     * Get time now
     *
     * @return NOW with specific time pattern
     */
    public static LocalDateTime nowIso8601() {
        String nowAsISO = df.format(new Date());
        return LocalDateTime.parse(nowAsISO, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Let spring set value of pattern via application.properties
     *
     * @param pattern set pattern
     */
    @Value("${application.timezone.pattern}")
    public void setPatternStatic(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Let spring set value of timezone via application.properties
     *
     * @param timezone set timezone
     */
    @Value("${application.timezone}")
    public void setTimezoneStatic(String timezone) {
        this.timezone = timezone;
    }
}
