package com.shepard.gns.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.CommitRepository
import com.shepard.gns.database.dao.WatchingBranchRepository
import com.shepard.gns.database.entity.Commit
import com.shepard.gns.database.entity.WatchingBranch
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author shepard
 * @since 19.09.2018
 */
@RunWith(AndroidJUnit4::class)
class CommitRepositoryTest {
    private lateinit var db: AppDatabase
    private lateinit var commitRepository: CommitRepository
    private lateinit var watchingBranchRepository: WatchingBranchRepository

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        commitRepository = db.commitRepository()
        watchingBranchRepository = db.watchingBranchRepository()
    }

    @After
    fun tearDown() {
        db.clearAllTables()
    }

    @Test
    fun find_commits_by_branch_id_success() {
        watchingBranchRepository.save(WatchingBranch())
        val branch = watchingBranchRepository.syncFindAll().single()
        commitRepository.saveAll(listOf(
                Commit(message = "message0", branchId = branch.id),
                Commit(message = "message1", branchId = branch.id),
                Commit(message = "message2", branchId = branch.id)))

        val commits = commitRepository.syncFindByBranchId(branch.id)

        assertTrue(commits.isNotEmpty())
        assertEquals(3, commits.size)

        val baseMessage = "message"
        commits.forEachIndexed { index, commit ->
            assertNotNull(commit)
            assertEquals(baseMessage + index, commit.message)
        }
    }

    @Test
    fun find_commits_by_branch_id_failure() {
        val commits = commitRepository.syncFindByBranchId(345L)

        assertTrue(commits.isEmpty())
    }
}