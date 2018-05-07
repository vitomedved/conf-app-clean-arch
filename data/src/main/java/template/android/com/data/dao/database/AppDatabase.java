package template.android.com.data.dao.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import template.android.com.data.dao.ExampleDao;
import template.android.com.data.dao.model.ExampleDbModel;

@Database(
        entities = {
                ExampleDbModel.class
        },
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ExampleDao exampleDao();
}
