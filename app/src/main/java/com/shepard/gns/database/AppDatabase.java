package com.shepard.gns.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shepard.gns.database.dao.UserRepository;
import com.shepard.gns.database.entity.User;

/**
 * @author shepard
 * @since 24.12.2017
 */
@Database(entities = { User.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract UserRepository userRepository();
}
