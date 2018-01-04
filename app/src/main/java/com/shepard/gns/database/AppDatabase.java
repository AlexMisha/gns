package com.shepard.gns.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.shepard.gns.database.dao.TokenRepository;
import com.shepard.gns.database.dao.UserRepository;
import com.shepard.gns.database.entity.Branch;
import com.shepard.gns.database.entity.Commit;
import com.shepard.gns.database.entity.Project;
import com.shepard.gns.database.entity.Token;
import com.shepard.gns.database.entity.User;

/**
 * @author shepard
 * @since 24.12.2017
 */
@Database(entities = { User.class, Token.class, Project.class, Branch.class,
    Commit.class }, version = 1)
@TypeConverters(Token.TokenTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
  public abstract UserRepository userRepository();

  public abstract TokenRepository tokenRepository();
}
