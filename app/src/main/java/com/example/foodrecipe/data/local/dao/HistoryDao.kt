package com.example.foodrecipe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodrecipe.data.local.entity.HistoryEntity

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: HistoryEntity)

    @Query("""
        SELECT * FROM history
        WHERE userId = :userId
        ORDER BY viewedAt DESC
    """)
    suspend fun getUserHistory(userId: String): List<HistoryEntity>

    @Query("""
        SELECT * FROM history
        WHERE userId = :userId
        ORDER BY viewedAt DESC
        LIMIT :limit
    """)
    suspend fun getRecentHistory(userId: String, limit: Int): List<HistoryEntity>

    @Query("""
        DELETE FROM history 
        WHERE userId = :userId
    """)
    suspend fun clearHistory(userId: String)

    @Query("""
        DELETE FROM history
        WHERE viewedAt < :timestamp
    """)
    suspend fun deleteOlderThan(timestamp: Long)
}
