package com.shepard.gns.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shepard.gns.database.dao.CommitRepository
import com.shepard.gns.database.dao.GitAccountRepository
import com.shepard.gns.database.dao.TokenRepository
import com.shepard.gns.database.dao.WatchingBranchRepository
import com.shepard.gns.database.entity.Commit
import com.shepard.gns.database.entity.GitAccount
import com.shepard.gns.database.entity.Token
import com.shepard.gns.database.entity.WatchingBranch

/**
 * @author shepard
 * @since 24.12.2017
 */
@Database(entities = [GitAccount::class, Token::class, WatchingBranch::class, Commit::class],
        version = 1)
@TypeConverters(Token.TokenTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gitAccountRepository(): GitAccountRepository

    abstract fun tokenRepository(): TokenRepository

    abstract fun watchingBranchRepository(): WatchingBranchRepository

    abstract fun commitRepository(): CommitRepository
}
