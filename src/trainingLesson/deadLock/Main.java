package trainingLesson.deadLock;

public class Main {
    public static void main(String[] args) {

        Friend friend1 = new Friend("Вася");
        Friend friend2 = new Friend("Петя");

        DeadLock thread1 = new DeadLock(friend1, friend2);
        DeadLock thread2 = new DeadLock(friend2, friend1);

        thread1.start();
        thread2.start();

        System.out.println("конец работы");




    }
}