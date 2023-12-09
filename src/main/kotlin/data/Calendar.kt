package data
import utils.*

class Calendar {
    private var events:ArrayList<Event>

    constructor(){
        events = arrayListOf()
    }
    fun AddEvent(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int, type: String, title: String, content: String) {
        events.add(Event(year, month, day, hour, minute, second, type, title, content))
    }
    fun DeleteEvent(evnt:Event?){
        if(evnt != null){
            events.remove(evnt)
        }
    }
    fun PutCalendar(year:Int, month:Int):String{
        // Variable:
        var result:String = ""

        // 초기 연산:
        val firstDayOfMonth = GetWeekNum(year, month, 1) // 첫 날의 요일을 구함 (1: 일요일, 2: 월요일, ..., 7: 토요일) /TEST
        val lastDayOfMonth = GetLastDayOfMonth(year, month) // 해당 월의 마지막 날짜를 구함 /TEST


        // 표 헤더 출력
        result += "일\t\t월\t\t화\t\t수\t\t목\t\t금\t\t토\n"

        // 첫 번째 주의 날짜까지의 빈 칸 출력
        for(i in 1 until firstDayOfMonth)
            result += "\t\t" // 각 날짜를 두 칸으로 늘림
        // 날짜 & 이벤트 개수 출력하기:
        for(day in 1..lastDayOfMonth){
            // 날짜 출력 (빈 날짜에는 빈 칸 출력)
            val eventCount = FindAllEventByDate(year, month, day).size // 해당 날짜의 이벤트 수 /TEST
            result += "${day}${if (eventCount > 0) "\t(${eventCount})" else "\t"}\t"
            // 줄바꿈 처리 (7일마다)
            if ((firstDayOfMonth + day - 1) % 7 == 0) {
                result += "\n"
            }
        }
        result += "\n"

        return result
    }
    fun getEvents(): List<Event> {
        return events
    }
    fun FindAllEventByDate(year:Int, month:Int, day:Int):ArrayList<Event>{
        var rtn: ArrayList<Event> = arrayListOf()
        for (evnt in events){
            val check_query = arrayOf(year, month, day)
            val dates = evnt.GetWhen()
            val check_target = arrayOf(dates[0], dates[1], dates[2])
            if(
                (check_query[0] == dates[0]) &&
                (check_query[1] == dates[1]) &&
                (check_query[2] == dates[2])
            ){
                rtn.add(evnt)
            }
        }
        return rtn
    }
    fun FindAllEventByTitle(title:String): ArrayList<Event> {
        var rtn: ArrayList<Event> = arrayListOf()
        for (evnt in events){
            val contentArray = evnt.GetContent()
            if (
                (title.isEmpty() || title == contentArray[1])
            ) {
                rtn.add(evnt)
            }
        }
        return rtn
    }
    fun FindAllEventByContent(content:String): ArrayList<Event> {
        var rtn: ArrayList<Event> = arrayListOf()
        for (evnt in events){
            val contentArray = evnt.GetContent()
            if (
                (content.isEmpty() || content == contentArray[2])
            ) {
                rtn.add(evnt)
            }
        }
        return rtn
    }
    fun FindEvent(year:Int, month:Int, day:Int, hour: Int, minute:Int, second: Int, eventType: String, eventTitle: String, eventContent: String):Event?{
        var rtn: Event? = null
        for (evnt in events){
            val check_query = arrayOf(year, month, day, hour, minute, second)
            val contentArray = evnt.GetContent()
            if (
                check_query.contentEquals(evnt.GetWhen()) &&
                (eventType.isEmpty() || eventType == contentArray[0]) &&
                (eventTitle.isEmpty() || eventTitle == contentArray[1]) &&
                (eventContent.isEmpty() || eventContent == contentArray[2])
            ) {
                return evnt
            }
        }
        return null
    }
    fun EditEventTime(evnt:Event?, year2: Int, month2: Int, day2: Int, hour2: Int, minute2: Int, second2: Int) {
        if(evnt != null){
            evnt.SetWhen(year2, month2, day2, hour2, minute2, second2)
        }
    }
    fun EditEventContent(evnt:Event?, eventType: String, eventTitle: String, eventContent: String){
        if(evnt != null){
            evnt.SetContent(eventType, eventTitle, eventContent)
        }
    }
    fun PutEventText(evnt:Event?): String? {
        if(evnt != null){
            return evnt.toText()
        }
        return null
    }
}