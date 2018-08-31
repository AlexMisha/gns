package com.shepard.gns.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.UserRepository
import com.shepard.gns.database.entity.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author shepard
 * @since 24.12.2017
 */
@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    private lateinit var userRepository: UserRepository
    private lateinit var db: AppDatabase

    @Before
    @Throws(Exception::class)
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
                .build()
        userRepository = db.userRepository()
        Observable.just(arrayListOf(User(name = "shepard"), User(name = "michael"), User(name = "eva")))
                .subscribeOn(Schedulers.io())
                .subscribe { userRepository.saveAll(it) }
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        userRepository.findAll()
                .subscribeOn(Schedulers.io())
                .subscribe({ userRepository.deleteAll(it) }, {
                    db.close()
                    throw it
                }, { db.close() })
    }

    @Test
    @Throws(Exception::class)
    fun read() {
        userRepository.findByName("shepard")
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    @Test
    @Throws(Exception::class)
    fun update() {
        userRepository.findByName("michael")
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.name = "john"
                    userRepository.updateEntity(it)
                }, { throw it }, {
                    userRepository.findByName("john")
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                })
    }
}