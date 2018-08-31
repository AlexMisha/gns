package com.shepard.gns.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.BranchRepository
import com.shepard.gns.database.dao.ProjectRepository
import com.shepard.gns.database.entity.Branch
import com.shepard.gns.database.entity.Project
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
class BranchRepositoryTest {
    private lateinit var db: AppDatabase
    private lateinit var projectRepository: ProjectRepository
    private lateinit var branchRepository: BranchRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
                .build()
        projectRepository = db.projectRepository()
        branchRepository = db.branchRepository()

        Observable.just(arrayListOf(Project(name = "gns"), Project(name = "example")))
                .subscribeOn(Schedulers.io())
                .subscribe({ projectRepository.saveAll(it) }, { throw it }, {
                    Observable.just(arrayListOf(Branch(name = "task1", projectId = 1),
                            Branch(name = "task2", projectId = 2)))
                            .subscribeOn(Schedulers.io())
                            .subscribe { branchRepository.saveAll(it) }
                })
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun findAll() {
        branchRepository.findAll()
                .subscribeOn(Schedulers.io())
                .flatMap { Flowable.fromIterable(it) }
                .subscribe { assertTrue(it.name == "task1" || it.name == "task2") }
    }

    @Test
    @Throws(Exception::class)
    fun findById() {
        branchRepository.findById(1)
                .subscribeOn(Schedulers.io())
                .subscribe { assertTrue(it.name == "task1") }
    }

    @Test
    @Throws(Exception::class)
    fun findByProjectId() {
        branchRepository.findByProjectId(1)
                .subscribeOn(Schedulers.io())
                .flatMap { Flowable.fromIterable(it) }
                .subscribe { assertTrue(it.name == "task1") }
    }

}