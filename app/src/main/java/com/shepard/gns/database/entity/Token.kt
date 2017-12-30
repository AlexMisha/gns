package com.shepard.gns.database.entity

import android.arch.persistence.room.*

/**
 * @author shepard
 * @since 21.12.2017
 */
@Entity(tableName = "token", foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = [("id")],
        childColumns = [("user_id")]))])
data class Token(@ColumnInfo(name = "id")
                 @PrimaryKey(autoGenerate = true)
                 var id: Long = 0,

                 @ColumnInfo(name = " type")
                 var type: TokenType,

                 @ColumnInfo(name = "user_id")
                 var userId: Long = 0,

                 @ColumnInfo(name = "value")
                 var value: String) {
    companion object TokenTypeConverter {
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