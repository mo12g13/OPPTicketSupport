package com.Momo;
import java.util.*;

public class TicketManager {
   private static LinkedList<Ticket> ticketQueue;

    public static void main(String[] args) {
        // write your code here
       ticketQueue = new LinkedList<Ticket>();




        Scanner scan = new Scanner(System.in);
        int task;

        while (true) {


            while (true) {
                try {
                    System.out.println("1. Enter Ticket\n2. Delete Ticket by ID\n3. Delete by Issue\n4 Display All Tickets\n5. Quit");
                    task = Integer.parseInt(scan.nextLine());

                    if (task < 1 || task > 6) throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid number");

                }
            }

            if (task == 1) {
                //Call addTickets, which will let us enter any number of new tickets
                addTickets(ticketQueue);



            } else if (task == 2) {
           deleteTicket(ticketQueue);


            }

            else if(task ==3){
            deleteTicketByIssue();            }

            else if(task==4){
                printAllTickets(ticketQueue);
            }


            else if (task == 5) {
                //Quit. Future prototype may want to save all tickets to a file
                System.out.println("Quitting program");
                break;
            } else {
                //this will happen for 3 or any other selection that is a valid int
                //TODO Program crashes if you enter anything else - please fix
                //Default will be print all tickets
                printAllTickets(ticketQueue);
            }
        }

        scan.close();

    }

    // A method that deletes ticket based on the ticket number provided by the user
    protected static void deleteTicket(LinkedList<Ticket> ticketQueue) {
        printAllTickets(ticketQueue);   //display list for user

        if (ticketQueue.size() == 0) {    //no tickets found condition!!
            System.out.println("No tickets to delete!\n");
            return;
        }
        Scanner deleteScanner = new Scanner(System.in);
        boolean found = false;
        try {
            System.out.println("Enter ID of ticket to delete");
            int deleteID = deleteScanner.nextInt();

            //Loop over all tickets. Delete the one with this ticket ID

            for (Ticket ticket : ticketQueue) {
                if (ticket.getTicketID() == deleteID) {
                    found = true;
                    ticketQueue.remove(ticket);
                    System.out.println(ticket.getReporter());
                    System.out.println(String.format("Ticket %d deleted", deleteID));
                    break; //don't need loop any more.
                }
            }
        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid number");

        } catch (InputMismatchException ne) {
            System.out.println("Please enter a valid number");
        }


        //If ticket isn't found, prompt the user to enter the ticket ID, loop over the ticket and delete the ticket with the
        // right ticket ID
        if (found == false) {
            System.out.println("Ticket ID not found, no ticket deleted");
            try {
                System.out.println("Enter ID of ticket to delete");
                int deleteID = deleteScanner.nextInt();
                //Loop over all tickets. Delete the one with this ticket ID

                for (Ticket ticket : ticketQueue) {
                    if (ticket.getTicketID() == deleteID) {
                        found = true;
                        ticketQueue.remove(ticket);
                        System.out.println(String.format("Ticket %d deleted", deleteID));
                        break; //don't need loop any more.
                    }
                }
            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number");

            } catch (InputMismatchException ne) {
                System.out.println("Please enter a valid number");
            }
        }
        printAllTickets(ticketQueue);  //print updated list

    }

    //Move the adding ticket code to a method
    protected static void addTickets(LinkedList<Ticket> ticketQueue) {
        Scanner sc = new Scanner(System.in);

        boolean moreProblems = true;
        String description;
        String reporter;
        //let's assume all tickets are created today, for testing. We can change this later if needed
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        int priority;

        while (moreProblems) {
            try {
                System.out.println("Enter problem");
                description = sc.nextLine();
                System.out.println("Who reported this issue?");
                reporter = sc.nextLine();
                System.out.println("Enter priority of " + description);
                priority = Integer.parseInt(sc.nextLine());

                Ticket t = new Ticket(description, priority, reporter, dateReported);
                ticketQueue.add(t);

                //To test, let's print out all of the currently stored tickets
                printAllTickets(ticketQueue);

                System.out.println("More tickets to add?");
                String more = sc.nextLine();
                if (more.equalsIgnoreCase("N")) {
                    moreProblems = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please re-enter ticket because number wasn't valid number");
            } catch (InputMismatchException mne) {
                System.out.println("Please re-enter ticket because number wasn't valid number");
            }

        }

    }
    //Search for the Issue in the ticketQueue and add it to the ticket found List and return it.
    private static LinkedList<Ticket> searchTicketByIssue(String name){
        LinkedList<Ticket> ticketFound = new LinkedList<Ticket>();
        for(Ticket ticket: ticketQueue){
            if(ticket.getDescription().equalsIgnoreCase(name)){
                ticketFound.add(ticket);
            }
        }
        return ticketFound;
    }
//Search for the issue and if it exist, run the deleteTicket method and deleted the matching that was found
    //If it isn't in the list, return the string stating that the issue wasn't found.
    private static void deleteTicketByIssue(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the name of the issue you want to search ");
        String myIssue = input.nextLine();
        LinkedList<Ticket> matchingFound = searchTicketByIssue(myIssue);
        if(matchingFound.isEmpty()){
            System.out.println("No issue matching "+ myIssue + "was found");
            return;

        }

            deleteTicket(matchingFound);

    }











    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All open tickets ----------");

        for (Ticket t : tickets ) {
            System.out.println(t); //Write a toString method in Ticket class
            //println will try to call toString on its argument
        }
        System.out.println(" ------- End of ticket list ----------");

    }





}
