package com.ilhomsoliev.myfinances.app

import android.app.Application
import android.content.Context
import com.ilhomsoliev.myfinances.data.DataStoreManager
import com.ilhomsoliev.myfinances.data.dao.transaction.TransactionDao
import com.ilhomsoliev.myfinances.data.db.ApplicationDatabase
import com.ilhomsoliev.myfinances.data.db.getDatabaseInstance
import com.ilhomsoliev.myfinances.data.repository.AccountRepository
import com.ilhomsoliev.myfinances.data.repository.GoalRepository
import com.ilhomsoliev.myfinances.data.repository.TransactionRepository
import com.ilhomsoliev.myfinances.feature.accounts.viewmodel.AccountsViewModel
import com.ilhomsoliev.myfinances.feature.accounts.viewmodel.DetailedAccountViewModel
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.AddGoalViewModel
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.DetailedGoalViewModel
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.GoalsViewModel
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.bottom.DetailedGoalMenuBsViewModel
import com.ilhomsoliev.myfinances.feature.main.viewmodel.AddTransactionViewModel
import com.ilhomsoliev.myfinances.feature.main.viewmodel.MainViewModel
import com.ilhomsoliev.myfinances.feature.onboarding.viewmodel.OnBoardingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class FinancesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@FinancesApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    roomModule,
                    dataStore(this@FinancesApplication)
                )
            )
        }
    }
}


val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { AddTransactionViewModel(get(),) }
    viewModel { AccountsViewModel(get()) }
    viewModel { DetailedAccountViewModel(get()) }
    viewModel { GoalsViewModel(get()) }
    viewModel { AddGoalViewModel(get()) }
    viewModel { DetailedGoalViewModel(get()) }
    viewModel { OnBoardingViewModel(get()) }
    viewModel { DetailedGoalMenuBsViewModel(get()) }
}

val repositoryModule = module {
    single { TransactionRepository(get(),get()) }
    single { AccountRepository(get(), get()) }
    single { GoalRepository(get(), get()) }
}

val roomModule = module {
    single { getDatabaseInstance(androidContext()) }
    single { get<ApplicationDatabase>().accountDao() }
    single { get<ApplicationDatabase>().goalDao() }
    single { get<ApplicationDatabase>().transactionDao() }
    single { get<ApplicationDatabase>().transactionCategoryDao() }
    single { get<ApplicationDatabase>().refillDao() }
}

fun dataStore(context: Context) = module {
    single { DataStoreManager(context) }
}