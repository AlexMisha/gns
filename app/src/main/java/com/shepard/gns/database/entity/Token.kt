package com.shepard.gns.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.TypeConverter
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

/**
 * @author shepard
 * @since 21.12.2017
 */
@Entity(tableName = "token", foreignKeys = [(ForeignKey(entity = GitAccount::class,
        parentColumns = [("id")],
        childColumns = [("account_id")]))])
data class Token(@ColumnInfo(name = "id")
                 @PrimaryKey(autoGenerate = true)
                 var id: Long = 0,

                 @ColumnInfo(name = "value")
                 var value: String,

                 @ColumnInfo(name = "type")
                 var type: TokenType,

                 @ColumnInfo(name = "account_id")
                 var accountId: Long = 1) {
    class TokenTypeConverter {
        @TypeConverter
        fun tokenTypeToLong(tokenType: TokenType): Long = tokenType.ordinal.toLong()

        @TypeConverter
        fun toTokenType(fromDb: Long): TokenType {
            if (fromDb >= TokenType.values().size) {
                throw IllegalArgumentException("invalid index of TokenType from db: $fromDb")
            }
            return TokenType.values()[fromDb.toInt()]
        }
    }
}