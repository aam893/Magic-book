package com.example.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.Update
import android.content.Context
import com.example.data.model.BilingualParagraph
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language
import com.example.data.model.VocabularyWord
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val translatedTitle: String,
    val author: String,
    val description: String,
    val targetLanguageCode: String,
    val nativeLanguage: String,
    val difficultyCode: String,
    val coverEmoji: String,
    val coverGradientStart: Long,
    val coverGradientEnd: Long,
    val paragraphsJson: String,
    val isAiGenerated: Boolean,
    val isFavorite: Boolean,
    val currentParagraphIndex: Int,
    val totalWords: Int,
    val dateCreated: Long = System.currentTimeMillis()
)

@Entity(tableName = "vocabulary")
data class VocabularyEntity(
    @PrimaryKey val id: String,
    val word: String,
    val translation: String,
    val phonetic: String,
    val partOfSpeech: String,
    val exampleTarget: String,
    val exampleTranslation: String,
    val languageCode: String,
    val masteryLevel: Int,
    val nextReviewTimestamp: Long,
    val bookTitleSource: String,
    val dateAdded: Long = System.currentTimeMillis()
)

class DatabaseConverters {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val paragraphListType = Types.newParameterizedType(List::class.java, BilingualParagraph::class.java)
    private val adapter = moshi.adapter<List<BilingualParagraph>>(paragraphListType)

    @TypeConverter
    fun fromParagraphList(paragraphs: List<BilingualParagraph>?): String {
        return adapter.toJson(paragraphs ?: emptyList())
    }

    @TypeConverter
    fun toParagraphList(json: String?): List<BilingualParagraph> {
        return if (json.isNullOrBlank()) emptyList() else adapter.fromJson(json) ?: emptyList()
    }
}

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY dateCreated DESC")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id LIMIT 1")
    suspend fun getBookById(id: String): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("UPDATE books SET currentParagraphIndex = :index WHERE id = :id")
    suspend fun updateReadingProgress(id: String, index: Int)

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteBookById(id: String)
}

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary ORDER BY dateAdded DESC")
    fun getAllVocabulary(): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE languageCode = :langCode ORDER BY dateAdded DESC")
    fun getVocabularyByLanguage(langCode: String): Flow<List<VocabularyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: VocabularyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<VocabularyEntity>)

    @Update
    suspend fun updateWord(word: VocabularyEntity)

    @Query("UPDATE vocabulary SET masteryLevel = :mastery, nextReviewTimestamp = :nextReview WHERE id = :id")
    suspend fun updateWordMastery(id: String, mastery: Int, nextReview: Long)

    @Delete
    suspend fun deleteWord(word: VocabularyEntity)

    @Query("DELETE FROM vocabulary WHERE id = :id")
    suspend fun deleteWordById(id: String)
}

@Database(entities = [BookEntity::class, VocabularyEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun vocabularyDao(): VocabularyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "linguaread_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
