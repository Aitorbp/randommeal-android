package com.example.randommeal.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true) val idDDBB: Int,
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
) : Parcelable

