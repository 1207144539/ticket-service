Ticket Service

This is a system of ticket service. In this system, you can define the numbers of row and column of the venue and also the 
expire hold time. You can get the number of remain available tickets in this service. If this ticket is been hold or reserved,
it does not count as an available ticket. When someone holds some tickets, within the hold time, these tickets can be reserved. 
After the expire time, these tickets can not be reversed. 

* Functions:

- Find the number of seats available.

- Find and hold the available seats, and each ticket hold expires within 120 seconds.

- Reserve and commit a specific group of held seats for a customer.


* Prerequisites:

Install the latest version of Java and Maven.


* Import the project in the Intellij

1. Open IntelliJ IDEA and close any existing project.
2. From the Welcome screen, click Import Project.
3. The Select File or Directory to Import dialog opens.
4. For the Import project from external model value, select Maven and click Next.
5. Click all the next and then finish.



* Constants

Before run this program. We can define ROW_NUM, COL_NUM and EXPIRE_TIME based on the requirement of customer
in the class of Constants.

 1.ROW_NUM : how many row this service has.
 2.COL_NUM : how many column this service has
 3.EXPIRE_TIME : how long the hold ticket will expire and become available.

Development Environment is base on Intellij.

* Debug

Choose ->Main- com.walmart.tickerservice.presentation
Right-click on TicketService project
Debug As -> Java Application


* Run
First way to run project:
    Main function path:src/main/java/com.walmart.ticketservice/presentation
    Choose ->Main- com.walmart.tickerservice.presentation
    Right-click on TicketService project
    Run As -> Java Application

Second way to run project:
   1.	get into the root, for example:
   C:\Users\Administrator.SC-201809142147\Desktop\walmart\Ticket-Service-Walmart-master-final\Ticket-Service-Walmart-master-final>
   2.	Then go into the package:target. Use the command:cd target
   3.	Finally, use command java -jar TicketService-1.0-SNAPSHOT.jar  to run the application.

* Directory Structure

  -Presentation  (com.walmart.ticketservice.presentation)
       -Main.java

  -Service   (com.walmart.ticketservice.service)
       -TicketService.java
       -TicketServiceImpl

  -Data    (com.walmart.ticketservice.data)

       -DataAccess.Java
       -Seat.java
       -SeatHold.java
       -SeatImpl.java
  -Utils   (com.walmart.ticketservice.utils)
       -Constants
       -SelectTickets
       -CheckEmail


* Detail of usage
1 When running the program, it will remind typing some command. If you type 1, it will run the method of numSeatsAvailable.
  It will show the number of available tickets.
2 If you type 2, it will run the method of findAndHoldSeats. You should type the number of seats you want to hold and type the
  email. It will hold these ticket for you and return a seatHoldId. You can use this seatHoldId and your email to reserve ticket.
  the tickets and return seatHoldId.
3 If you type 3, it will run the method of reserveSeats. You should use the seatHoldId and email to reverse. After reversing
  success, it will show the success information and reserveCode.
4 If you type exit, it will exit this program.


Test Result by command line:

***************************************************************************
* 1-> run the method of numSeatsAvailable
* 2-> run the method of findAndHoldSeats
* 3-> run the method of reserveSeats
* exit -> exit this program
***************************************************************************
Please enter your command:
1
ticket remains:100
Please enter your command:
2
please enter seatNumber:
6
please enter email:
ma@gmail.com
Your seatHoldId is: 71, Seats are [<5, 1> <5, 2> <5, 3> <5, 4> <5, 5> <5, 6> ]
You should reserve it in 2 minutes, or it will be cancel
Please enter your command:
2
please enter seatNumber:
10
please enter email:
Bob@gmail.com
Your seatHoldId is: 534, Seats are [<5, 8> <5, 9> <5, 10> <6, 1> <6, 2> <6, 3> <6, 4> <6, 5> <6, 6> <5, 7> ]
You should reserve it in 2 minutes, or it will be cancel
Please enter your command:
3
Please enter your seatHoldId
534
Please enter your email
Bob@gmail.com
Success! Your reserveCode is:2019APRIL1991623411764000, and seat remains:84
Please enter your command:
3
Please enter your seatHoldId
534
SeatHoldId is not Exist. Please enter again.
Please enter your seatHoldId
100
SeatHoldId is not Exist. Please enter again.
Please enter your seatHoldId
101
SeatHoldId is not Exist. Please enter again.
102
type wrong
Please enter your command:
2
ticket remains:90
Please enter your command:
3
Please enter your seatHoldId
71
SeatHoldId expires now

Explain of the result:

1. Test the function of numSeatsAvailable. Because I initial 10*10 seats, the remain tickets are 100.
2. Test the function of findAndHoldSeats. Hold 6 tickets using email of ma@gmail.com. It will show the seatHoldId
   and all the seats that have been hold. The hold time is seted as 2 minutes.
3. Test the function of findAndHoldSeats. Hold 10 tickets using email of Bob@gmail.com. It will show the seatHoldId
   and all the seats that have been hold. The hold time is seted as 2 minutes.
4. Test the function of reserveSeats. Bob reserve the ticket by using the seatHoldId and email. It will show success information,
   the reserveCode and remaining tickets numbers. Because the ticket hold by ma is still in hold, the remainning tickets is
   100-6-10=84
5. If someone still uses the seatHoldId of Bob, because bob has used it one time this seatHoldId does not work.
6. Then test if someone types the seatHoldId wrong for three times. Person will not be allowed to type again.
7. After more than 2 minute, use the function of numSeatsAvailable. The remain tickets are 90 because the tickets hold
   by ma expires.
8. Then test if I can use the seatHoldId hold by ma. It will remind the seatHoldId expires.


Test result by JUnit:
Test function path:src/test/java/com.walmart.test
There are three test classes

1. The class of FindAndHoldSeatsTest is used to test the function of findAndHoldSeats and numSeatsAvailable. Firstly, get the number of
   remain tickets named as remainTicket. Then three people hold 14 tickets. Check if the remain tickets = remainTicket-14. After more than expire
   time, check if remain Tickets = remainTicket.
2. The class of ReserveSeatsTest is used to test the function of reserveSeats. Firstly, check the number of remain tickets
   named as remainTicket. Then three people hold 14 tickets. Check if the remain tickets = remainTicket-14. Then Bob reserves 5
   tickets. After more than expire time, check if remain Tickets = remainTicket -5, because this 5 tickets has been reserved by Bob.
3. The class of TestThread is used to test if this service is thread-safe. Firstly, there are only 100 tickets, and Alice reserve
   90 tickets. Therefore, it remains only 10 tickets. Thread1 want to hold 6 tickets, and thread2 want to hold 6 tickets. When these
   two threads run. The result:

   Thread2-Ticket remains: 10
   Thread1-Ticket remains: 10
   Thread2-After ry hold 6tickets, tickets remain: 4
   No enough tickets
   Thread1- Do not have enough ticket.
   End of Main

   Thread2 hold 6 tickets and Thread1 finds out it does not have enough tickets. Therefore, this service is thread-safe.



