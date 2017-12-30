package com.shepard.gns.database

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.UserRepository
import com.shepard.gns.database.entity.User
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase.assertTrue
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
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun save() {
        Observable.just(User(name = "shepard"))
                .subscribeOn(Schedulers.io())
                .subscribe { userRepository.save(it) }
    }

    @Test
    @Throws(Exception::class)
    fun read() {
        userRepository.findByName("shepard")
                .subscribeOn(Schedulers.io())
                .subscribe { assertTrue(it.id == 1L) }
    }

    @Test
    @Throws(Exception::class)
    fun update() {
        userRepository.findByName("shepard")
                .subscribeOn(Schedulers.io())
                .subscribe {
                    it.name = "michael"
                    userRepository.updateEntity(it)
                }
    }

    @Test
    @Throws(Exception::class)
    fun delete() {
        userRepository.findByName("michael")
                .subscribeOn(Schedulers.io())
                .subscribe { userRepository.delete(it) }
    }

}