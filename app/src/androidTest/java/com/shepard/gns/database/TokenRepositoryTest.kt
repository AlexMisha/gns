package com.shepard.gns.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.shepard.gns.database.dao.TokenRepository
import com.shepard.gns.database.dao.GitAccountRepository
import com.shepard.gns.database.entity.Token
import com.shepard.gns.database.entity.TokenType
import com.shepard.gns.database.entity.GitAccount
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.After
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
                .build()
        gitAccountRepository = db.gitAccountRepository().apply { save(GitAccount(name = "alice")) }
        tokenRepository = db.tokenRepository().apply {
            gitAccountRepository.findAll().observeForever {
                val account = it.single()
                save(Token(type = TokenType.GITHUB, accountId = account.id, value = "SlAV32hkKG"))
            }
        }
    }

    @After
    fun tearDown() {
        gitAccountRepository.findAll().observeForever { gitAccountRepository.deleteAll(it) }
        tokenRepository.findAll().observeForever { tokenRepository.deleteAll(it) }
    }

    @Test
    fun find_token_by_account_id_success() {
        gitAccountRepository.findAll().observeForever {
            val account = it.single()
            tokenRepository.findByAccountId(account.id).observeForever {
                it shouldNotBe null
                it.type shouldBe TokenType.GITHUB
                it.value shouldBe "SlAV32hkKG"
            }
        }
    }

    @Test
    fun find_token_by_account_id_failure() {
        tokenRepository.findByAccountId(345L).observeForever {
            it shouldBe null
        }
    }
}