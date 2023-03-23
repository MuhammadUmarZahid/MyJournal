package com.bbx.myjournal

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class EmotionDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var emotionDao: EmotionDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        emotionDao = database.emotionDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertEmotion_returnsTrue() = runBlocking {
        val emotion = EmotionEntity(emotionId = 3, emotionType = 1, emotionNotes = "test", createdAt = Date(System.currentTimeMillis()))
        emotionDao.insertEmotion(emotion)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.Main) {
            emotionDao.getAllEmotions().collect {
                assertThat(it).contains(emotion)
                latch.countDown()

            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun getAllEmotions_returnsTrue() = runBlocking {
        val emotion = EmotionEntity(emotionId = 3, emotionType = 1, emotionNotes = "test", createdAt = Date(System.currentTimeMillis()))
        val emotion2 = EmotionEntity(emotionId = 4, emotionType = -1, emotionNotes = "test1", createdAt = Date(System.currentTimeMillis()))
        val emotion3 = EmotionEntity(emotionId = 5, emotionType = 0, emotionNotes = "test2", createdAt = Date(System.currentTimeMillis()))
        emotionDao.insertEmotion(emotion)
        emotionDao.insertEmotion(emotion2)
        emotionDao.insertEmotion(emotion3)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.Main) {
            emotionDao.getAllEmotions().collect {
                assertThat(it.size).isEqualTo(3)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

}