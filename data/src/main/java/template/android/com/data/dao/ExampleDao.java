package template.android.com.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import template.android.com.data.dao.model.ExampleDbModel;

@Dao
public interface ExampleDao {

    @Query("SELECT * FROM exampledbmodel WHERE id = :id")
    ExampleDbModel getById(int id);


    @Query("SELECT * FROM exampledbmodel WHERE name LIKE :name")
    ExampleDbModel getByName(String name);

    @Query("SELECT * FROM exampledbmodel")
    List<ExampleDbModel> getAll();

    @Insert
    void insert(ExampleDbModel model, ExampleDbModel... models);

    @Query("DELETE FROM exampleDbModel")
    void deleteAll();
}
