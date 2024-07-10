# **Тестовое задание для Clevertec**

## Описание

### [Задание](./documentation/task.pdf)

## Как запустить проект

`javac -d out -sourcepath src ./src/main/java/ru/clevertec/check/CheckRunner.java`

`java -cp out main.java.ru.clevertec.check.CheckRunner id-quantity discountCard=xxxx balanceDebitCard=xxxx`

где:

id - идентификатор товара

quantity - количество товара
discountCard=xxxx - название и номер дисконтной карты

balanceDebitCard=xxxx - баланс на дебетовой карте

## Пример запуска:

`java -cp out main.java.ru.clevertec.check.CheckRunner 1-3 2-5 1-1 discountCard=1111 balanceDebitCard=100 `

В результате команды будет создан файл result.csv в корне проекта, содержащий в себе список товаров и их количество с
ценой, а также рассчитанную сумму с учетом скидки по предъявленной карте, если она есть.
Чек будет продублирован в консоль.

Пример файла result.csv:
[result.csv](result.csv)

Реализована обработка исключений. В случае возникновения исключения, исключение будет записано в файл `result.csv` с
описанием исключения.