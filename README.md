[![](https://jitpack.io/v/podeli-bnpl/podeli-widget-android.svg)](https://jitpack.io/#podeli-bnpl/podeli-widget-android)

# podeli-widget-android

Чтобы подключить, нужно в settings.gradle добавить
```
pluginManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
И в build.gradle
```
dependencies {
  implementation 'com.github.podeli-bnpl:podeli-widget-android:v1.0.0'
}
```

Добавить вью
```
<com.podeli.podeliwidget.PodeliWidget
  android:id="@+id/podeli_widget"
  android:layout_width="match_parent"
  android:layout_height="wrap_content" />
```
В коде вызвать
```
paymentWidgetProcent.setPaymentAmount() // сумма будет отображаться в процентах
paymentWidget.setPaymentAmount(3000.0) // будет отображаться конкретная сумма, формат суммы double
```

Пример можно посмотреть в app модуле
