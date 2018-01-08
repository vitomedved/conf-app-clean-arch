package template.android.com.domain.utils;

import java.util.Date;

public interface DateUtils {

    Date convertISO8601ToTimestamp(String isoISO8601DateString);

    String convertToUserReadableTimestamp(long timestamp);

    String convertToSimpleUserReadableDateFormat(long timestamp);

    String convertToSimpleUserReadableDateFormatWithSeconds(long timestamp);
}
