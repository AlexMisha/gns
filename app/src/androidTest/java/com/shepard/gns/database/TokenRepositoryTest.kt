package com.shepard.gns.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.TokenRepository
import com.shepard.gns.database.dao.UserRepository
import com.shepard.gns.database.entity.Token
import com.shepard.gns.database.entity.TokenType
import com.shepard.gns.database.entity.User
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author shepard
 * @since 30.12.2017
 */
@RunWith(AndroidJUnit4::class)
class TokenRepositoryTest {
    private lateinit var db: AppDatabase
    private lateinit var tokenRepository: TokenRepository
    private lateinit var userRepository: UserRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
                .build()
        tokenRepository = db.tokenRepository()
        userRepository = db.userRepository()

        Observable.just(User(name = "alice"))
                .subscribeOn(Schedulers.io())
                .subscribe({ userRepository.save(it) }, { throw it }, {
                    userRepository.findByName("alice")
                            .subscribeOn(Schedulers.io())
                            .subscribe {
                                Observable.just(arrayListOf(
                                        Token(type = TokenType.GITHUB, userId = it.id
                                                , value = "SlAV32hkKG"),
                                        Token(type = TokenType.GITLAB, userId = it.id
                                                , value = "8xLOxBtZp8")))
                                        .subscribeOn(Schedulers.io())
                                        .subscribe { tokenRepository.saveAll(it) }
                            }
                })
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        tokenRepository.findAll()
                .subscribeOn(Schedulers.io())
                .subscribe({ tokenRepository.deleteAll(it) }, {
                    db.close()
                    throw it
                }, { db.close() })
    }

    @Test
    @Throws(Exception::class)
    fun findByTokenType() {
        tokenRepository.findByTokenType(TokenType.GITHUB)
                .subscribeOn(Schedulers.io())
                .flatMap { Flowable.fromIterable(it) }
                .subscribe { assertTrue(it.value == "SlAV32hkKG") }

        tokenRepository.findByTokenType(TokenType.GITLAB)
                .subscribeOn(Schedulers.io())
                .flatMap { Flowable.fromIterable(it) }
                .subscribe { assertTrue(it.value == "8xLOxBtZp8") }
    }

    @Test
    @Throws(Exception::class)
    fun updateEntity() {
        var id: Long

        userRepository.findByName("alice")
                .subscribeOn(Schedulers.io())
                .subscribe {
                    id = it.id
                    tokenRepository.findByTokenTypeAndUserId(TokenType.GITHUB, id)
                            .subscribeOn(Schedulers.io())
                            .subscribe({
                                it.value = "8xLOxDtZp8"
                                tokenRepository.updateEntity(it)
                            }, { throw it }, {
                                tokenRepository.findByTokenTypeAndUserId(TokenType.GITHUB, id)
                                        .subscribeOn(Schedulers.io())
                                        .subscribe { assertTrue(it.value == "8xLOxDtZp8") }
                            })
                }
    }

}