package com.bbx.myjournal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bbx.myjournal.data.local.DatabaseConstants.JOURNAL_DATABASE_VERSION
import com.bbx.myjournal.data.EmotionEntity
import com.bbx.myjournal.utils.Converters

@Database(entities = [EmotionEntity::class], version = JOURNAL_DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun emotionDao(): EmotionDao
}