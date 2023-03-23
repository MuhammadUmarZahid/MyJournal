package com.bbx.myjournal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bbx.myjournal.DatabaseConstants.JOURNAL_DATABASE_VERSION

@Database(entities = [EmotionEntity::class], version = JOURNAL_DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun emotionDao():EmotionDao
}