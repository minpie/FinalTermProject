package mainapp

import utils.*
import data.Calendar
import java.io.File
import com.google.gson.*

class Control {
    lateinit var current_calendar:Calendar

    constructor(){
        current_calendar = Calendar()
    }
    constructor(fname:String){
        LoadCalendar(fname)
    }
    fun SaveCalendar(fname: String) {
        try {
            val gson = Gson()
            File(fname).writeText(gson.toJson(current_calendar))
        } catch (e: Exception) {
            println("Error saving calendar to file: ${e.message}")
        }
    }
    fun LoadCalendar(fname:String){
        if(!File(fname).exists()){
            return
        }
        val jsonString = File(fname).readText()
        current_calendar = Gson().fromJson(jsonString, Calendar::class.java)
    }
    fun CheckEvent(){
    }
    fun AddEvent(year:Int, month:Int, day:Int, hour: Int, minute:Int, second: Int, type:String, title:String, content:String) {
        current_calendar.AddEvent(year, month, day, hour, minute, second, type, title, content)
    }
    fun DeleteEvent(year:Int, month:Int, day:Int, hour: Int, minute:Int, second: Int, type:String, title:String, content:String){
        current_calendar.DeleteEvent(current_calendar.FindEvent(year, month, day, hour, minute, second, type, title, content))
    }
    fun ShowCalenderMonth(year:Int, month:Int):String{
        return current_calendar.PutCalendar(year, month)
    }
    fun EditEvent(
        year:Int, month:Int, day:Int, hour: Int, minute:Int, second: Int, type:String, title:String, content:String,
        year2:Int, month2:Int, day2:Int, hour2: Int, minute2:Int, second2: Int, type2:String, title2:String, content2:String
        ) {
        current_calendar.EditEventTime(current_calendar.FindEvent(year, month, day, hour, minute, second, type, title, content), year2, month2, day2, hour2, minute2, second2)
        current_calendar.EditEventContent(current_calendar.FindEvent(year2, month2, day2, hour2, minute2, second2, type, title, content), type2, title2, content2)
    }
    fun GetEvent():
}