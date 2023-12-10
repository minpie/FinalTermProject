package mainapp

import  utils.*
import data.Event

var isRunning = true

fun main() {
    var sess1: Control = Control()
    val filePath = "event.txt"
    sess1.LoadCalendar(filePath)

    while (isRunning) {
        // 메뉴 출력
        print("메뉴 번호를 선택하세요 (1: 종료, 2: 달력조회, 3: 이벤트 추가, 4: 제목으로 이벤트 조회 5: 내용으로 이벤트 조회 6: 이벤트 수정): ")
        // 사용자 입력 받기
        val choice = readLine()?.trim()?.toIntOrNull() ?: continue
        // 메뉴 처리
        when (choice) {
            1 -> {
                sess1.SaveCalendar(filePath)
                isRunning = false
            }

            2 -> {
                // 달력 조회
                val year = GetNumInt("년도를 입력하세요: ")
                val month = GetNumInt("월을 입력하세요: ")
                print(sess1.ShowCalenderMonth(year, month))
            }

            3 -> {
                println("이벤트를 추가합니다.")

                println("이벤트 시작 시점:")
                val startDate = ParseDateStr(GetString("년/월/일 을 입력하세요(yyyy/mm/dd):"))
                val startTime = ParseTimeStr(GetString("시/분/초 를 입력하세요(hh/mm/ss):"))
                val startType = GetString("종류를 입력하세요:")
                val startTitle = GetString("제목을 입력하세요:")
                val startContent = GetString("내용을 입력하세요:")

                println("이벤트 종료 시점:")
                val endDate = ParseDateStr(GetString("년/월/일 을 입력하세요(yyyy/mm/dd):"))
                val endTime = ParseTimeStr(GetString("시/분/초 를 입력하세요(hh/mm/ss):"))
                val endType = GetString("종류를 입력하세요:")
                val endTitle = GetString("제목을 입력하세요:")
                val endContent = GetString("내용을 입력하세요:")

                sess1.AddEvent(startDate[0], startDate[1], startDate[2], startTime[0], startTime[1], startTime[2], startType, startTitle, startContent)
                sess1.AddEvent(endDate[0], endDate[1], endDate[2], endTime[0], endTime[1], endTime[2], endType, endTitle, endContent)
                println("이벤트가 추가되었습니다.")
            }

            4 -> {
                println("이벤트를 제목으로 검색합니다.")
                val searchingInput = GetString("이벤트 제목을 입력하십시오 : ")
                print(sess1.ShowAllEventByTitle(searchingInput))
            }

            5 -> {
                println("이벤트를 내용으로 검색합니다.")
                val searchingInput = GetString("이벤트 내용을 입력하십시오 : ")
                print(sess1.ShowAllEventByContent(searchingInput))
            }

            6-> {
                // 사용자에게 수정할 이벤트 정보 입력 받기
                val startDate = ParseDateStr(GetString("수정할 이벤트의 년/월/일을 입력하세요(yyyy/mm/dd):"))
                val startTime = ParseTimeStr(GetString("수정할 이벤트의 시/분/초를 입력하세요(hh/mm/ss):"))
                val eventType = GetString("수정할 이벤트의 종류를 입력하세요:")
                val eventTitle = GetString("수정할 이벤트의 제목을 입력하세요:")
                val eventContent = GetString("수정할 이벤트의 내용을 입력하세요:")


                // 사용자에게 수정할 내용 입력 받기
                val newStartDate = ParseDateStr(GetString("새로운 년/월/일을 입력하세요(yyyy/mm/dd):"))
                val newStartTime = ParseTimeStr(GetString("새로운 시/분/초를 입력하세요(hh/mm/ss):"))
                val newEventType = GetString("새로운 종류를 입력하세요:")
                val newEventTitle = GetString("새로운 제목을 입력하세요:")
                val newEventContent = GetString("새로운 내용을 입력하세요:")

                // 이벤트 찾아 수정
                sess1.EditEvent(
                    startDate[0], startDate[1], startDate[2], startTime[0], startTime[1], startTime[2], eventType, eventTitle, eventContent,
                    newStartDate[0], newStartDate[1], newStartDate[2], newStartTime[0], newStartTime[1], newStartTime[2], newEventType, newEventTitle, newEventContent
                )
            }
        }
    }
    // 프로그램 종료
    println("프로그램을 종료합니다.")
}