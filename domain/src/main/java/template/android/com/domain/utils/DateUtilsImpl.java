package template.android.com.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtilsImpl implements DateUtils {

    private static final String ISO_8601_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String HUMAN_READABLE_DATE_FORMAT = "EEE, d MMM yyyy, hh:mm aaa";
    private static final String SIMPLE_HUMAN_READABLE_DATE_FORMAT = "dd.mm.yyyy HH:mm";
    private static final String SIMPLE_HUMAN_READABLE_DATE_FORMAT_SECONDS = "dd.mm.yyyy HH:mm:ss";

    private static final long DEFAULT_TIMESTAMP = 0L;

    private final SimpleDateFormat iso8601DateFormat = new SimpleDateFormat(ISO_8601_DATE_FORMAT_PATTERN, Locale.US);
    private final SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat(HUMAN_READABLE_DATE_FORMAT, Locale.US);
    private final SimpleDateFormat simpleHumanReadableDateFormat = new SimpleDateFormat(SIMPLE_HUMAN_READABLE_DATE_FORMAT, Locale.US);
    private final SimpleDateFormat simpleHumanReadableDateFormatSeconds = new SimpleDateFormat(SIMPLE_HUMAN_READABLE_DATE_FORMAT_SECONDS, Locale.US);

    private final StringUtils stringUtils;

    public DateUtilsImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public Date convertISO8601ToTimestamp(final String dateString) {
        if (stringUtils.isEmpty(dateString)) {
            return new Date(DEFAULT_TIMESTAMP);
        }

        synchronized (iso8601DateFormat) {
            try {
                return iso8601DateFormat.parse(dateString);

            } catch (final ParseException e) {
                return new Date(DEFAULT_TIMESTAMP);
            }
        }
    }

    @Override
    public String convertToUserReadableTimestamp(final long timestamp) {
        synchronized (humanReadableDateFormat) {
            return humanReadableDateFormat.format(timestamp);
        }
    }

    @Override
    public String convertToSimpleUserReadableDateFormat(final long timestamp) {
        synchronized (simpleHumanReadableDateFormat) {
            return simpleHumanReadableDateFormat.format(timestamp);
        }
    }

    @Override
    public String convertToSimpleUserReadableDateFormatWithSeconds(final long timestamp) {
        synchronized (simpleHumanReadableDateFormatSeconds) {
            return simpleHumanReadableDateFormatSeconds.format(timestamp);
        }
    }
}
