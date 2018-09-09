package template.android.com.device.storage;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import template.android.com.domain.storage.SystemStorage;

public final class SystemStorageImpl implements SystemStorage {

    private final Context context;

    public SystemStorageImpl(final Context context) {
        this.context = context;
    }

    @Override
    public File getFilesDir() {
        return context.getFilesDir();
    }

    @Override
    public File getDir(final String name) {
        return context.getDir(name, Context.MODE_PRIVATE);
    }

    @Override
    public File getCacheDir() {
        return context.getCacheDir();
    }

    @Override
    public File getExternalDir(final String name) {
        return context.getExternalFilesDir(name);
    }

    @Override
    public boolean isExternalDirMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
