package com.shepard.gns.database;

import com.shepard.gns.database.dao.BranchRepository;
import com.shepard.gns.database.dao.CommitRepository;
import com.shepard.gns.database.dao.ProjectRepository;
import com.shepard.gns.database.dao.TokenRepository;
import com.shepard.gns.database.dao.UserRepository;
import com.shepard.gns.database.entity.WatchingBranch;
import com.shepard.gns.database.entity.Commit;
import com.shepard.gns.database.entity.Token;
import com.shepard.gns.database.entity.GitAccount;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * @author shepard
 * @since 24.12.2017
 */
@Database(entities = {GitAccount.class, Token.class, WatchingBranch.class,
        Commit.class}, version = 1)
@TypeConverters(Token.TokenTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserRepository userRepository();

    public abstract TokenRepository tokenRepository();

    public abstract ProjectRepository projectRepository();

    public abstract BranchRepository branchRepository();

    public abstract CommitRepository commitRepository();
}
