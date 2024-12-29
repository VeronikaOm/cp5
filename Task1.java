import java.util.concurrent.*;

public class Task1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> fetchDataFromDB = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);  //Імітація затримки(роботи з БД)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Дані отримано з бази");
            return "Щось з БД";
        });

        CompletableFuture<String> processData = fetchDataFromDB.thenCompose(resultFromDB -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("Обробка даних: " + resultFromDB);
                return resultFromDB + " - оброблено";
            });
        });

        CompletableFuture<String> combineResult = fetchDataFromDB.thenCombine(processData, (result1, result2) -> {
            return result1 + " та " + result2;
        });

        String finalResult = combineResult.get();
        System.out.println("Результат комбінації: " + finalResult);

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(fetchDataFromDB, processData);
        allOfFuture.get();
        System.out.println("Усі завдання виконано.");

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(fetchDataFromDB, processData);
        Object anyResult = anyOfFuture.get();
        System.out.println("Перше завершене завдання: " + anyResult);
    }
}