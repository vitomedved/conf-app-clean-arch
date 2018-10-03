package template.android.com.domain.storage;

public interface ApplicationStorage {

    void saveString(String key, String value);

    String getString(String key);
}
