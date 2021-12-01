package com.lvtu.koala.data.local

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lvtu.koala.KoalaApplication
import com.lvtu.koala.data.local.dao.BankDao
import com.lvtu.koala.data.local.dao.FundDao
import com.lvtu.koala.data.local.dao.MatchOrderDao
import com.lvtu.koala.data.local.entity.BankEntity
import com.lvtu.koala.data.local.entity.FundEntity
import com.lvtu.koala.data.local.entity.MatchOrderEntity

@Database(entities = [BankEntity::class, FundEntity::class, MatchOrderEntity::class], version = 7, exportSchema = false)
abstract class KoalaDatabase: RoomDatabase() {
    companion object {
        private const val TAG = "KoalaDatabase"
        var dataBase =
            Room.databaseBuilder(KoalaApplication.INSTANCE, KoalaDatabase::class.java, "db_koala")
                //是否允许在主线程进行查询
                .allowMainThreadQueries()
                //数据库创建和打开后的回调，可以重写其中的方法
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d(TAG, "onCreate: db_user")
                    }
                })
                //数据库升级异常之后的回滚
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun getBankDao(): BankDao

    abstract fun getFundDao(): FundDao

    abstract fun getMatchOrderDao(): MatchOrderDao
}