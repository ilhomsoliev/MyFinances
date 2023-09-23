package com.ilhomsoliev.myfinances.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ilhomsoliev.myfinances.data.dao.account.AccountDao
import com.ilhomsoliev.myfinances.data.dao.goal.GoalDao
import com.ilhomsoliev.myfinances.data.dao.goal.RefillDao
import com.ilhomsoliev.myfinances.data.dao.transaction.TransactionCategoryDao
import com.ilhomsoliev.myfinances.data.dao.transaction.TransactionDao
import com.ilhomsoliev.myfinances.data.model.account.AccountEntity
import com.ilhomsoliev.myfinances.data.model.goals.GoalEntity
import com.ilhomsoliev.myfinances.data.model.goals.RefillEntity
import com.ilhomsoliev.myfinances.data.model.transaction.TransactionCategoryEntity
import com.ilhomsoliev.myfinances.data.model.transaction.TransactionEntity
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [TransactionEntity::class, TransactionCategoryEntity::class, GoalEntity::class, AccountEntity::class, RefillEntity::class],
    version = 6,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionCategoryDao(): TransactionCategoryDao
    abstract fun goalDao(): GoalDao
    abstract fun accountDao(): AccountDao
    abstract fun refillDao(): RefillDao
}

@Volatile
private var INSTANCE: ApplicationDatabase? = null

fun getDatabaseInstance(context: Context) =
    INSTANCE ?: synchronized(context) {
        val instance =
            Room.databaseBuilder(context, ApplicationDatabase::class.java, "finances_database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let {
                            CoroutineScope(Dispatchers.IO).launch {
                                val transactionCategoryDao = it.transactionCategoryDao()
                                transactionCategoryDao.insert(getOutcomeCategories())
                            }
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
                .also {
                    INSTANCE = it
                }
        instance
    }

fun getOutcomeCategories() = listOf(
    TransactionCategoryEntity(
        id = 1,
        name = "Образование",
        iconId = 1,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 2,
        name = "Транспорт",
        iconId = 2,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 3,
        name = "Продукты",
        iconId = 3,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 4,
        name = "Одежда",
        iconId = 4,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 5,
        name = "Подписки",
        iconId = 5,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 6,
        name = "Помощь",
        iconId = 6,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 7,
        name = "Еда",
        iconId = 7,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 8,
        name = "Путешествия",
        iconId = 8,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 9,
        name = "Ремонт",
        iconId = 9,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 10,
        name = "Красота",
        iconId = 10,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
    TransactionCategoryEntity(
        id = 11,
        name = "Прочее",
        iconId = 11,
        type = TransactionType.Outcome.name,
        dateCreated = 0L,
    ),
)