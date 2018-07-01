public class Application {

    public static void main(String[] args) {
        if (args.length == 0) {
            DataBase.createDataBase();
            new Thread(new TimeStampsCreation()).start();
            new Thread(new TimeStampsCommitAndDelete()).start();
        }

        if (args.length > 0){
            if (args[0].equals("-p")) {
                DataBaseReader.readDataBase();
            }
        }
    }
}
