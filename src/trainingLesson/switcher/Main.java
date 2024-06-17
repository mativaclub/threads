package trainingLesson.switcher;

public class Main {
    public static void main(String[] args) {
        MyProgramState state = new MyProgramState();

        ThreadA thread1 = new ThreadA(state);
        ThreadB thread2 = new ThreadB(state);

        thread1.start();
        thread2.start();
    }
}