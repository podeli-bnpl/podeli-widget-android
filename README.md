# podeli-widget-android
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
