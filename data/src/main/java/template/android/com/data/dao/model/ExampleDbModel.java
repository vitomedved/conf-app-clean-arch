package template.android.com.data.dao.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public final class ExampleDbModel {

    public static final String NAME = ExampleDbModel.class.getSimpleName();

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @Ignore
    public ExampleDbModel(final String name) {
        this(0, name);
    }

    public ExampleDbModel(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
}
