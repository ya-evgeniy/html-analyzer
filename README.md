# html-analyzer
### База данных:
Для работы приложения необходима база данных PostgreSQL. В jar-файле приложения откройте файл `hibernate.cfg.tld` для изменения настроек подключения к базе данных. Пример файла представлен ниже.
1. В строке №3 **меняем ип базы** с localhost на ип который использует ваша база, так же **меняем порт** (5432), если он установлен не по умолчанию и **меняем название базы** (test) на ваше.
2. В строке №4 **меняем имя пользователя** (evgeniy_test) на имя пользователя в вашей базе. Пользователю необходимо разрешить создавать таблицы и вставлять/обновлять строки. 
3. В строке №5 **меняем пароль** для пользователя с 000000 на ваш.
```
1 <session-factory>
2     <property name="hibernate.hbm2ddl.auto">update</property>
3     <property name="connection.url">jdbc:postgresql://localhost:5432/test</property>
4     <property name="connection.username">evgeniy_test</property>
5     <property name="connection.password">000000</property>
6     <property name="hibernate.dialect">org.hibernate.dialect.PostgreSQL10Dialect</property>
7 </session-factory>
```

### Запуск приложения
Для запуска приложения из под Windows открываем командную строку и выполняем код представленный ниже. Перед запуском меняем `<sitelink>` на адрес страницы которую нужно распарсить.
```
chcp 65001
java -jar html.analyzer-1.1.jar <sitelink>
```

### Логирование
Лог файл создается по пути `%userprofile%\html_analyzer.log`
