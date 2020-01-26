package dk.jonathan.nespresso.capsules.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "capsule")
data class Capsule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "brand") var brand: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "intensity")var intensity: Int = 0,
    @ColumnInfo(name = "rating")var rating: Int = 0,
    @ColumnInfo(name = "barcode")var barcode: String = "",
    @ColumnInfo(name = "color")var color: String = ""
){

    @Override
    override fun toString(): String {
        return "$id:$color $brand $name($intensity): Rating: $rating "
    }
}