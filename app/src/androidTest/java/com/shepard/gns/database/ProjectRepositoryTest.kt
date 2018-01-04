package com.shepard.gns.database

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.ProjectRepository
import com.shepard.gns.database.entity.Project
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author shepard
 * @since 04.01.2018
 */
@RunWith(AndroidJUnit4::class)
class ProjectRepositoryTest {
    private lateinit var db: AppDatabase
    private lateinit var projectRepository: ProjectRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
                .build()
        projectRepository = db.projectRepository()

        Observable.just(arrayListOf(Project(name = "gns"), Project(name = "example")))
                .subscribeOn(Schedulers.io())
                .subscribe { projectRepository.saveAll(it) }
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun findAll() {
        projectRepository.findAll()
                .subscribeOn(Schedulers.io())
                .flatMap { Flowable.fromIterable(it) }
                .subscribe { assertTrue(it.name == "gns" || it.name == "example") }
    }

    @Test
    @Throws(Exception::class)
    fun findById() {
        projectRepository.findById(1)
                .subscribeOn(Schedulers.io())
                .subscribe { assertTrue(it.name == "gns") }
    }

}