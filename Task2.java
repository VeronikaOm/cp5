import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Task2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Запити на отримання погоди для трьох міст
        CompletableFuture<WeatherData> city1Weather = getWeatherData("Kyiv");
        CompletableFuture<WeatherData> city2Weather = getWeatherData("Lviv");
        CompletableFuture<WeatherData> city3Weather = getWeatherData("Odesa");

        // Використовуємо allOf для того, щоб дочекатись завершення всіх запитів
        CompletableFuture<Void> allWeather = CompletableFuture.allOf(city1Weather, city2Weather, city3Weather);
        allWeather.get();  // Очікуємо завершення всіх завдань

        // Використовуємо thenCompose для подальшої обробки
        CompletableFuture<String> comparison = city1Weather.thenCompose(kyiv ->
                city2Weather.thenCombine(city3Weather, (lviv, odesa) -> {
                    compareWeather(kyiv, lviv, odesa);
                    return "Порівняння завершено!";
                })
        );

        // Виводимо результати порівняння погоди
        System.out.println("Kyiv Weather: " + city1Weather.get());
        System.out.println("Lviv Weather: " + city2Weather.get());
        System.out.println("Odesa Weather: " + city3Weather.get());
        System.out.println(comparison.get());  // Очікуємо на завершення порівняння
    }

    // Метод для отримання даних про погоду
    private static CompletableFuture<WeatherData> getWeatherData(String city) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);  // Імітація затримки
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Імітація різної погоди для різних міст
            if (city.equals("Kyiv")) {
                return new WeatherData(5, 80, 10); // Київ
            } else if (city.equals("Lviv")) {
                return new WeatherData(15, 60, 5);  // Львів
            } else { // Odesa
                return new WeatherData(25, 50, 15); // Одеса
            }
        });
    }

    // Порівняння температури, вологості та швидкості вітру для трьох міст
    private static void compareWeather(WeatherData kyiv, WeatherData lviv, WeatherData odesa) {
        System.out.println("\nПорівняння погоди:");

        if (odesa.getTemperature() > 20) {
            System.out.println("В Одесі ідеальна погода для пляжу!");
        } else {
            System.out.println("В Одесі холодно, варто вдягнутись тепліше.");
        }

        if (lviv.getTemperature() > 20) {
            System.out.println("У Львові можна поїхати на пляж.");
        } else {
            System.out.println("У Львові холодно, варто вдягнутись тепліше.");
        }

        if (kyiv.getTemperature() > 15) {
            System.out.println("У Києві можна відпочити на пляжі.");
        } else {
            System.out.println("У Києві холодно, варто вдягнутись тепліше.");
        }
    }
}

// Клас WeatherData для зберігання інформації про погоду
class WeatherData {
    private final int temperature;
    private final int humidity;
    private final int windSpeed;

    public WeatherData(int temperature, int humidity, int windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + "°C, Humidity: " + humidity + "%, Wind Speed: " + windSpeed + " km/h";
    }
}
