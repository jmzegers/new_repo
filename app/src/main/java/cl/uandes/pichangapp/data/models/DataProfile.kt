package cl.uandes.pichangapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dataProfile")
data class DataProfile(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "nameTeam")
    open val nameTeam: String,
    @ColumnInfo(name = "email")
    open val email: String,
    @ColumnInfo(name = "password")
    open val password: String,
    @ColumnInfo(name = "category")
    open val category: String,
    @ColumnInfo(name = "id")
    open val id: Int
)