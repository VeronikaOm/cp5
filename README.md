# Практичне завдання 5
## Завдання 1
### Короткий опис 
Програма демонструє роботу з класом `CompletableFuture` у Java для асинхронного виконання завдань. Вона створює два асинхронних завдання:
1. Перше завдання імітує отримання даних з бази даних з затримкою.
2. Друге завдання обробляє отримані дані після завершення першого.

Програма також показує використання таких методів класу `CompletableFuture`, як:
- `thenCompose()`
- `thenCombine()`
- `allOf()`
- `anyOf()`

### Опис методів

1. **`thenCompose()`**:
   - Метод використовується для асинхронного виконання завдання, яке залежить від результату попереднього завдання. У нашому випадку друге завдання (обробка даних) виконується після того, як перше завдання (отримання даних з бази) завершується.

2. **`thenCombine()`**:
   - Використовується для поєднання результатів двох асинхронних завдань Програма комбінує результат отримання даних з БД і результат обробки цих даних, щоб отримати кінцевий результат.

3. **`allOf()`**:
   - Використовується для того, щоб дочекаютися завершення обох асинхронних завдань, і тільки після цього виконується певна дія. Програма використовує allOf() для того, щоб переконатися, що обидва завдання завершені, перед тим як вивести повідомлення.

4. **`anyOf()`**:
   - Використовується для того, щоб дочекайтеся завершення першого з декількох асинхронних завдань. У програмі цей метод визначає, яке з завдань завершиться першим, і виводить його результат.

### Результат

Після завершення обох завдань програма комбінує їх результати, чекає на завершення всіх завдань та виводить відповідні повідомлення. Також визначається, яке з завдань завершилося першим.

## Завдання 2

### Короткий опис 
Програма порівнює погоду в трьох містах (Київ, Львів, Одеса) за допомогою асинхронних операцій, використовуючи методи класу `CompletableFuture` для паралельного отримання даних про температуру, вологість і швидкість вітру.

### Опис методів

1. **`thenCompose()`**:
Використовується для того, щоб після отримання погоди для Києва, виконати додаткові операції з обробкою результатів для Львова та Одеси. Зокрема, викликається метод `thenCombine()`, щоб паралельно порівняти погоду для трьох міст.

2. **`thenCombine()`**:
Використовується для комбінування результатів погоди для Львова та Одеси, коли вже отримано дані для Києва. Цей метод дозволяє порівняти погоду в трьох містах і вивести результат після їх обробки.

3. **`allOf()`**:
Використовується для того, щоб переконатися, що всі запити на погоду для трьох міст завершилися перед тим, як почати обробку даних.

### Результат

Виведення даних про погоду для трьох міст (Київ, Львів, Одеса) та порівняння їх температур. Програма визначає, де зараз найкраща погода для пляжу, а де слід одягнутися тепліше, залежно від температури в кожному місті.