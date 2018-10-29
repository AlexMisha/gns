package com.shepard.gns.api

import com.shepard.gns.database.entity.Commit
import retrofit2.Call


/**
 * @author paul
 * @since 28.10.18
 */
interface CommitApi {

    /**
     * The commit by sha
     * @param sha check sum of commit
     * @return Call instance
     */
    fun getCommitBy(sha: String): Call<Commit>

    /**
     * The list of commits by branchid
     * @param branchId unique identifier of branch
     * @return Call instance
     */
    fun getCommitsBy(branchId: Long): Call<List<Commit>>

    /**
     * The list of commits by branch id not earlier than dateFrom
     * @param branchId unique identifier of branch
     * @param dateFrom data later which commits would be retrieved
     * @return Call instance
     */
    fun getCommitsBy(branchId: Long, dateFrom: Long): Call<List<Commit>>
}