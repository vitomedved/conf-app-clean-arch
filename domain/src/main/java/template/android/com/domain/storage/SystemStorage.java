package template.android.com.domain.storage;

import java.io.File;

public interface SystemStorage {

    File getFilesDir();

    File getDir(String name);

//    File getCodeCacheDir();

    File getCacheDir();

    File getExternalDir(String name);

    boolean isExternalDirMounted();
}
