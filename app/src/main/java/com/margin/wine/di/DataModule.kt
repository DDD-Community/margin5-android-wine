package com.margin.wine.di

import com.margin.wine.data.source.repository.WineNoteRepositoryImpl
import com.margin.wine.data.source.repository.WineRepositoryImpl
import com.margin.wine.domain.repository.WineNoteRepository
import com.margin.wine.domain.repository.WineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindWineRepository(repository: WineRepositoryImpl) : WineRepository

    @Binds
    abstract fun bindWineNoteRepository(impl: WineNoteRepositoryImpl) : WineNoteRepository

}