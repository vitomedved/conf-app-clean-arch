package template.android.com.data.storage;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

@RunWith(AndroidJUnit4.class)
public final class StorageOptionsTest {

    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    private Context context;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext()
                                         .getApplicationContext();
    }

    @Test
    public void testStorageOptions() throws Exception {

        writeToDirectory(context.getFilesDir(), "getFilesDir_file", "getFilesDir");
        writeToDirectory(context.getDir("getDir", Context.MODE_PRIVATE), "getDir_file", "getDir");
        writeToDirectory(context.getCodeCacheDir(), "codeCacheDir_file", "getCodeCacheDir");
        writeToDirectory(context.getCacheDir(), "getCacheDir_file", "getCacheDir");
        writeToDirectory(context.getExternalFilesDir("getExternalFilesDir"), "getExternalFilesDir_file", "getExternalFilesDir");
        writeToDirectory(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "getExternalFilesDir_documents_file", "getExternalFilesDir_documents");
        writeToDirectory(Environment.getExternalStorageDirectory(), "getExternalStorageDirectory_file", "getExternalStorageDirectory");
        writeToDirectory(Environment.getDataDirectory(), "getDataDirectory_file", "getDataDirectory");
    }

    private void writeToDirectory(File parent, String fileName, String tag) throws Exception {

        BufferedWriter writer;

        final File file = new File(parent, fileName);
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();

        writer = new BufferedWriter(new PrintWriter(new FileOutputStream(file)));

        for (int i = 0; i < 10_000; i++) {
            writer.write("fjdksafkdsajflkadsjklfjkalnsudvndluafnfuindsjkhfadksh");
            writer.newLine();
        }

        Log.d("luka_log", String.format("Stored file: %24s - tag: %10s - in: %s", fileName, tag, parent.getAbsolutePath()));
    }
}
