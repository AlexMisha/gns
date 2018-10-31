package com.shepard.gns.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.GitAccountRepository
import com.shepard.gns.database.dao.TokenRepository
import com.shepard.gns.database.entity.GitAccount
import com.shepard.gns.database.entity.Token
import com.shepard.gns.database.entity.TokenType
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
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
    private lateinit var gitAccountRepository: GitAccountRepository

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        gitAccountRepository = db.gitAccountRepository()
        tokenRepository = db.tokenRepository()
    }

    @After
    fun tearDown() {
        db.clearAllTables()
    }

    @Test
    fun find_token_by_account_id_success() {
        gitAccountRepository.save(GitAccount(name = "alice"))
        val account = gitAccountRepository.synсFindAll().single()
        tokenRepository.save(Token(type = TokenType.GITHUB, accountId = account.id, value = "SlAV32hkKG"))

        val token = tokenRepository.syncFindByAccountId(account.id)

        assertNotNull(token)
        assertEquals(TokenType.GITHUB, token.type)
        assertEquals("SlAV32hkKG", token.value)
    }

    @Test
    fun find_token_by_account_id_failure() {
        val token = tokenRepository.syncFindByAccountId(345L)

        assertNull(token)
    }
}